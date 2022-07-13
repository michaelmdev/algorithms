public class SelectionSort {
    /**
     * In-place comparison sorting.
     *
     * @param array массив элементов для сортировк
     * @param <T>   тип элементов
     */
    public static <T extends Comparable<? super T>> void sort(T[] array) {
        T buf;
        for (int j = 0, k = array.length - 1; j < k; ++j) {
            int min = j;
            for (int i = j + 1, n = array.length; i < n; ++i) {
                if (array[i].compareTo(array[min]) < 0) {
                    min = i;
                }
            }
            buf = array[min];
            array[min] = array[j];
            array[j] = buf;
        }
    }
}

