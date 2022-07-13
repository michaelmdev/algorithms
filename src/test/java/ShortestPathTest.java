import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;

class ShortestPathTest {

    @ParameterizedTest
    @MethodSource("data")
    void dynamicTest(Edge[] given, int from, int to, int expectedS, Set<Edge> expected) {
        System.out.println("N=" + given.length);
        final var actual = ShortestPath.dynamic(given, from, to);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expectedS, actual.stream()
                .mapToInt(e -> e.weight).sum());
        System.out.println("Solution:");
        actual.forEach(System.out::println);
        Assertions.assertEquals(expected, actual);
    }

    public static Arguments[] data() {
        // граф из практикума
        final var graph = new Edge[]{
                new Edge(1, 4, 2),
                new Edge(1, 3, 4),
                new Edge(1, 2, 3),

                new Edge(4, 5, 5),
                new Edge(4, 6, 2),

                new Edge(3, 6, 6),

                new Edge(2, 6, 3),

                new Edge(6, 5, 1),
                new Edge(6, 7, 8),
                new Edge(6, 8, 7),

                new Edge(5, 7, 6),
                new Edge(5, 9, 12),
                new Edge(7, 10, 4),

                new Edge(8, 10, 3),

                new Edge(9, 8, 6),
                new Edge(9, 10, 11),
        };

        // граф задания
        final var problem = new Edge[]{
                new Edge(1, 2, 8),
                new Edge(1, 3, 3),

                new Edge(2, 4, 1),
                new Edge(2, 5, 5),

                new Edge(3, 5, 4),
                new Edge(3, 6, 12),

                new Edge(4, 7, 6),

                new Edge(5, 4, 16),
                new Edge(5, 7, 12),
                new Edge(5, 6, 15),

                new Edge(6, 7, 7),
        };

        return new Arguments[]{
                // примр из практикума
                Arguments.of(graph, 1, 10, 14, Set.of(
                        new Edge(1, 4, 2),
                        new Edge(4, 6, 2),
                        new Edge(6, 8, 7),
                        new Edge(8, 10, 3)
                )),

                // пример из задания
                Arguments.of(problem, 1, 7, 15, Set.of(
                        new Edge(1, 2, 8),
                        new Edge(2, 4, 1),
                        new Edge(4, 7, 6)
                )),
        };
    }
}
