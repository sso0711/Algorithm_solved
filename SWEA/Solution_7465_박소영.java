import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_7465_박소영 {
	static int N, M, ans;
	static int[] parent;
	static int[] rank;
		
	public static void makeSet(int n) {
	    parent = new int[n+1];
	    rank = new int[n+1];

	    for(int i = 1; i <= n; i++) {
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
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        for(int tcase=1; tcase<=T; tcase++) {
	        st = new StringTokenizer(br.readLine());
	        N = Integer.parseInt(st.nextToken());
	        M = Integer.parseInt(st.nextToken());
	        makeSet(N);
	        
	        for(int m=0; m<M; m++) {
	        	st = new StringTokenizer(br.readLine());
	        	int a = Integer.parseInt(st.nextToken());
	        	int b = Integer.parseInt(st.nextToken());
	        	union(a,b);
	        }
	        
	        boolean[] check = new boolean[N+1];
	        ans = 0;

	        for(int i=1; i<=N; i++) {
	            int root = findSet(i);

	            if(!check[root]) {
	                check[root] = true;
	                ans++;
	            }
	        }

	        System.out.printf("#%d %d\n", tcase, ans);
	        
        }
        
		
	}

}
