package ECS.Contracts;

import java.util.Collection;

public interface ComponentManager {
    /**
     * Registers this type of component (unless already registered)
     */
    <T extends Component> ComponentManager register(Class<T> componentClass);

    /**
     * Get the signature of this component type that may be used for system to subscribe to
     */
    <T extends Component> int getSignatureIndex(Class<T> componentClass);

    /**
     * Binds the component into an entity
     */
    void bind(Entity entity, Component component);

    /**
     * Unbinds any component of this type from an entity
     */
    <T extends Component> void unbind(Entity entity, Class<T> componentClass);

    /**
     * Unbinds all the components from entity that it might possess
     */
    void unbindAll(Entity entity);

    /**
     * Gets this component of this type from the entity (if exists)
     */
    <T extends Component> T get(Entity entity, Class<T> componentClass);

    /**
     * Gets all the entities that possess a certain component type
     */
    <T extends Component> Collection<Entity> getEntities(Class<T> componentClass);
}
