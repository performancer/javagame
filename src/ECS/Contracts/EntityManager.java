package ECS.Contracts;

import ECS.Signature;

import java.util.Collection;
import java.util.Set;

public interface EntityManager {
    void initialize(Set<Signature> signatures);

    Collection<Entity> getBySignature(Signature signature);

    Entity register(Entity entity);

    void unregister(Entity entity);

    void subscribe(Entity entity, int index);
}
