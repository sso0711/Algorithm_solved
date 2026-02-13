package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 부분집합 문제
public class Solution_1486_박소영 {

	static int N, B, ans; // ans : 높이가 B 이상인 탑 중에서 높이가 가장 낮은 탑
	static int[] heights;

	public static void f(int cnt, int sum) {
		if(sum >= B) {
			ans = Math.min(ans, sum);
			return;
		}
		
		if(cnt == N) {
			// ans = Math.min(ans, sum); // 전부 더해도 B이하일 수 있다.
			return;
		}
		
			
		f(cnt+1, sum + heights[cnt]);
		f(cnt+1, sum);
		
	}
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tcase=1; tcase<=T; tcase++){
        	
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 직원들의 수
            B = Integer.parseInt(st.nextToken()); // 선반 높이
            ans = Integer.MAX_VALUE;
            heights = new int[N];
            
            // 직원들의 키 저장
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++){
            	heights[i] = Integer.parseInt(st.nextToken());
            }
            
            f(0,0);
            

            System.out.printf("#%d %d\n", tcase, ans-B);
        }
    }

}
