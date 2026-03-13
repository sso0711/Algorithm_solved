import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 
 * 
 * 
 * 
 * */
public class Solution_1251_박소영 {
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
	static int N,ENum,E, ans;
	
	
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
			N = Integer.parseInt(br.readLine());
			X = new int[N];
			Y = new int[N];

			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				X[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				Y[i] = Integer.parseInt(st.nextToken());
			}
			
			double E = Double.parseDouble(br.readLine());
			
			makeSets(N);
			
			ENum = N*(N-1)/2;
			edgeList = new Edge[ENum];
			int idx = 0;
			for(int i=0; i<N; i++) {
				for(int j=i+1; j<N; j++) {
					if (i==j) continue;
					
					long dx = X[i] - X[j];
					long dy = Y[i] - Y[j];
					
					long weight = dx*dx + dy*dy; // 거리의 제곱

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
					if(count == N-1) break;
				}
			
			}
			
			System.out.printf("#%d %d\n",tcase,Math.round(result*E));
		}
	}

}
