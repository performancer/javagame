package Game.World.Systems;

import ECS.Contracts.ComponentManager;
import ECS.Contracts.Entity;
import ECS.Contracts.System;
import Pathfinding.AStar;
import Pathfinding.PathNotFoundException;
import Game.World.Components.TransformComponent;
import Game.World.Region;
import Game.World.Vec2;
import Game.World.Vec2Heuristic;

import java.util.Collection;
import java.util.Stack;

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

            Region region = new Region();

            for (Entity entityWithTransform : this.componentManager.getEntities(TransformComponent.class)) {
                TransformComponent t = entityWithTransform.getComponent(TransformComponent.class);

                if (t.position.equals(transform.position)) {
                    continue;
                }

                region.setImpassable(t.position);
            }

            Vec2Heuristic heuristic = new Vec2Heuristic();

            AStar<Vec2> algorithm = new AStar<>(region, heuristic);
            Stack<Vec2> path;

            Vec2 target = new Vec2(38, 5);

            if (target.equals(transform.position)) {
                java.lang.System.out.println("I have reached my target");

                return;
            }

            try {
                path = algorithm.findPath(transform.position, target);
            } catch (PathNotFoundException e) {
                java.lang.System.out.println("I wasn't able to figure out the path");

                return;
            }

            path.pop();
            Vec2 next = path.pop();
            java.lang.System.out.println("I'm moving from " + transform.position + " to " + next);
            transform.position = next;
        }
    }
}
