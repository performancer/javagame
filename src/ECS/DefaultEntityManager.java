package ECS;

import ECS.Contracts.ComponentManager;
import ECS.Contracts.Entity;
import ECS.Contracts.EntityManager;

import java.util.*;

public class DefaultEntityManager implements EntityManager {
    private final HashMap<Entity, Signature> entityToSignatureMap = new HashMap<>();
    private final HashMap<Signature, Collection<Entity>> signatureToEntitiesMap = new HashMap<>();

    private void onEntitySignatureChanged(Entity entity) {
        Signature entitySignature = this.entityToSignatureMap.get(entity);

        for (Signature collectionSignature : this.signatureToEntitiesMap.keySet()) {
            Collection<Entity> entityArray = this.signatureToEntitiesMap.get(collectionSignature);

            if (entitySignature.contains(collectionSignature)) {
                entityArray.add(entity);
            } else {
                entityArray.remove(entity);
            }

            this.signatureToEntitiesMap.put(collectionSignature, entityArray);
        }
    }

    @Override
    public void initialize(Set<Signature> signatures) {
        for (Signature signature : signatures) {
            this.signatureToEntitiesMap.put(signature, new LinkedList<>());
        }
    }

    @Override
    public Collection<Entity> getBySignature(Signature signature) {
        if (!this.signatureToEntitiesMap.containsKey(signature)) {
            return new LinkedList<>();
        }

        return signatureToEntitiesMap.get(signature);
    }

    @Override
    public Entity register(Entity entity) {
        this.entityToSignatureMap.put(entity, new Signature());
        this.onEntitySignatureChanged(entity);

        return entity;
    }

    @Override
    public void unregister(Entity entity) {
        this.entityToSignatureMap.remove(entity);
        this.onEntitySignatureChanged(entity);
    }

    @Override
    public void subscribe(Entity entity, int index) {
        Signature signature = this.entityToSignatureMap.get(entity);
        signature.set(index);
        this.entityToSignatureMap.put(entity, signature);
        this.onEntitySignatureChanged(entity);
    }
}
