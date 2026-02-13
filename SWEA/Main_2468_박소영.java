package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;


/*
 * 1차 시도 : dfs 함수에서 arr[nx][ny] < rain 인 부분을 건너뛰도록 조건을 걸지 않으면 잠긴 영역까지 탐색하게 된다.
 * 2차 시도 : 1이하면 모두 잠기게 되므로 2 이상인 영역부터 탐색하려 했는데, 전체 맵이 1 이하인 경우가 있을 수 있다.
 * 
 * */
public class Main_2468_박소영 {
	static int maxHeight =0;
	static int T,cnt,ans,rain;
	static boolean[][] visited;
	static int[][] arr;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	
	
	public static void dfs(int x, int y) {
		visited[x][y] = true;
		
		for(int d=0; d<4; d++) {
			int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || ny < 0 || nx >= T || ny >= T) continue;
            if (visited[nx][ny]) continue;
            if (arr[nx][ny] <= rain) continue;  // 1차 시도 보완

            dfs(nx, ny);
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		arr = new int[T][T];
		visited = new boolean[T][T];
		
		for(int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<T; j++) {
				int num = Integer.parseInt(st.nextToken());
				arr[i][j] = num;
				maxHeight = Math.max(maxHeight, num); // 최대높이 계속 갱신
			}
		}
		

		for(int i=0; i<maxHeight; i++) { // 2차 시도 보완
			rain = i;
			cnt = 0; // 각각의 최대잠긴 높이(maxHeight)에 따른 영역 갯수
			
			for (int a = 0; a < T; a++) {
			    Arrays.fill(visited[a], false);
			}
			
			for(int k=0; k<T; k++) {
				for(int j=0; j<T; j++) {
					if(!visited[k][j] && arr[k][j] > rain) { // 안 잠기는 영역만 탐색 시작
						dfs(k,j);
						cnt += 1;
					}
				}
			}
			ans = Math.max(cnt, ans); // 이전까지의 최대 영역수와 비교
		}
		
		
		
		System.out.println(ans);
	}

}
