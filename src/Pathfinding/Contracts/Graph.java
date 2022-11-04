package Pathfinding.Contracts;

import java.util.Set;

public interface Graph<T> {
    Set<T> getConnections(T node);
}
