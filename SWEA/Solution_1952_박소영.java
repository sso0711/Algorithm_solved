package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1952_박소영 {
    static int ans; // 최소비용
    static int[] prices = new int[4];
    static int[] months = new int[12];

    public static void f(int cnt, int sum) {
    	if(sum > ans) return; // 가지치기
    	
    	if(cnt>=12) {
    		ans = Math.min(ans, sum);
    		ans = Math.min(ans, prices[3]);
    		return;
    	}
    	
    	for(int i=0; i<3; i++) {
    		if(i==0) f(cnt+1, sum + months[cnt]*prices[i]); // 1일
    		if(i==1) f(cnt+1, sum + prices[i]); // 1달
    		if(i==2) f(cnt+3, sum + prices[i]); // 3달
    	}
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tcase=1; tcase<=T; tcase++){
        	
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<4; i++){
                prices[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<12; i++){
                months[i] = Integer.parseInt(st.nextToken());
            }
            
            ans = Integer.MAX_VALUE;
            
            f(0,0);

            System.out.printf("#%d %d\n",tcase,ans);
        }
    }
}


