package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2563_박소영 {
	static boolean visited[][] = new boolean[100][100];
	static final int[] dx = {1,0,-1,0};
	static final int[] dy = {0,1,0,-1};
	static int a,b,maxX, maxY,T;
	
	public static void dfs(int x, int y) {
		visited[x][y] = true;
		
		for(int d=0; d<4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx < a || ny < b || nx >= maxX || ny >= maxY ) continue;
			if (visited[nx][ny]) continue;
			
			dfs(nx,ny);
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		
		for(int i=0 ; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			maxX = a+10;
			maxY = b+10;
			
			if(maxX > 100) maxX = 100;
			if(maxY > 100) maxY = 100;
			
			// 이 부분이 없다면, a,b가 이미 칠해진 영역일 때 사방이 다 visited이므로 아무것도 못하고 끝남
			for(int x = a; x < maxX; x++) {
                for(int y = b; y < maxY; y++) {
                    if(!visited[x][y]) {
                        dfs(x, y);
                    }
                }
            }
			
		}
		
		int cnt = 0;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(visited[i][j]) cnt++;
			}
		}
		
		
		System.out.println(cnt);
	}

}
