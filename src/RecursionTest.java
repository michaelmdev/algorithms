import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RecursionTest {

    @ParameterizedTest
    @MethodSource("fac")
    void fooTest(int n, double expected) {
        double actual = Recursion.foo(n);
        Assertions.assertEquals(expected, actual, 0.001);
    }

    static Arguments[] fac() {
        return new Arguments[]{
                Arguments.of(0, 0.3333),
                Arguments.of(1, 0.0555),
                Arguments.of(2, 0.0185),
                Arguments.of(3, 0.0092),
                Arguments.of(10, 0.02000),
        };
    }

    @ParameterizedTest
    @MethodSource("facData")
    void facTest(int n, long expected) {
        long actual = Recursion.fac(n);
        Assertions.assertEquals(expected, actual);
    }

    static Arguments[] facData() {
        return new Arguments[]{
                Arguments.of(0, 1),
                Arguments.of(1, 1),
                Arguments.of(2, 2),
                Arguments.of(3, 6),
                Arguments.of(4, 24),
                Arguments.of(5, 120),
                Arguments.of(10, 3628800),
        };
    }

    @ParameterizedTest
    @MethodSource("powData")
    void powTest(int base, int n, long expected) {
        long actual = Recursion.pow(base, n);
        Assertions.assertEquals(expected, actual);
    }

    static Arguments[] powData() {
        return new Arguments[]{
                Arguments.of(0, 0, 1),
                Arguments.of(1, 0, 1),
                Arguments.of(2, 0, 1),
                Arguments.of(2, 1, 2),
                Arguments.of(2, 2, 4),
                Arguments.of(2, 3, 8),
                Arguments.of(2, 4, 16),
                Arguments.of(3, 0, 1),
                Arguments.of(3, 1, 3),
                Arguments.of(3, 2, 9),
                Arguments.of(3, 3, 27),
                Arguments.of(3, 4, 81),
        };
    }

}
