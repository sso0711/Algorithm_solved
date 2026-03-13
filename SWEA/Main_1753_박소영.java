import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main_1753_박소영 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());
		
		List<int[]>[] adjList = new ArrayList[V+1];
		int[] minDist = new int[V+1]; // 출발지에서 자신으로의 최소거리(비용)
		
		// 인접리스트 생성
		for (int i = 1; i <= V; i++) {
		    adjList[i] = new ArrayList<>();
		}
		
		// 인접리스트 초기화
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from].add(new int[] {to,weight});		
		}
		
		// 전처리
		Arrays.fill(minDist, Integer.MAX_VALUE);
		minDist[start] = 0;
		// 우선순위큐 생성
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
		pq.offer(new int[] {start,0}); // 시작정점
		
		
		// step1 : 출발지에서 가까운 정점으로 탐색되지 않은 정점 중에
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
            
            // 이미 처리된 노드면 무시
            if (minDist[cur[0]] < cur[1]) continue;
            
			// step2 : 찾은 cur정점을 경유해서 갈 수 있는 다른 미탐색 정점의 최소비용 비교하여 갱신
            for (int[] next : adjList[cur[0]]) {
                int nextNode = next[0];
                int cost     = cur[1] + next[1];

                // 더 짧은 경로 발견 시 갱신
                if (cost < minDist[nextNode]) {
                    minDist[nextNode] = cost;
                    pq.offer(new int[]{nextNode, cost});
                }
            }
		}
		
//		System.out.println(Arrays.toString(minDist));
		
        for (int i = 1; i <= V; i++) {
            if (minDist[i] == Integer.MAX_VALUE) {
            	System.out.println("INF");
            } else {
            	System.out.println(minDist[i]);
            }
        }
	}

}
