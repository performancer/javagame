package ECS.Contracts;

import ECS.Signature;

import java.util.Set;

public interface SystemManager {
    Set<Signature> getSignatures();

    SystemManager register(System system, Signature signature);

    void update(EntityManager entityManager);
}
