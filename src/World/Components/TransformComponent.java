package World.Components;

import ECS.Contracts.Component;
import World.Vec2;

public class TransformComponent implements Component {
    public Vec2 position;

    public TransformComponent(Vec2 position) {
        this.position = position;
    }
}
