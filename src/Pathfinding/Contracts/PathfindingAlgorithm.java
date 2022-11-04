package Pathfinding.Contracts;

import Pathfinding.PathNotFoundException;

import java.util.Stack;

public interface PathfindingAlgorithm<T> {
    Stack<T> findPath(T start, T end) throws PathNotFoundException;
}
