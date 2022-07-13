import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;

class BinPackingTest {

    @ParameterizedTest
    @MethodSource("data")
    void ffdTest(int[] items, int binSize, int[][] expected) {
        System.out.printf("N=%d\nitems=%s\nbin=%d\n\n", items.length, Arrays.toString(items), binSize);

        int[][] actual = BinPacking.ffd(items, binSize);
        Assertions.assertArrayEquals(expected, actual);
        for (int i = 0, length = expected.length; i < length; i++) {
            Assertions.assertArrayEquals(expected[i], actual[i]);
        }

        // вывод
        System.out.println("Solution:");
        for (int[] ints : actual) {
            int total = Arrays.stream(ints).sum();
            System.out.printf("%s : total = %d, free = %d\n", Arrays.toString(ints), total, binSize - total);
        }
        System.out.println("Bins: " + actual.length);

    }

    public static Arguments[] data() {
        return new Arguments[]{
                Arguments.of(
                        new int[]{1, 2, 3, 4}, 5,
                        new int[][]{{4, 1}, {3, 2}}
                ),
                Arguments.of(
                        new int[]{1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4,}, 6,
                        new int[][]{{4, 2}, {4, 2}, {4, 2}, {3, 3}, {3, 1, 1, 1}}
                ),
                Arguments.of(
                        new int[]{11, 2, 15, 5, 6, 17, 7}, 20,
                        new int[][]{{17, 2}, {15, 5}, {11, 7}, {6}}
                )
        };
    }

}
