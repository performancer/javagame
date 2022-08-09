package ECS.Contracts;

import java.util.Collection;

public interface System {
    void update(Collection<Entity> entities);
}
