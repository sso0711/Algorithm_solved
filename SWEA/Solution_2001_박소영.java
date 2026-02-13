package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 2차원 누적합
 * 
 * boj 11660 문제와 거의 비슷하고,
 * 차이점은 findMax 함수를 통해 size가 M인 모든 경우의 수 중 최대를 찾아주었다.
 * boj 11660 문제의 사각형 영역 합 구하는 공식에 아래를 대입해주었다.
 * 
 * st2=j
 * ed2=k
 * 
 * st1=j-size+1
 * ed1=k-size+1
 * 
 * */
public class Solution_2001_박소영 {
	static int T,N,M;
	static int[][] arr;
	static int[][] dp;
	static int ans;
	
	public static void findMax(int size) {
		for (int j = size; j <= N; j++) {
			for (int k = size; k <= N; k++) {
				ans = Math.max(ans, dp[j][k] - dp[j-size][k] - dp[j][k-size] + dp[j-size][k-size]);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for(int i=1; i<T+1; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr = new int[N+1][N+1];
			dp = new int[N+1][N+1];
			ans = 0;
			
			for (int j = 1; j <= N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 1; k <= N; k++) {
					arr[j][k] = Integer.parseInt(st.nextToken());
					dp[j][k] = dp[j-1][k] + dp[j][k-1] - dp[j-1][k-1] + arr[j][k];
				}
			}
			
			findMax(M);
			
			System.out.printf("#%d %d\n",i,ans);
		}
	}
}
