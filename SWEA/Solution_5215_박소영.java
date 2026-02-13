package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 부분집합
// n개 중 정해진 갯수의 조합을 구할 땐 (nCr에서 r이 고정) 백트래킹을 이용한 조합이 적합하지만,
// n개 중 몇 개를 고르는지 정해져있지 않고, nC1 ~ nCn을 모두 구하며 찾아야 할 땐 부분집합이 더 적합하다.(nC?)


public class Solution_5215_박소영 { 
	static int T,N,L; 
	static boolean[] visited; 
	static int ans; 
	static int[] taste; 
	static int[] kalori; 
	
	public static void f(int cnt, int cal, int tasteSum){
	    if(cal > L) return; 
	    
	    if(cnt == N){
	        ans = Math.max(ans, tasteSum);
	        return;
	    }
	    
	    // 선택하는 경우
	    f(cnt+1, cal + kalori[cnt], tasteSum + taste[cnt]);
	    // 선택 안하는 경우
	    f(cnt+1, cal, tasteSum);
	} 
	
	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st; T = Integer.parseInt(br.readLine()); 
		
		for(int i=1; i<=T; i++){ 
			st = new StringTokenizer(br.readLine()); 
			N= Integer.parseInt(st.nextToken()); 
			L= Integer.parseInt(st.nextToken()); 
			
			taste = new int[N]; 
			kalori = new int[N]; 
			ans = 0; 
			visited = new boolean[N]; // 인영의 카드 목록 
			
			for(Integer j=0; j<N; j++){ 
				st = new StringTokenizer(br.readLine()); 
				taste[j] = Integer.parseInt(st.nextToken()); 
				kalori[j] = Integer.parseInt(st.nextToken()); 
			} 
			
			f(0,0,0); 
			
			System.out.printf("#%d %d\n",i,ans); 
		} 
	}
}