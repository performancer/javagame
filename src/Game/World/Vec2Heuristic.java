package Game.World;

import Pathfinding.Contracts.Heuristic;

public class Vec2Heuristic implements Heuristic<Vec2> {
    @Override
    public double computeCost(Vec2 from, Vec2 to) {
        return Math.abs(from.getX() - to.getX()) + Math.abs(from.getY() - to.getY());
    }
}
