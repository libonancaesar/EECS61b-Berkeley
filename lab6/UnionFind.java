public class UnionFind {

    // TODO - Add instance variables?
    private  int[] parentArray;
    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parentArray = new int[n]; /* start to allocate stuff*/
        for (int i = 0; i < n; i++) {
            parentArray[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex >= parentArray.length || vertex < 0) {
            throw new IllegalArgumentException(vertex + " is not an illegal vertex");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        return -parentArray[find(v1)];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        // TODO
        return parentArray[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        int rootV1 = find(v1);
        int rootV2 = find(v2);
        int sizeV1 = -parentArray[rootV1];
        int sizeV2 = -parentArray[rootV2];
        if (!connected(v1, v2)) {
            if (sizeV1 > sizeV2) {
                parentArray[rootV2] = rootV1;
                parentArray[rootV1] = -(sizeV1 + sizeV2);
            } else {
                parentArray[rootV1] = rootV2;
                parentArray[rootV2] = -(sizeV1 + sizeV2);
            }
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        int tempVertex = vertex;
        while(parent(tempVertex) >= 0) {
            tempVertex = parent(tempVertex);
        }
        return tempVertex;
    }



    public void unionWithPathCompression(int v1, int v2) {
        int rootV1 = find(v1);
        int rootV2 = find(v2);
        int sizeV1 = -parentArray[rootV1];
        int sizeV2 = -parentArray[rootV2];
        int newRoot = rootV1;
        if (!connected(v1, v2)) {
            if (sizeV1 == sizeV2) {
                parentArray[rootV2] = rootV1;
                parentArray[rootV1] = -(sizeV1 + sizeV2);
            } else {
                parentArray[rootV1] = rootV2;
                parentArray[rootV2] = -(sizeV1 + sizeV2);
                newRoot = rootV2;
            }
        }

        upDateParentRoot(v1, newRoot);
        upDateParentRoot(v2, newRoot);
    }

    private void upDateParentRoot(int v, int p) {
        while (parentArray[v] >= 0) {
            int temp = parent(v);
            parentArray[v] = p;
            v = temp;
        }
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10);
        uf.union(0,1);
        uf.union(1,1);
        uf.union(4,5);
        uf.union(3,5);
        uf.union(3,1);
        uf.unionWithPathCompression(8,5);
        System.out.println(uf.parent(8));
    }
}
