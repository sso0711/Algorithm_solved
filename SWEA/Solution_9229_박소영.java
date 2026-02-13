package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 정렬 후 투포인터로 풀었다.
 * 다른 풀이를 보니 완전탐색(이중for문)으로 풀어도 O(N^2)으로 시간 내에 가능한것같았다.
 * 투포인터는 정렬이 필수는 아니지만 이 문제에서는 필요했다
 * 
 * 
 * 1차 시도 : if(sum == M) {
				ans = sum; // 이 부분에서 ans 갱신 필요했음
				break;
			}
	근데 위 if문을 else if(sum <= M) 로 포함시켜도 된다 !
 * 
 * */

public class Solution_9229_박소영 {

	static int T,N,M;
 	static int[] arr;
	static int ans, sum;
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for(int i=1; i<T+1; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr = new int[N];
			ans = -1;
			sum = -1;
			int start = 0; // 투 포인터
			int ed = N-1;
			
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr);

			while(start < ed) {
				
				sum = arr[start] + arr[ed];
				
				if(sum == M) {
					ans = sum;
					break;
				}
				
				if(sum > M) ed--;
				else if(sum < M) {
					ans = Math.max(ans, sum);
					start++;
				}
			}
			
			System.out.printf("#%d %d\n",i,ans);
		}
	}
}
