public final class Edge implements Comparable<Edge> {
    // номер вершины "из"
    final int from;
    // номер вершины "в"
    final int to;
    // вес ребра
    final int weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge edge) {
        if (weight != edge.weight) return weight < edge.weight ? -1 : 1;
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;

        Edge edge = (Edge) o;

        if (from != edge.from) return false;
        if (to != edge.to) return false;
        return weight == edge.weight;
    }

    @Override
    public int hashCode() {
        int result = from;
        result = 31 * result + to;
        result = 31 * result + weight;
        return result;
    }

    @Override
    public String toString() {
        return "(" + from + "->" + to + " :" + weight + ")" ;
    }
}
