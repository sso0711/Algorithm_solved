import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Arrays;
/*
 * 
 * 1차 시도 : 남은 벽돌 갯수를 구해야 하는데, 부순 벽돌 갯수를 출력했었다. 당연히 로직이 잘못됐다 생각하고 이리저리 고쳤었다,,
 * 2차 시도 : 중복순열 경우의 수마다 shoot 전후에 원본맵 저장/복원 로직 추가
 * 3차 시도 : 벽돌 내리는 로직 수정
 * 4차 시도 : bfs 첫 줄에 visited 초기화 추가. perm에서 shoot 끝날 때 뿐만 아니라 각 공 쏠 때마다 초기화 해야, 
 *        visited 처리로 인해 다음 공이 부숴야 공을 건너뛰어버리는 것을 막을 수 있다
 * 
 * */
public class Solution_5656_박소영 {
    static int N, W, H, ans;
    static int[][] map;
    static boolean[][] visited;
    static int[] N_selected; // N개의 공이 떨어질 위치들 저장
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    
    
    // 1. W개 중 벽돌 떨어뜨릴 곳 N개를 고르기 ( 중복순열 )
    public static void perm(int cnt) {
    	if(cnt == N) {
    		// 2차 시도 보완
    		int[][] saveMap = new int[H][W];
    		for(int i=0; i<H; i++) saveMap[i] = map[i].clone();
    		
    		shoot();// 2. 고른 N개 위치에 공 떨어뜨리기
    		
    		// 이번 반복에서 구한 남은벽돌수(remain)와 이전의 최솟값 ans 비교
    		int remain = 0;
    		for(int i=0; i<H; i++)
    		    for(int j=0; j<W; j++)
    		        if(map[i][j] != 0) remain++;
    		ans = Math.min(ans, remain);
    		
    		// 2차 시도 보완: 복원
    		map = saveMap;
    		for(int i=0; i<H; i++) {
    			Arrays.fill(visited[i], false);
    		}
    		return;
    	}
    	
    	for(int i=0; i<W; i++) {
    		N_selected[cnt] = i;
    		perm(cnt+1);
    	}
    }
    
    // 2. 고른 N개 위치에 대해 공 하나씩 떨어뜨리고 -> 3. 뿌수기 -> 2,3 반복
    public static void shoot() {
    	for(int idx: N_selected) {
    		for(int x=0; x<H; x++) {
    			if(map[x][idx]==0) continue;
    			
    			// 벽돌에 닿으면
    			int depth = map[x][idx]-1;
    			// 적힌 수만큼 상하좌우 뿌수기
    			bfs(x,idx,depth);
    			// 다음 공 떨어뜨리러 가기
    			break;
    		}
    	}
    	
    	
    }
    
    // 3. 벽돌 깨기
    public static void bfs(int x, int y, int depth) {
        for(int i=0; i<H; i++) Arrays.fill(visited[i],false); // 4차 시도 보완
    	Queue<int[]> q = new ArrayDeque<>();
    	q.offer(new int[] {x,y,depth} );
    	visited[x][y] = true;
    	
    	while(!q.isEmpty()) {
    		int[] cur = q.poll();
    		int cx = cur[0];
    		int cy = cur[1];
    		int cdepth = cur[2];
    		
    		map[cx][cy] = 0;
    		
    		for(int multiple = 1; multiple <= cdepth; multiple++) {
	    		for(int d=0; d<4; d++) {
	    			int nx = cx + dx[d]*multiple;
	    			int ny = cy + dy[d]*multiple;
	    			
	    			if(nx<0 || ny<0 || nx>=H || ny>=W || visited[nx][ny]) continue;
	    			if(map[nx][ny] == 0) continue;
	    			
	    			int ndepth = map[nx][ny] - 1;
	    			q.offer(new int[] {nx,ny,ndepth});
	    			visited[nx][ny] = true;
	    		}
    		}
    	}
        // 4. 밑에 빈공간 있는 벽돌 내리기
    	down();
    }
    
    // 4. 밑에 빈공간 있는 벽돌 내리기
    public static void down() {
    	int tmp1 = -1;
		int tmp0 = -1;
		int tmpy = -1;
		boolean flag = false;
    	
    	for(int y=0; y<W; y++) {
    		int bottom = H-1;
    		for(int x=H-1; x>=0; x--) {
    			if(map[x][y] != 0) {
    				map[bottom][y] = map[x][y];
    				if(bottom != x) map[x][y] = 0;
    				bottom--;
    			}
    	
    		}
    		for(int x=bottom; x>=0; x--) map[x][y] = 0;
    		
// 3차 시도 보완
//    		for(int x=H-1; x>=0; x--) {
//    			if(map[x][y] == 0) {
//    				tmp0 = x;
//    			}
//    			else if(tmp0 != -1 && map[x][y] != 0) {
//    				tmp1 = x;
//    			}
//    			
//    			if(tmp0 != -1 && tmp1 != -1) {
//    				tmpy = y;
//    				flag = true;
//    			}
//    			if(flag) {
//    				map[tmp0][tmpy] = map[tmp1][tmpy];
//    				map[tmp1][tmpy] = 0;
//    				flag = false;
//    				
//    			}
//    			
//    			if(tmp1==0) break;
//    	
//    		}
    	}
    	
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tcase=1; tcase<=T; tcase++){
            st = new StringTokenizer(br.readLine());

            N= Integer.parseInt(st.nextToken());
            W= Integer.parseInt(st.nextToken());
            H= Integer.parseInt(st.nextToken());
            ans = H * W;
            
            map = new int[H][W];
            visited = new boolean[H][W];
            N_selected = new int[N];
            
            // 맵 입력받기
            for(int i=0; i<H; i++) {
            	st = new StringTokenizer(br.readLine());
            	for(int j=0; j<W; j++) {
                	map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            perm(0);

            System.out.printf("#%d %d\n",tcase,ans);
        }
    }

}
