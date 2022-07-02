import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;

class KruskalTest {

    @ParameterizedTest
    @MethodSource("data")
    void kruskalTest(Edge[] given, Set<Edge> expected) {
        System.out.println("N=" + given.length);
        var actual = Kruskal.mst(given);
        System.out.println("Solution:");
        actual.forEach(System.out::println);
        Assertions.assertEquals(expected, actual);
    }

    static Arguments[] data() {
        return new Arguments[]{
                // Пример из лабораторного практикума
                Arguments.of(
                        new Edge[]{
                                new Edge(1, 3, 2),
                                new Edge(1, 2, 1),
                                new Edge(1, 5, 10),
                                new Edge(2, 5, 6),
                                new Edge(3, 5, 7),
                                new Edge(5, 4, 11),
                                new Edge(2, 4, 3),
                                new Edge(3, 4, 4),
                        },
                        Set.of(
                                new Edge(1, 2, 1),
                                new Edge(1, 3, 2),
                                new Edge(2, 4, 3),
                                new Edge(2, 5, 6)
                        )
                ),
                // Граф из задания вариант 4
                Arguments.of(
                        new Edge[]{
                                new Edge(2, 1, 14),
                                new Edge(2, 3, 5),
                                new Edge(2, 6, 2),
                                new Edge(2, 8, 9),
                                new Edge(2, 4, 10),
                                new Edge(2, 5, 2),

                                new Edge(1, 6, 8),
                                new Edge(3, 8, 11),

                                new Edge(7, 6, 5),
                                new Edge(7, 8, 7),
                                new Edge(7, 4, 5),
                                new Edge(7, 5, 8),

                                new Edge(6, 4, 6),
                                new Edge(4, 5, 3),
                                new Edge(5, 8, 1),
                        },
                        Set.of(
                                new Edge(5, 8, 1),
                                new Edge(2, 6, 2),
                                new Edge(2, 5, 2),
                                new Edge(4, 5, 3),
                                new Edge(2, 3, 5),
                                new Edge(7, 6, 5),
                                new Edge(1, 6, 8)
                        )
                )
        };
    }
}
