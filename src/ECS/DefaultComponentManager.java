package ECS;

import ECS.Contracts.Component;
import ECS.Contracts.ComponentManager;
import ECS.Contracts.Entity;

import java.util.Collection;
import java.util.HashMap;

public class DefaultComponentManager implements ComponentManager {
    private final HashMap<String, ComponentArray> arrays = new HashMap<>();
    private final HashMap<String, Integer> signatureIndices = new HashMap<>();
    private int nextSignatureIndex = 0;

    private <T extends Component> String getKey(Class<T> componentClass) {
        return componentClass.getCanonicalName();
    }

    private <T extends Component> ComponentArray getArray(Class<T> componentClass) {
        String key = this.getKey(componentClass);

        if (!this.arrays.containsKey(key)) {
            throw new RuntimeException("This kind of component is not registered");
        }

        return this.arrays.get(key);
    }

    @Override
    public <T extends Component> int getSignatureIndex(Class<T> componentClass) {
        String key = this.getKey(componentClass);

        if (!this.signatureIndices.containsKey(key)) {
            throw new RuntimeException("This type of component is not registered");
        }

        return this.signatureIndices.get(key);
    }

    @Override
    public <T extends Component> ComponentManager register(Class<T> componentClass) {
        String key = this.getKey(componentClass);
        this.signatureIndices.put(key, this.nextSignatureIndex);
        this.arrays.put(key, new ComponentArray());
        this.nextSignatureIndex++;

        return this;
    }

    @Override
    public void bind(Entity entity, Component component) {
       this.getArray(component.getClass()).insert(entity, component);
    }

    @Override
    public <T extends Component> T get(Entity entity, Class<T> componentClass) {
        return (T) this.getArray(componentClass).get(entity);
    }

    @Override
    public <T extends Component> Collection<Entity> getEntities(Class<T> componentClass) {
        return this.getArray(componentClass).getEntities();
    }

    @Override
    public <T extends Component> void unbind(Entity entity, Class<T> componentClass) {
        this.getArray(componentClass).remove(entity);
    }

    @Override
    public void unbindAll(Entity entity) {
        for (ComponentArray array : this.arrays.values()) {
            array.remove(entity);
        }
    }
}
