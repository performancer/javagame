package Pathfinding;

import Pathfinding.Contracts.Graph;
import Pathfinding.Contracts.Heuristic;
import Pathfinding.Contracts.PathfindingAlgorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

public class AStar<T> implements PathfindingAlgorithm<T> {
    private final Graph<T> graph;
    private final Heuristic<T> heuristic;

    public AStar(Graph<T> graph, Heuristic<T> heuristic) {
        this.graph = graph;
        this.heuristic = heuristic;
    }

    private Stack<T> rewind(Node<T> current) {
        Stack<T> route = new Stack<>();

        do {
            route.add(current.getValue());
            current = current.getPrevious();
        } while (current != null);



        return route;
    }

    public Stack<T> findPath(T start, T end) throws PathNotFoundException {
        PriorityQueue<Node<T>> openNodes = new PriorityQueue<>();
        Map<Integer, Node<T>> allNodes = new HashMap<>();

        Node<T> startNode = new Node<>(start, null, 0d, this.heuristic.computeCost(start, end));
        openNodes.add(startNode);
        allNodes.put(start.hashCode(), startNode);

        while(!openNodes.isEmpty()) {
            Node<T> current = openNodes.poll();
            System.out.println(current.getValue());
            if (current.getValue().equals(end)) {
                return this.rewind(current);
            }

            for (T connection : graph.getConnections(current.getValue())) {
                Node<T> connectionNode = allNodes.get(connection.hashCode());
                double newScore = current.getRouteScore() + this.heuristic.computeCost(current.getValue(), connection);

                if (connectionNode == null || connectionNode.getRouteScore() > newScore) {
                    double estimated = newScore + this.heuristic.computeCost(connection, end);
                    connectionNode = new Node<>(connection, current, newScore, estimated);
                    openNodes.add(connectionNode);
                    allNodes.put(connection.hashCode(), connectionNode);
                }
            }
        }

        throw new PathNotFoundException();
    }
}
