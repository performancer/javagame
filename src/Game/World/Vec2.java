package Game.World;

public class Vec2 {
    private final int x;
    private final int y;

    public Vec2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Vec2 v)) {
            return false;
        }

        return this.x == v.x && this.y == v.y;
    }

    @Override
    public String toString() {
        return "{" + this.x + ", " + this.y + "}";
    }

    @Override
    public int hashCode() {
        return this.x * 31 + y;
    }
}
