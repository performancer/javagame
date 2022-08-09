package ECS;

import ECS.Contracts.Component;
import ECS.Contracts.ComponentManager;
import ECS.Contracts.Entity;
import ECS.Contracts.EntityManager;

public class DefaultEntity implements Entity {
    private final EntityManager entityManager;
    private final ComponentManager componentManager;

    public DefaultEntity(EntityManager entityManager, ComponentManager componentManager) {
        this.entityManager = entityManager;
        this.componentManager = componentManager;
    }

    @Override
    public <T extends Component> T getComponent(Class<T> componentClass) {
        return this.componentManager.get(this, componentClass);
    }

    @Override
    public Entity addComponent(Component component) {
        this.componentManager.bind(this, component);
        int index = this.componentManager.getSignatureIndex(component.getClass());
        this.entityManager.subscribe(this, index);

        return this;
    }

    @Override
    public <T extends Component> Entity removeComponent(Class<T> componentClass) {
        this.componentManager.unbind(this, componentClass);

        return this;
    }

    @Override
    public Entity destroy() {
        this.entityManager.unregister(this);
        this.componentManager.unbindAll(this);

        return this;
    }
}
