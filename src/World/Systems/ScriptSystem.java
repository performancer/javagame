package World.Systems;

import ECS.Contracts.ComponentManager;
import ECS.Contracts.Entity;
import ECS.Contracts.System;
import World.Components.TransformComponent;
import World.Vec2;

import java.util.Collection;
import java.util.Random;

public class ScriptSystem implements System {
    private final ComponentManager componentManager;

    public ScriptSystem(ComponentManager componentManager) {
        this.componentManager = componentManager;
    }

    private boolean isMovementBlocked(Vec2 position) {
        Collection<Entity> entitiesWithTransform = this.componentManager.getEntities(TransformComponent.class);
        for (Entity entityWithTransform : entitiesWithTransform) {
            TransformComponent transform = entityWithTransform.getComponent(TransformComponent.class);

            if (transform.position.equals(position)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void update(Collection<Entity> entities) {
        for(Entity entity : entities) {
            final TransformComponent transform = entity.getComponent(TransformComponent.class);
            java.lang.System.out.println("This entity is thinking");
            java.lang.System.out.println("My position is " + transform.position);

            Random random = new Random();
            int x = random.nextInt(3) - 1;
            int y = random.nextInt(3) - 1;

            final Vec2 newPosition = new Vec2(transform.position.getX() + x, transform.position.getY() + y);
            java.lang.System.out.println("I'm moving from " + transform.position + " to " + newPosition);

            if (isMovementBlocked(newPosition)) {
                java.lang.System.out.println("Seems like we are blocked...");

                continue;
            }

            transform.position = newPosition;
        }
    }
}
