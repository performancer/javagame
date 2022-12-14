package Game.Graphics.Systems;

import ECS.Contracts.Entity;
import ECS.Contracts.System;
import Game.Graphics.Components.RenderComponent;
import Game.World.Components.TransformComponent;
import Game.World.Vec2;

import java.util.Collection;

public class RenderSystem implements System {
    private Entity findFirstAt(Collection<Entity> entities, Vec2 position) {
        for (Entity entity : entities) {
            TransformComponent transform = entity.getComponent(TransformComponent.class);

            if (transform.position.equals(position)) {
                return entity;
            }
        }

        return null;
    }

    @Override
    public void update(Collection<Entity> entities) {
        java.lang.System.out.println();
        java.lang.System.out.println();
        java.lang.System.out.println();
        java.lang.System.out.println();
        java.lang.System.out.println();
        java.lang.System.out.println();
        java.lang.System.out.println();

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 40; x++) {
                Entity first = this.findFirstAt(entities, new Vec2(x, y));

                if (first != null) {
                    RenderComponent render = first.getComponent(RenderComponent.class);
                    java.lang.System.out.print(render.color + render.character + RenderComponent.RESET);

                    continue;
                }

                java.lang.System.out.print("   ");
            }

            java.lang.System.out.println();
        }

        java.lang.System.out.println("Printing world finished!");
    }
}
