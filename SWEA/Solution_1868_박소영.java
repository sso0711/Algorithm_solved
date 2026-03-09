import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 최소가 되는 경우의 수를 구하기 위해 클릭하는 칸의 부분집합/조합을 구하려 하였으나
 * 한변의 길이 N이 100, 지뢰 10개인 경우 생각해보면 부분집합 경우의 수는 2^90으로 불가능하다.
 *  
 * 따라서 이 문제는 주변지뢰 0인 칸을 우선적으로 클릭하면 최소가 보장된다는 점을 깨달으면 구현은 쉬웠다.(그리디)
 * (0근처에 0이 있어 연쇄적으로 열리는 경우, 이들 중 어떤 것을 먼저 클릭하던 결과는 같다!)
 * 
 * 
 * */

public class Solution_1868_박소영 {
	static int N, ans; 
	static int[][] map;
	static boolean[][] visited;	
	static int[] dx = {0,1,0,-1,1,1,-1,-1};
	static int[] dy = {1,0,-1,0,-1,1,-1,1};

    public static int countBomb(int x, int y) {
    	int cnt = 0;
    	for(int d=0; d<8; d++) {
    		int nx = x+dx[d];
    		int ny = y+dy[d];
    		
    		if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
    		if(map[nx][ny] == -1) cnt++;
    		
    	}
    	return cnt;
    }
    
    public static void bfs(int x, int y) {
    	Queue<int[]> stack = new ArrayDeque<>();
    	stack.offer(new int[] {x,y});
    	visited[x][y] = true;
    	
    	while(!stack.isEmpty()) {
    		int[] cur = stack.poll();
    		int cx = cur[0];
    		int cy = cur[1];
    		
	    	for(int d=0; d<8; d++) {
	    		int nx = cx + dx[d];
	    		int ny = cy + dy[d];
	    		if(nx<0 || ny<0 || nx>=N || ny>=N || visited[nx][ny]) continue;

	    		visited[nx][ny] = true; // 1차 시도 : 밑의 if문에서 올려줌. [nx][ny]가 0이든 아니든 0인 [x][y]의 8방 중 하나이므로 무조건 visited 처리
	    		if(map[nx][ny]==0) {
	    			stack.offer(new int[]{nx, ny});
	    		}
	    	}
    	}
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tcase=1; tcase<=T; tcase++){
        	
            N = Integer.parseInt(br.readLine());
            ans = 0;
            map = new int[N][N];
            visited = new boolean[N][N];
            
            // 맵 입력받기
            // "*"은 -1, "."은 -2로 map에 저장하였다.
            for(int i=0; i<N; i++){
            	String str = br.readLine();
            	String[] strArr = str.split("");
            	for(int j=0; j<N; j++){
            		if(strArr[j].equals("*")) map[i][j] = -1;
            		else if(strArr[j].equals(".")) map[i][j] = -2;
            	}
            }
            
            // . 이면 근처지뢰수 계산
            for(int i=0; i<N; i++){
            	for(int j=0; j<N; j++){
            		if(map[i][j] == -2) {
            			map[i][j] = countBomb(i,j);
            		}
            		if(map[i][j] == -1) visited[i][j] = true;
            		
            	}
            }
            
            
            // 근처에 지뢰 없는 0인 칸부터 탐색
            for(int i=0; i<N; i++){
            	for(int j=0; j<N; j++){
            		if(!visited[i][j] && map[i][j] == 0) {
            			bfs(i,j); // 한번 클릭 = 한번 bfs
            			ans ++;
            		}
            	}
            }
            
            // 나머지 미방문 .인 칸 탐색
            for(int i=0; i<N; i++){
            	for(int j=0; j<N; j++){
            		if(!visited[i][j]) {
            			visited[i][j] = true;
            			ans ++;
            		}
            	}
            }
            

            System.out.printf("#%d %d\n",tcase,ans);
        }
    }

}
