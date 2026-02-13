package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 일반적인 격자그래프 문제.
 * 사다리를 타는 방향에 따라 좌,우를 먼저 탐색해서 가로선을 지나치지 않도록만 해주면 되었다.
 * 
 * 1차 시도 : 답을 찾은 후 재귀함수의 반복을 멈추기 위해 if(found) return; 필수!!
 * */

public class Solution_1210_박소영 {
	static int T;
	static int ans;
	static boolean found;
	static int[][] arr = new int[100][100];
	static boolean[][] visited = new boolean[100][100];
	static int[] dx = {0,0,-1}; //좌우 먼저 탐색 필요! (아래방향은 불가.
	static int[] dy = {-1,1,0};
	
	public static void dfs(int x, int y) {
		if(found) return; // 1차 시도 보완

		visited[x][y] = true;
		
		if(x==0) {
			ans = y;
			found = true;
			return;
		}
		
		
		for(int d=0; d<3; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(nx<0 || ny<0 || nx>=100 || ny>=100) continue;
			if(visited[nx][ny]) continue;
			if(arr[nx][ny] != 1) continue; 
			
			dfs(nx,ny);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int test_case = 1; test_case <= 10; test_case++) {
			
			br.readLine(); // n번째 테케 번호 입력
			StringTokenizer st;
			
			ans = 0;
			found = false;
			for(int i=0; i<100; i++) {
				Arrays.fill(visited[i], false);
				}
			
			// 사다리 입력받기
			for(int i=0; i<100; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<100; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<100; i++) {
				if(arr[99][i]==2) dfs(99,i);
			}
			
		
			
			System.out.printf("#%d %s\n",test_case, ans);
		}
	}

}
