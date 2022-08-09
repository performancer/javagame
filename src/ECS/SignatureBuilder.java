package ECS;

import ECS.Contracts.Component;
import ECS.Contracts.ComponentManager;

public class SignatureBuilder {
    private final ComponentManager componentManager;
    private final Signature signature = new Signature();

    public SignatureBuilder(ComponentManager componentManager) {
        this.componentManager = componentManager;
    }

    public <T extends Component> SignatureBuilder add(Class<T> componentClass) {
        signature.set(this.componentManager.getSignatureIndex(componentClass));

        return this;
    }

    public Signature get() {
        return this.signature;
    }
}
