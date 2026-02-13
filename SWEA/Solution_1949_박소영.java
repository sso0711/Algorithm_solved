package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution_1949_박소영 {
	// 4방에 자신보다 0 ~ K-1만큼 작은 곳이 하나라도 있어야 깎았을 때 변화가능성이 있다.
	static int N, K, max, ans; // 최장 등산로 길이
	static int[][] map;
	static boolean[][] visited;	
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
    		
    		// 더 낮으면 계속 탐색
    		if(map[nx][ny] < map[x][y])
    			dfs(nx, ny, length+1 ,isCut);
    		// 더 높은데,
    		// 아직 안깎았고 깎을 수 있으면 깎고 탐색
    		else if(isCut == 0 && map[nx][ny] - K < map[x][y]) {
    			diff = map[nx][ny] - map[x][y];
    			map[nx][ny] -= (diff +1); // 깎기 (주변보다 1만 낮아지도록, 최대한 덜깎아야 경로가 길어질 가능성이 높다)
    			dfs(nx, ny, length+1, 1);
    			map[nx][ny] += (diff +1);
    			
    		}
			// 2차 시도 보완 : 한 방향이 막히면 나머지 방향도 안보기 때문에, 이렇게 하면 안됨
//    		else { 
//    			return; // 자신보다 낮지 않고, 이미 깎았거나 K이상이라 깎아도 소용없을 때 멈춤.
//    		}	
    	}
    	visited[x][y] = false; // 1차 시도 보완 
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tcase=1; tcase<=T; tcase++){
        	
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            ans = max = 0;
            map = new int[N][N];
            visited = new boolean[N][N];
            
            for(int i=0; i<N; i++){
            	st = new StringTokenizer(br.readLine());
            	for(int j=0; j<N; j++){
            		map[i][j] = Integer.parseInt(st.nextToken());
            		max = Math.max(max, map[i][j]);
            	}
            }
            
            // 최대 높이부터 탐색 시작
            for(int i=0; i<N; i++){
            	for(int j=0; j<N; j++){
            		if(map[i][j] == max) dfs(i,j,1,0);
            	}
            }
            

            System.out.printf("#%d %d\n",tcase,ans);
        }
    }

}

