package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11659_박소영 {
	static int N,M;
	static int[] arr;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N+1];
		dp = new int[N+1]; // dp[i] 는 0~i번째 인덱스까지의 합
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			dp[i] = dp[i - 1] + arr[i];
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int ed = Integer.parseInt(st.nextToken());
			
			int sum = dp[ed] - dp[start-1]; // 인덱스 주의
			
			
			System.out.println(sum);
		}
	}

}
