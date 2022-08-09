package ECS;

import ECS.Contracts.Entity;
import ECS.Contracts.EntityManager;
import ECS.Contracts.System;
import ECS.Contracts.SystemManager;

import java.util.*;

public class DefaultSystemManager implements SystemManager {
    private final HashMap<String, System> systems = new HashMap<>();
    private final HashMap<String, Signature> signatures = new HashMap<>();

    private <S extends System> String getKey(Class<S> systemClass) {
        return systemClass.getCanonicalName();
    }

    @Override
    public Set<Signature> getSignatures() {
        return new LinkedHashSet<>(this.signatures.values());
    }

    @Override
    public SystemManager register(System system, Signature signature) {
        String key = this.getKey(system.getClass());
        this.systems.put(key, system);
        this.signatures.put(key, signature);

        return this;
    }

    @Override
    public void update(EntityManager entityManager) {
        for (System system : this.systems.values()) {
            String key = this.getKey(system.getClass());
            Collection<Entity> entities = entityManager.getBySignature(this.signatures.get(key));
            system.update(entities);
        }
    }
}
