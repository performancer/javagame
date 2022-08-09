package World;

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

    public boolean equals(Vec2 point) {
        return this.x == point.x && this.y == point.y;
    }

    @Override
    public String toString() {
        return "{" + this.x + ", " + this.y + "}";
    }
}
