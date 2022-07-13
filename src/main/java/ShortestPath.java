import java.util.*;
import java.util.stream.Collectors;

public class ShortestPath {

    /**
     * @param edges - массив рёбер взвешенного направленного графа
     * @param from  - исходная вершина
     * @return - кратчайший путь или null если невозможно попасть в целевую вершину
     */
    public static Set<Edge> dynamic(final Edge[] edges,
                                    final int from,
                                    final int to) {
        assert edges != null;
        assert edges.length > 0;

        // карта from -> to[]
        final var map = Arrays.stream(edges)
                .collect(Collectors.groupingBy(edge -> edge.from));

        List<Variant> wave = new ArrayList<>();
        wave.add(Variant.from(from));
        Optional<Variant> winner = Optional.empty();

        while (wave.stream().anyMatch(variant -> map.get(variant.last) != null)) {
            wave = wave.stream()
                    .map(variant -> map.getOrDefault(variant.last, Collections.emptyList())
                            .stream().map(variant::fork).collect(Collectors.toList())
                    )
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());

            final var min = wave.stream()
                    .filter(variant -> variant.last == to)
                    .min(Comparator.comparingInt(variant -> variant.s));

            if (min.isPresent() && (winner.isEmpty() || min.get().getS() < winner.get().getS())) {
                winner = min;
            }
        }

        return winner.map(Variant::getPath).orElse(Collections.emptySet());
    }

    static final class Variant {
        // ссылки на вершины этого пути
        final Set<Edge> path;
        // крайний
        final int last;
        // длина пути этого варианта - сравниваемое значние
        final int s;

        private Variant(Set<Edge> path, int vertex, int s) {
            this.path = path;
            this.last = vertex;
            this.s = s;
        }

        public Variant(int vertex) {
            this.path = new LinkedHashSet<>();
            this.last = vertex;
            this.s = 0;
        }

        // исходный пустой путь
        private static Variant from(int vertex) {
            return new Variant(vertex);
        }

        public Variant fork(Edge edge) {
            final var set = new LinkedHashSet<>(this.path);
            set.add(edge);
            return new Variant(set, edge.to, this.s + edge.weight);
        }

        public Set<Edge> getPath() {
            return path;
        }

        public int getLast() {
            return last;
        }

        public int getS() {
            return s;
        }

        @Override
        public String toString() {
            return path.toString() + "[" + s + "]" ;
        }
    }
}


