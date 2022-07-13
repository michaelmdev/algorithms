import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class BinPacking {

    public static int[][] ffd(int[] items, int binSize) {

        TreeSet<Bin> bins = new TreeSet<>();

        Arrays.sort(items);
        outer:
        for (int i = items.length - 1; i >= 0; --i) {
            int item = items[i];
            for (Bin bin : bins) {
                if (item <= bin.free) {
                    bin.put(item);
                    continue outer;
                }
            }

            Bin bin = new Bin(binSize);
            bins.add(bin);
            bin.put(item);
        }

        return bins.stream()
                .map(bin -> bin.items.stream().mapToInt(x -> x).toArray())
                .toArray(int[][]::new);
    }

    static class Bin implements Comparable<Bin> {
        final List<Integer> items = new ArrayList<>();
        int free;

        private Bin(int size) {
            free = size;
        }


        public int put(int item) {
            items.add(item);
            return free -= item;
        }

        @Override
        public int compareTo(@NotNull Bin other) {
            return this.free - other.free;
        }

        @Override
        public String toString() {
            return items + " :" + free;
        }
    }
}
