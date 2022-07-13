import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class Kruskal {
    static Set<Edge> mst(Edge[] edges) {
        Arrays.sort(edges);
        DisjointSetUnion dsu = new DisjointSetUnion(edges.length);
        Set<Edge> mst = new LinkedHashSet<>();
        for (Edge edge : edges) {
            if (dsu.union(edge.from, edge.to)) {
                mst.add(edge);
            }
        }
        return mst;
    }
}

class DisjointSetUnion {
    int[] set;
    int[] rnk;

    public DisjointSetUnion(int size) {
        set = new int[size];
        rnk = new int[size];
        for (int i = 0; i < size; i++)
            set[i] = i;
    }

    public boolean union(int u, int v) {
        if ((u = set(u)) == (v = set(v))) {
            return false;
        }
        if (rnk[u] < rnk[v]) {
            set[u] = v;
        } else {
            set[v] = u;
            if (rnk[u] == rnk[v]) {
                rnk[u]++;
            }
        }
        return true;
    }

    private int set(int x) {
        return x == set[x] ? x : (set[x] = set(set[x]));
    }
}

