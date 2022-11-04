package Pathfinding.Contracts;

public interface Heuristic<T> {
    double computeCost(T from, T to);
}
