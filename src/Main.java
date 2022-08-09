import ECS.*;
import ECS.Contracts.ComponentManager;
import ECS.Contracts.EntityManager;
import ECS.Contracts.SystemManager;
import Graphics.Components.RenderComponent;
import Graphics.Systems.RenderSystem;
import World.Components.ScriptComponent;
import World.Components.TransformComponent;
import World.Systems.ScriptSystem;
import World.Vec2;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
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
                .addComponent(new RenderComponent(RenderComponent.ANSI_RED, 'x'));

        for(int i = 0; i < 100; i++) {
            systemManager.update(entityManager);
        }
    }

    private static void generateMap(EntityManager entityManager, ComponentManager componentManager) {
        Random random = new Random();
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 40; x++) {
                boolean force = x == 0 || y == 0 || x == 39 || y == 9;

                if (!force && x < 3) {
                    continue;
                }

                if (force || random.nextDouble() < 0.33) {
                    entityManager.register(new DefaultEntity(entityManager, componentManager))
                            .addComponent(new TransformComponent(new Vec2(x, y)))
                            .addComponent(new RenderComponent(RenderComponent.ANSI_BLUE, 'x'));
                }
            }
        }
    }
}
