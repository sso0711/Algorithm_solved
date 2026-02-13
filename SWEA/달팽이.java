package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 달팽이 {

	static int T;
	
	static int N, M;
	static int num = 25;
	static int d = 0;
	
    static int[][] graph;
    static boolean[][] visited;
		
	static int[] dx = {0,1,0,-1}; // 좌하우상
	static int[] dy = {-1,0,1,0};
	
	public static void dfs(int x, int y) {
        visited[x][y] = true;
        graph[x][y] = num--;
        
        if(num >=1) {
        	
        	int nx = x + dx[d];
            int ny = y + dy[d];
            
            // 범위를 벗어나는 경우 방향 바꾸기 !
            if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) {
            	d = (d+1) % 4;
            	nx = x + dx[d];
                ny = y + dy[d];
            }
            
            dfs(nx,ny);
            
            
        }
    }

	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		
        
		for(int i=1; i<T+1; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			graph = new int[N][M];
			visited = new boolean[N][M];
		
			
//			for (int j = 0; j < N; j++) {
//	            st = new StringTokenizer(br.readLine());
//	            for (int k = 0; k < M; k++) {
//	                graph[j][k] = Integer.parseInt(st.nextToken());
//	            }
//	        }
			
			dfs(0,M-1);
			
			for (int j = 0; j < N; j++) {
	            for (int k = 0; k < M; k++) {
	            	System.out.printf("%d ",graph[j][k]);
	            }
	            System.out.println();
	        }
			
			
		}
	}
}


