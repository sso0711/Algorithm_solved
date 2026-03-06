import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4014_박소영 {
	static int N, X, ans; 
	static int[][] map;
	static boolean[][] visited;	
	//static int[] rowMax;
	//static int[] columnMax;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};

    public static void dfs(int x, int y, int length, int isCut) {
    	ans = Math.max(ans, length);
    	visited[x][y] = true;
    	int diff = 0;
    	
    	for(int d=0; d<4; d++) {
    		int nx = x + dx[d];
    		int ny = y + dy[d];
    		
    		if(nx<0 || ny<0 || nx>=N || ny>=N || visited[nx][ny]) continue;
    		
    		if(map[nx][ny] < map[x][y])
    			dfs(nx, ny, length+1 ,isCut);
    		else if(isCut == 0 && map[nx][ny] - K < map[x][y]) {
    			diff = map[nx][ny] - map[x][y];
    			map[nx][ny] -= (diff +1); 
    			dfs(nx, ny, length+1, 1);
    			map[nx][ny] += (diff +1);
    			
    		}
    	}
    	visited[x][y] = false; // 1차 시도 보완 
    }
    
    
    public static void column_check() {
    	for(int j=0; j<N; j++) {
    		for(int i=0; i<N-1; i++) {
        		int cur = map[i][j];
        		int next = map[i+1][j];
        		
        		if(cur==next) {
        			
        		} else if(cur-1==next) {// 1낮은 내리막길
        			
        		} else if(cur+1==next) {// 1높은 오르막길
        			
        		} else { // 2이상 차이남
        			continue;
        		}
        		
        	}
    	}
    }
    
    public static void row_check() {
    	for(int j=0; j<N; j++) {
    		for(int i=0; i<N-1; i++) {
        		int cur = map[j][i];
        		int next = map[j][i+1];
        		
        		if(cur==next) {
        			
        		} else if(cur-1==next) {// 1낮은 내리막길
        			
        		} else if(cur+1==next) {// 1높은 오르막길
        			
        		} else { // 2이상 차이남
        			continue;
        		}
        		
        	}
    	}
    }
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tcase=1; tcase<=T; tcase++){
        	st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            ans = 0;
            map = new int[N][N];
            visited = new boolean[N][N];
            //rowMax = new int[N];
            //columnMax = new int[N];
            
            // 맵 입력받기
            for(int i=0; i<N; i++){
            	st = new StringTokenizer(br.readLine());
            	for(int j=0; j<N; j++){
            		map[i][j] = Integer.parseInt(st.nextToken());
            	}
            }
            
            // 세로줄 검사
            for(int i=0; i<N; i++) column_check();
            // 가로줄 검사
            for(int i=0; i<N; i++) row_check();
            
            System.out.printf("#%d %d\n",tcase,ans);
        }
        
	}

}
