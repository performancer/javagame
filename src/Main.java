import ECS.*;
import ECS.Contracts.ComponentManager;
import ECS.Contracts.EntityManager;
import ECS.Contracts.SystemManager;
import Game.Graphics.Components.RenderComponent;
import Game.Graphics.Systems.RenderSystem;
import Game.World.Components.ScriptComponent;
import Game.World.Components.TransformComponent;
import Game.World.Systems.ScriptSystem;
import Game.World.Vec2;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        EntityManager entityManager = new DefaultEntityManager();
        ComponentManager componentManager = new DefaultComponentManager();
        SystemManager systemManager = new DefaultSystemManager();

        componentManager
                .register(TransformComponent.class)
                .register(ScriptComponent.class)
                .register(RenderComponent.class);
        systemManager
                .register(
                        new ScriptSystem(componentManager),
                        new SignatureBuilder(componentManager)
                                .add(ScriptComponent.class)
                                .add(TransformComponent.class)
                                .get()
                )
                .register(
                        new RenderSystem(),
                        new SignatureBuilder(componentManager)
                                .add(RenderComponent.class)
                                .add(TransformComponent.class)
                                .get()
                );
        entityManager.initialize(systemManager.getSignatures());

        generateMap(entityManager, componentManager);

        entityManager.register(new DefaultEntity(entityManager, componentManager))
                .addComponent(new TransformComponent(new Vec2(1, 5)))
                .addComponent(new ScriptComponent())
                .addComponent(new RenderComponent(RenderComponent.RED_BACKGROUND, " P "));
        /*
        entityManager.register(new DefaultEntity(entityManager, componentManager))
                .addComponent(new TransformComponent(new Vec2(38, 5)))
                .addComponent(new RenderComponent(RenderComponent.GREEN_BACKGROUND, " F "));
        */
        for(int i = 0; i < 100; i++) {
            systemManager.update(entityManager);
            TimeUnit.SECONDS.sleep(1);
        }
    }

    private static void generateMap(EntityManager entityManager, ComponentManager componentManager) {
        Random random = new Random();
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 40; x++) {
                boolean force = x == 0 || y == 0 || x == 39 || y == 9;

                if (!force && (x < 3 || x > 36)) {
                    continue;
                }

                if (force || random.nextDouble() < 0.33) {
                    entityManager.register(new DefaultEntity(entityManager, componentManager))
                            .addComponent(new TransformComponent(new Vec2(x, y)))
                            .addComponent(new RenderComponent(RenderComponent.BLUE_BACKGROUND, "   "));
                }
            }
        }
    }
}
