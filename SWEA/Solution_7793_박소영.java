import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 
 * 1차 시도 : devils.size(), q.size()를 변수로 빼주지 않으면 매 반복마다 for문 조건이 달라지게 된다.
 * 2차 시도 : 인간이 움직일 때 여신을 만나면 최솟값 갱신 후 break가 아닌 return하여 정답을 바로 도출한다.
 * 			bfs돌다가 여신 처음 만난 순간 무조건 최단거리이기 때문에!!!! 나머지를 체크할 필요가 없는 것
 * 			알고있어도 놓치는 부분이 항상 생기는듯하다
 *			
 *			추가로, 같은 이유로 인간의 좌표를 (x,y,time)으로 써서 어떤 좌표에 왓을때 얼마의 시간이 걸렸는지를 함께 저장해주었는데, 이 time이 사실상 필요없다.
 * 
 * */

public class Solution_7793_박소영 {
	static int N, M, ans;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static Queue<int[]> devils = new ArrayDeque<>();
    
    public static void bfs(int x, int y, int time) {
    	Queue<int[]> q = new ArrayDeque<>();
    	q.offer(new int[] {x,y,time});
    	visited[x][y] = true;

    	while(!q.isEmpty()) {
    		// 1. 먼저 악마가 한 겹 확장된다.
    		int sz1 = devils.size();
            for(int i=0;i<sz1;i++) { // 한 겹만 확장되므로 while(!q.isEmpty()) 가 아님
	    		int[] cur = devils.poll();
	    		int cx = cur[0];
	    		int cy = cur[1];
	    		
	    		for(int d=0; d<4; d++) {
		    		int nx = cx + dx[d];
		    		int ny = cy + dy[d];
		    		
		    		if(nx<0 || ny<0 || nx>=N || ny>=M) continue;
		    		if(map[nx][ny]=='X' || map[nx][ny]=='D' || map[nx][ny]=='*') continue;
		    		
		    		// 악마는 *을 확장시키기만 하고, visited는 전혀 사용/변화 시키지 않는다.
		    		map[nx][ny] = '*';
		    		devils.offer(new int[] {nx, ny});
		    	}
            }
	    	
	    	// 2. 인간도 한 칸 움직인다.
            int sz2 = q.size();
            for(int i=0;i<sz2;i++) {
            	int[] cur2 = q.poll();
	    		int cx = cur2[0];
	    		int cy = cur2[1];
	    		int ctime = cur2[2];
	    		
	            for(int d=0; d<4; d++) {
		    		int nx = cx + dx[d];
		    		int ny = cy + dy[d];
		    		int ntime = ctime + 1;
		    		
		    		if(nx<0 || ny<0 || nx>=N || ny>=M || visited[nx][ny]) continue;
		    		if(map[nx][ny]=='X' || map[nx][ny]=='*') continue;
		    		
		    		// 여신 만나면 최솟값 갱신 후
		    		if(map[nx][ny] == 'D') {
		    			ans = Math.min(ans, ntime);
		    			return; // 2차 시도 수정
		    		}
		    		
		    		q.offer(new int[] {nx, ny, ntime});
		    		visited[nx][ny] = true;
		    		
		    	}
            }
    	}
    }
    
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tcase=1; tcase<=T; tcase++){
        	st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            ans = Integer.MAX_VALUE;
            map = new char[N][M];
            visited = new boolean[N][M];
            devils.clear();
            
            for(int i=0; i<N; i++) {
            	String str = br.readLine();
            	for(int j=0; j<M; j++) {
                	map[i][j] = str.charAt(j);
                	if(map[i][j]=='*') devils.offer(new int[] {i,j}); // 악마 좌표들 저장
                }
            }
            
            for(int i=0; i<N; i++) {
            	for(int j=0; j<M; j++) {
                	if(map[i][j]=='S') {
                		bfs(i,j,0);
                	}
                }
            }
            
            if (ans == Integer.MAX_VALUE) System.out.printf("#%d %s\n",tcase,"GAME OVER");
            else System.out.printf("#%d %d\n",tcase,ans);
        }
	}

}
