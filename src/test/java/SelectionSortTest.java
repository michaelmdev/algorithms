import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

class SelectionSortTest {

    // more aggressive but precise
    // @ValueSource(ints = {1500_00, 3500_00, 6000_00})
    @ParameterizedTest
    @ValueSource(ints = {1500_0, 3500_0, 6000_0})
    void speedTest(int n) {
        Integer[] given = buildArray(n);
        final var t0 = System.nanoTime();
        SelectionSort.sort(given);
        final var t1 = System.nanoTime();
        System.out.printf("N: %d, time: %f ms\n", n, (t1 - t0) / 1E6);
    }

    private Integer[] buildArray(int n) {
        final var random = new Random();
        final Integer[] array = new Integer[n];
        for (int i = 0; i < n; ++i) {
            array[i] = random.nextInt();
        }
        return array;
    }

    @ParameterizedTest
    @MethodSource("data")
    <T extends Comparable<T>>
    void sortTest(T[] actual, T[] expected) {
        SelectionSort.sort(actual);
        Assertions.assertArrayEquals(expected, actual);
    }

    public static Arguments[] data() {
        return new Arguments[]{
                // simple
                Arguments.of(
                        new Integer[]{8, 5, 2, 6, 9, 3, 1, 4, 0, 7},
                        new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
                ),
                // duplicates
                Arguments.of(
                        new Integer[]{8, 5, 5, 5, 5, 5, 1, 4, 0, 7},
                        new Integer[]{0, 1, 4, 5, 5, 5, 5, 5, 7, 8}
                ),
                // perfect case
                Arguments.of(
                        new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                        new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
                ),
                // worst case
                Arguments.of(
                        new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0},
                        new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
                )
        };
    }


}
