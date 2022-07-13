import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Random;

class FibonacciSearchTest {

    @ParameterizedTest
    @MethodSource("data")
    <T extends Comparable<T>> void searchTest(T what, T[] given, int expected) {
        final var actual = FibonacciSearch.fibonacciSearch(what, given);
        Assertions.assertEquals(expected, actual);
    }

    public static Arguments[] data() {
        final Integer[] ints = new Integer[]{0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150};
        return new Arguments[]{
                // // beyond left
                Arguments.of(-1, ints, -1),
                // // left bound
                Arguments.of(0, ints, 0),
                // exact
                Arguments.of(50, ints, 5),
                // tight left
                Arguments.of(90, ints, 9),
                // simple
                Arguments.of(100, ints, 10),
                // tight right
                Arguments.of(120, ints, 12),
                // right bound
                Arguments.of(150, ints, 15),
                // beyond bound
                Arguments.of(160, ints, -1),
                // not found
                Arguments.of(145, ints, -1),
                // empty
                Arguments.of(1, new Integer[]{}, -1),
                // single
                Arguments.of(0, new Integer[]{0}, 0),
                // pair
                Arguments.of(0, new Integer[]{0, 1}, 0),
                Arguments.of(1, new Integer[]{0, 1}, 1),
                // triple
                Arguments.of(0, new Integer[]{0, 1, 2}, 0),
                Arguments.of(1, new Integer[]{0, 1, 2}, 1),
                Arguments.of(2, new Integer[]{0, 1, 2}, 2),
                // quadro
                Arguments.of(0, new Integer[]{0, 1, 2, 3}, 0),
                Arguments.of(1, new Integer[]{0, 1, 2, 3}, 1),
                Arguments.of(2, new Integer[]{0, 1, 2, 3}, 2),
                Arguments.of(3, new Integer[]{0, 1, 2, 3}, 3),
        };
    }

    @ParameterizedTest
    // original values are not about logarithms definitely, oh those russians :(
    // @ValueSource(ints = {1500, 3500, 6000})
    @ValueSource(ints = {1024 * 1024, 2048 * 1024, 4096 * 1024, 8192 * 1024, 16384 * 1024, 32768 * 1024})
    void speedTest(int n) {
        Integer[] given = buildArray(n);
        final var t0 = System.nanoTime();
        FibonacciSearch.fibonacciSearch(n / 2, given);
        final var t1 = System.nanoTime();
        System.out.printf("N: %d, time: %f ms\n", n, (t1 - t0) / 1E6);
    }

    private Integer[] buildArray(int n) {
        final var random = new Random();
        final Integer[] array = new Integer[n];
        for (int i = 0, next = 0; i < n; ++i) {
            next += random.nextInt(4);
            array[i] = next;
        }
        Arrays.sort(array);
        return array;
    }

}
