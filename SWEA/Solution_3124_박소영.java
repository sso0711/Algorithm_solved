
public class unionFind {
	static int[] parent;
	static int[] rank;
		
	public static void makeSet(int n) {
	    parent = new int[n];
	    rank = new int[n];

	    for(int i = 0; i < n; i++) {
	        parent[i] = i;   // 자기 자신이 부모
	        rank[i] = 0;
	    }
	}

	// path 압축 포함
	public static int findSet(int x) {
	    if(parent[x] != x) {
	        parent[x] = findSet(parent[x]);
	    }
	    return parent[x];
	}

	public static void union(int a, int b) {
	    int rootA = findSet(a);
	    int rootB = findSet(b);

	    if(rootA == rootB) return;

			// 트리 높이가 더 낮아지도록 union
	    if(rank[rootA] < rank[rootB]) {
	        parent[rootA] = rootB;
	    }
	    else if(rank[rootA] > rank[rootB]) {
	        parent[rootB] = rootA;
	    }
	    else {
	        parent[rootB] = rootA;
	        rank[rootA]++;
	    }
	}

	public static void main(String[] args) {
			
	}

}
