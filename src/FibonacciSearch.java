@SuppressWarnings("ForLoopReplaceableByForEach")
public class FibonacciSearch {
    /**
     * @param x   - искомый ключ
     * @param a   - массив ключей
     * @param <T> - тип ключа
     * @return - индекс ключас, или -1 если не нейден
     */
    public static <T extends Comparable<? super T>> int fibonacciSearch(T x, T[] a) {
        // left & right beyond bounds
        int p = -1;
        int q = a.length;
        final int k = a.length;
        // index mapping
        final int[] fibs = buildFibonacciNumbersUntil(a.length);


        while (p + 1 < q) {
            for (int i = 0, n = fibs.length; i < n; ++i) {
                final int fi = p + fibs[i] < k ? p + fibs[i] : k - 1;
                final int compare = x.compareTo(a[fi]);
                if (compare < 0) {
                    q = fi;
                    break;
                } else if (compare == 0) {
                    return fi;
                } else {
                    p = fi;
                }
            }
        }

        return -1;
    }


    /**
     * @param n - max Fibonacci number in sequence required
     * @return array of Fibonacci numbers util n, e.g. [1, 2, 3, 5, 8, 13, ... ,n]
     */
    private static int[] buildFibonacciNumbersUntil(int n) {
        switch (n) {
            case 0:
                return new int[]{1};
            case 1:
                return new int[]{1, 2};
            default:
                final int[] fibs = new int[n];
                fibs[0] = 1;
                fibs[1] = 2;
                int i;
                for (i = 2; i < n && fibs[i - 1] <= n; ++i) {
                    fibs[i] = fibs[i - 1] + fibs[i - 2];
                }
                final int[] result = new int[i];
                System.arraycopy(fibs, 0, result, 0, i);
                return result;
        }
    }
}
