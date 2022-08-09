package ECS;

import ECS.Contracts.Component;
import ECS.Contracts.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ComponentArray {
    private final ArrayList<Component> components = new ArrayList<>();
    private final HashMap<Entity, Integer> entityToIndexMap = new HashMap<>();
    private final HashMap<Integer, Entity> indexToEntityMap = new HashMap<>();
    private int size = 0;

    public Collection<Entity> getEntities() {
        return entityToIndexMap.keySet();
    }

    public Component get(Entity entity) {
        if (!this.entityToIndexMap.containsKey(entity)) {
            throw new RuntimeException("The entity does not have this kind of component registered");
        }

        int index = this.entityToIndexMap.get(entity);

        return this.components.get(index);
    }

    public void insert(Entity entity, Component component) {
        if (this.entityToIndexMap.containsKey(entity)) {
            throw new RuntimeException("This entity already has component of same type registered");
        }

        int index = size;
        this.entityToIndexMap.put(entity, index);
        this.indexToEntityMap.put(index, entity);
        this.components.add(index, component);
        this.size++;
    }

    public void remove(Entity entity) {
        if (!this.entityToIndexMap.containsKey(entity)) {
            return;
        }

        //replace the removed element with the last element to keep the size smaller
        int indexOfRemovedEntity = this.entityToIndexMap.get(entity);
        int indexOfLastElement = this.size - 1;
        this.components.set(indexOfRemovedEntity, this.components.get(indexOfLastElement));

        //update the hashmaps
        Entity entityOfLastElement = this.indexToEntityMap.get(indexOfLastElement);
        this.entityToIndexMap.put(entityOfLastElement, indexOfRemovedEntity);
        this.indexToEntityMap.put(indexOfRemovedEntity, entityOfLastElement);

        //remove the superfluous data
        this.entityToIndexMap.remove(entity);
        this.indexToEntityMap.remove(indexOfLastElement);

        this.size--;
    }
}
