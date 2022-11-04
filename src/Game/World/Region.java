package Game.World;

import Pathfinding.Contracts.Graph;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Region implements Graph<Vec2> {
    private final boolean[][] grid;

    public Region() {
        this.grid = new boolean[40][20];

        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 20; j++) {
                this.grid[i][j] = false;
            }
        }
    }

    private boolean withinBounds(Vec2 tile) {
        return tile.getX() >= 0 && tile.getY() >= 0 && tile.getX() < 40 && tile.getY() < 20;
    }

    private boolean isPassable(Vec2 tile) {
        return withinBounds(tile) && !grid[tile.getX()][tile.getY()];
    }

    public void setImpassable(Vec2 tile) {
        this.grid[tile.getX()][tile.getY()] = true;
    }

    @Override
    public Set<Vec2> getConnections(Vec2 node) {
        Set<Vec2> set = new LinkedHashSet<>();

        set.add(new Vec2(node.getX() + 1, node.getY() + 1));
        set.add(new Vec2(node.getX() + 1, node.getY()));
        set.add(new Vec2(node.getX() + 1, node.getY() - 1));

        set.add(new Vec2(node.getX(), node.getY() + 1));
        set.add(new Vec2(node.getX(), node.getY() - 1));

        set.add(new Vec2(node.getX() - 1, node.getY() + 1));
        set.add(new Vec2(node.getX() - 1, node.getY()));
        set.add(new Vec2(node.getX() - 1, node.getY() - 1));


        set = set.stream().filter(this::isPassable).collect(Collectors.toSet());

        return set;
    }
}
