public class Recursion {
    /**
     * @return an = n! / (2^n * 3 ^ (n+1))
     */
    public static double foo(int n) {
        return fac(n) / (double) (pow(2, n) * pow(3, n + 1));
    }

    /**
     * Extremely naive implementation, does not check overflow exception et al
     *
     * @param n - number
     * @return n!
     */
    static long pow(int base, int n) {
        assert base >= 0;
        assert n >= 0;
        return n > 0 ? pow(base, n - 1) * base : 1;
    }

    /**
     * Extremely naive implementation, does not check overflow exception et al
     *
     * @param n - number
     * @return n!
     */
    static long fac(int n) {
        assert n >= 0;
        return n > 0 ? fac(n - 1) * n : 1;
    }
}
