import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1247_박소영 {
	static class Edge implements Comparable<Edge> {
		int start,end;
		long weight;
		
		public Edge(int start, int end, long weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight);
		}
	}
	
	static Edge[] edgeList;
	static int[] parents, ranks;
	static int[] X,Y;
	static int N,V,ENum ;
	
	
	static void makeSets(int v) {
		parents = new int[v];
		ranks = new int[v];
		for (int i=0; i<v; i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int a) {
		if(parents[a] == a) return a;
		else {
			 parents[a] = findSet(parents[a]);
		}
		return parents[a];
	}
	
	public static boolean union(int a, int b) {
	    int rootA = findSet(a);
	    int rootB = findSet(b);

	    if(rootA == rootB) return false;

		// 트리 높이가 더 낮아지도록 union
	    if(ranks[rootA] < ranks[rootB]) {
	        parents[rootA] = rootB;
	    }
	    else if(ranks[rootA] > ranks[rootB]) {
	        parents[rootB] = rootA;
	    }
	    else {
	        parents[rootB] = rootA;
	        ranks[rootA]++;
	    }
	    return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int tcase=1; tcase<=T; tcase++) {
			N = Integer.parseInt(br.readLine()); // 고객의 수
			V = N + 2; // 회사 + 고객 + 집
			X = new int[V];
			Y = new int[V];
			
			st = new StringTokenizer(br.readLine());
			X[0] = Integer.parseInt(st.nextToken());
			Y[0] = Integer.parseInt(st.nextToken());
			X[1] = Integer.parseInt(st.nextToken());
			Y[1] = Integer.parseInt(st.nextToken());
			
			for(int i=2; i<=N+1; i++) {
				X[i] = Integer.parseInt(st.nextToken());
				Y[i] = Integer.parseInt(st.nextToken());
			}
			
			makeSets(V);
			
			ENum = V*(V-1) / 2;
			edgeList = new Edge[ENum];
			int idx = 0;
			for(int i=0; i<V; i++) {
				for(int j=i+1; j<V; j++) {
					if (i==j) continue;
					
					long dx = X[i] - X[j];
					long dy = Y[i] - Y[j];
					
					long weight = Math.abs(dx) + Math.abs(dy); // 맨해튼 거리

					edgeList[idx++] = new Edge(i, j, weight);
				}
			}
			
			Arrays.sort(edgeList); // 가중치 기준 오름차순 정렬
			
			
			int count = 0;
			long result = 0; // 선택 간선 수, 누적간선비용
			for (Edge edge : edgeList) {
				if(union(edge.start, edge.end)) { // 선택한 간선이 싸이클을 발생시키지 X
					count++;
					result += edge.weight;
					if(count == V-1) break;
				}
			
			}
			
			
			
			System.out.printf("#%d %d\n",tcase, result);
		}
	}

}
