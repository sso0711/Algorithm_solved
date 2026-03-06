import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
/* 
 * 
 * 최단경로길이를 구해야 하므로, dfs가 아닌 bfs로 탐색을 진행하여야 한다.
 * 뚫 / 안뚫었을때의 최단경로를 각각 다른 함수와 다른 visited를 만들어 구하고, 둘 중 더 작은 값을 답으로 출력했다.
 * 
 * 1차 시도 :
 * bfs2에서 visited2를 2차원 배열로 사용했는데, isBroke 상태 구분이 없으면 같은 칸 (1,0)에 도달할 때 아래와 같은 문제 발생
 * 벽을 아직 안 부순 상태로 방문 → visited2[1][0] = true
 * 나중에 벽을 부순 상태로 같은 칸에 오려고 하면 → visited2[1][0]이 이미 true라 스킵됨
 * 
 * => visited[isBroke][row][col] 2차원 -> 3차원으로 변경 
 *  (아이디어가 떠오르지 않아서 검색을 통해 3차원을 써야한다는 사실을 파악)
 * 
 * 
 * */

public class Main_2206_박소영 {
	static int N,M,noBreakAns, breakAns, ans;
	static boolean[][] visited1;
	static boolean[][][] visited2;
	static byte[][] map;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	
	
	public static class Node {
        int row, col, dist, isBroke;
        
        public Node(int row, int col, int dist, int isBroke) {
            this.row = row;
            this.col = col;
            this.dist = dist;
            this.isBroke = isBroke;
        }
    }
	
	
	// 안 뚫었을 때 최단거리
	public static void bfs1(int x, int y, int distance, int isBroke) {
		Queue<Node> q = new ArrayDeque<Node>();
		q.offer(new Node(x,y,distance,isBroke));
		visited1[x][y] = true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			int cx = cur.row;
			int cy = cur.col;
			
			if(cx==N-1 && cy==M-1) {
				noBreakAns = cur.dist;
				return;
			}
			
			for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny]==1) continue;
                if (visited1[nx][ny]) continue;
                
                q.offer(new Node(nx,ny,cur.dist+1,isBroke));
	            visited1[nx][ny] = true;
            }
		}
		// 도착지까지 갈 수 없는 경우
		noBreakAns = -1;
	}
	
	
	// 뚫었을 때 최단거리
	public static void bfs2(int x, int y, int distance, int isBroke) {
		Queue<Node> q = new ArrayDeque<Node>();
		q.offer(new Node(x,y,distance, isBroke));
		visited2[isBroke][x][y] = true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			int cx = cur.row;
			int cy = cur.col;
			
			// 뚫었는데도/아직 안뚫었는데 경로가 더 길다? 튀튀
			if(noBreakAns != -1 && cur.dist >= noBreakAns) continue;
			// 도착지점 도착
			if(cx==N-1 && cy==M-1) {
				breakAns = cur.dist;
				return;
			}
			
			for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (visited2[cur.isBroke][nx][ny]) continue;
                
                // 뚫었는데 또막히면 해당경로 탐색 중단
                if(map[nx][ny]==1 && cur.isBroke==1) continue;
                
                // 길이 열려있으면 isBroke여부 상관없이 탐색 진행
                if(map[nx][ny]==0) {
	                q.offer(new Node(nx,ny,cur.dist+1,cur.isBroke)); // 2차 시도 보완
		            visited2[cur.isBroke][nx][ny] = true;
                } 
                // 아직 안뚫었으면 뚫기
                else if(map[nx][ny]==1 && cur.isBroke==0){
                	// map[nx][ny] = 0;
                	q.offer(new Node(nx,ny,cur.dist+1,1));
		            visited2[1][nx][ny] = true;
                }
            }
			
		}
		// while문 안에서 if(cx==N-1 && cy==M-1) return; 을 하지 못했다는 건 경로가 없음을 의미
		breakAns = -1;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		noBreakAns = Integer.MAX_VALUE;
		breakAns = Integer.MAX_VALUE;
		map = new byte[N][M];
		ans = 0;
		visited1 = new boolean[N][M];
		visited2 = new boolean[2][N][M];
		
		// 맵 입력받기
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			String[] strArr = str.split("");
			for(int j=0; j<M; j++) {
				map[i][j] = Byte.parseByte(strArr[j]);
			}
		}
		
		// 안뚫었을 때 최소거리 (noBreakAns) 갱신
		bfs1(0,0,1,0);
		// 뚫었을 때 최소거리 (BreakAns) 계산
		bfs2(0,0,1,0);

		if(noBreakAns == -1 && breakAns == -1) ans = -1;
		else if(noBreakAns == -1) ans = breakAns;
		else if(breakAns == -1) ans = noBreakAns;
		// 둘다 -1이 아닌 경우
		else ans = Math.min(noBreakAns, breakAns);
		
		System.out.println(ans);
	}

}
