package ECS;

import java.util.BitSet;

public class Signature {
    private final BitSet bitset = new BitSet();

    public boolean contains(Signature signature) {
        BitSet intersection = (BitSet) bitset.clone();
        intersection.and(signature.bitset);

        return intersection.equals(signature.bitset);
    }

    public void set(int i) {
        bitset.set(i);
    }

    @Override
    public int hashCode() {
        return bitset.hashCode();
    }
}
