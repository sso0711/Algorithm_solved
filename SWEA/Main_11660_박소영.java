package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 2차원 구간합 문제
 * 
 * 1차 시도 : 1차원 dp로 생각했었다. dp[i][j] = dp[i][j-1] + arr[i][j]; 와 같이 써서 이전 행들 + 현재 요소까지 합으로 생각했는데
 * 			dp식도 잘못됐고 점화식을 애초에 세울 수가 없었다,, 즉 나는 부분문제를 잘못 쪼갰다
 * 			구하려는 것은 "사각형"영역의 합이다. 그러면 2차원 dp테이블의 각 요소도 사각형의 합을 저장했어야 했다
 * 			
 * 		    => dp[i][j] = (1,1) ~ (i,j)까지 "사각형"의 합 (2차원 dp)로 수정 
 * 
 * 
 *	누적합은 dp의 한 종류이다.
 *	
 * 
 */

public class Main_11660_박소영 {
	static int N,M;
	static int[][] arr;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N+1][N+1]; // (1,1)부터 활용하기 때문에
		dp = new int[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + arr[i][j];
			
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int k = 0; k < M; k++) {
			st = new StringTokenizer(br.readLine());
			
			int st1 = Integer.parseInt(st.nextToken());
			int ed1 = Integer.parseInt(st.nextToken());
			
			int st2 = Integer.parseInt(st.nextToken());
			int ed2 = Integer.parseInt(st.nextToken());
			
			int sum = dp[st2][ed2] - dp[st1-1][ed2] - dp[st2][ed1-1] + dp[st1-1][ed1-1]; 
			
			
			System.out.println(sum);
		}
	}

}

