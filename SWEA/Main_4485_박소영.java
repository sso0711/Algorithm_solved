import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
 * 격자그래프에서 다익스트라를 적용하는 문제.
 * 
 * 주어지는 그래프가 인접행렬과 비슷해서 헷갈렸는데,
 * 인접행렬은 인덱스 i,j를 잇는 간선의 가중치가 저장되지만, 이 문제에서 주어지는건 
 * 
 * 1차 시도 : nextX, nextY 범위 체크 추가
 * 2차 시도 : 처음 비용이 0이 아니라 해당 map칸의 비용으로 시작!!
 * 
 * 
 * */

public class Main_4485_박소영 {
	
	static int N;
	static int[][] map;
	static int[][] minDist;
	static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
	
	public static void dijikstra(int startX, int startY) {
		// 우선순위 큐 : {비용, 노드X, 노드Y} 기준 MinHeap
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // 시작 노드 비용 0으로 설정 후 삽입
        minDist[startX][startY] = map[startX][startY]; // 2차 시도 보완
        pq.offer(new int[]{map[startX][startY], startX, startY});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int dist = cur[0]; // 현재까지의 비용
            int nowX  = cur[1]; // 현재 노드의 x좌표
            int nowY  = cur[2]; // 현재 노드의 y좌표

            // 이미 처리된 노드면 무시
            if (minDist[nowX][nowY] < dist) continue;

            // 인접 노드 -> 4방향 확인
            for (int d=0; d<4; d++) {
                int nextX = nowX + dx[d];
                int nextY = nowY + dy[d];
                if(nextX<0 || nextY<0 || nextX>=N || nextY>=N) continue; // 1차 시도 보완

                int cost = dist + map[nextX][nextY];
                

                // 더 짧은 경로 발견 시 갱신
                if (cost < minDist[nextX][nextY]) {
                    minDist[nextX][nextY] = cost;
                    pq.offer(new int[]{cost, nextX, nextY});
                }
            }
        }
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tcase=1;
		
		while(true) {
			N = Integer.parseInt(br.readLine());
			
			if (N==0) {
				break;
			} else {
				map = new int[N][N];
				minDist = new int[N][N]; // 출발지에서 자신으로의 최소거리(비용)
				
				for (int i=0; i<N; i++) {
					st = new StringTokenizer(br.readLine());
					for (int j=0; j<N; j++) {
						map[i][j] = Integer.parseInt(st.nextToken());
					}
				}
				
				// 전처리
				for(int i=0; i<N; i++) {
					Arrays.fill(minDist[i], Integer.MAX_VALUE);
				}

				dijikstra(0,0);
				
				System.out.printf("Problem %d: %d\n",tcase, minDist[N-1][N-1]);
				tcase++;
			}
	
			
		
		}
		
	}

}

