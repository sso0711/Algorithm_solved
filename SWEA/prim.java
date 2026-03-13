import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class prim {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		List<int[]>[] adjList = new ArrayList[V];
		boolean[] visited = new boolean[V]; // 트리에 포함 여부
		int[] minEdge = new int[V]; // 트리 정점, 자신과의 간선 비용의 최솟값
		
		// 인접리스트 생성
		for (int i = 0; i < V; i++) {
		    adjList[i] = new ArrayList<>();
		}
		
		// 인접리스트 초기화
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from].add(new int[] {to,weight});
			adjList[to].add(new int[] {from,weight});		
		}
		
		// 전처리1 : minEdge는 최소값 유저이므로 큰 값으로 초기화
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		// 전처리2 : 임의의 시작 정점의 minEdge 간선비용 0으로 만듦 : 첫번째 반복에서 선택될 트리의 시작정점을 만들기 위해
		minEdge[0] = 0;
		
		int result = 0; // MST비용
		int c = 0;
		
		// 우선순위큐 생성
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
		pq.offer(new int[] {0,0}); // 시작정점
				
				
		 // step1: minEdge가 가장 작은 비트리 정점 선택
		for(c = 0; c < V; c++) {
			int min = Integer.MAX_VALUE;
			int minVertex = -1;
			
			while(!pq.isEmpty()) {
				int[] cur = pq.poll();
				if(!visited[cur[0]]) {
					minVertex = cur[0];
					min = cur[1];
					break;
				}
			}
			
			if(minVertex == -1) break;
			visited[minVertex] = true;
			result += min;
			
			
			// 인접리스트 탐색
			for(int[] temp : adjList[minVertex]) {
				int to = temp[0];
				int weight = temp[1];
				
				if(!visited[to] && minEdge[to] > weight) {
					minEdge[to] = weight;
					pq.offer(new int[] {to, weight});
				}
			}
			
		}
		
		System.out.println( c==V ? result : -1);
	}

}
