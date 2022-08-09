package ECS.Contracts;

public interface Entity {
    <T extends Component> T getComponent(Class<T> componentClass);

    Entity addComponent(Component component);

    <T extends Component> Entity removeComponent(Class<T> componentClass);

    Entity destroy();
}
