package Pathfinding;

public class Node <T> implements Comparable<Node<T>> {
    private final T value;
    private final Node<T> previous;
    private final double routeScore;
    private final double estimatedScore;

    Node(T current, Node<T> previous, double routeScore, double estimatedScore) {
        this.value = current;
        this.previous = previous;
        this.routeScore = routeScore;
        this.estimatedScore = estimatedScore;
    }

    public T getValue() {
        return this.value;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    public double getRouteScore() {
        return routeScore;
    }

    public double getEstimatedScore() {
        return estimatedScore;
    }

    @Override
    public int compareTo(Node other) {
        return Double.compare(this.estimatedScore, other.estimatedScore);
    }
}
