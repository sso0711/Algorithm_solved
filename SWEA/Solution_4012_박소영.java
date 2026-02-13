package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * 
 * 
 * 
 * */

public class Solution_4012_박소영 {
	static int N, ans; // 경우의 수
    static int[][] map;
    static int[] arr = new int[2];

    public static void f(int cnt, int start, Deque<Integer> dq) {
        if(cnt==2) {
            int b = dq.removeLast();
            int a = dq.removeLast();
            ans = Math.min(ans, Math.abs(a - b));
            dq.addLast(a);
            dq.addLast(b);
            return;
        }

        for(int i=start; i<N; i++){
            for(int j=i+1; j<N; j++){
                dq.addLast(map[i][j] + map[j][i]);
                f(cnt+1, i+1, dq); // start 이동
                dq.removeLast();
            }
        }
    }

    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tcase=1; tcase<=T; tcase++){

            N = Integer.parseInt(br.readLine());
            ans = Integer.MAX_VALUE;
            map = new int[N][N];
            Deque<Integer> stack = new ArrayDeque<>();
            
            for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
            
            
            f(0,0,stack);

            System.out.printf("#%d %d\n",tcase,ans);
        }
    }
}

