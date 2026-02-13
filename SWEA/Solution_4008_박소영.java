package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4008_박소영 {
	static int N,max,min; // 숫자갯수, 최대, 최소, 차이
	static int[] nums;
	static int[] ops = new int[4]; // + - * /
	
	public static void dfs(int cnt, int sum) {
		if(cnt == N) {
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			return; // 1차 시도: return안하면 재귀 계속됨..
		}
		
		
		for(int i=0; i<4; i++) {
			if(ops[i] == 0) continue; // 연산자를 다 썼거나 없으면 pass
			
			ops[i]--;
			if(i==0) {
				dfs(cnt+1, sum + nums[cnt]);
			}else if(i==1) {
				dfs(cnt+1, sum - nums[cnt]);
			}else if(i==2) {
				dfs(cnt+1, sum * nums[cnt]);
			}else if(i==3) {
				dfs(cnt+1, sum / nums[cnt]);
			}
			
			// dfs(cnt+1, sum); // 여기에 쓰면 sum을 다시 되돌려놔야하는데, 또 4개의 if가 필요하므로 위와 같이 쓰는게 낫다.
			ops[i]++;
		}
	}
	


	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			nums = new int[N];
			max = min = 0;
			
			st = new StringTokenizer(br.readLine());
			for (int i =0; i<4; i++) { // 연산자 갯수 저장
				ops[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i =0; i<N; i++) { // 피연산자 저장
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            
            dfs(1, nums[0]);
			
			System.out.printf("#%d %d\n",test_case, max-min);
		}
	}

}