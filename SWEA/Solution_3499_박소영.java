package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution_3499_박소영 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			Deque<String> q1 = new ArrayDeque<>();
			Deque<String> q2 = new ArrayDeque<>();
			
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 큐 2개
			for (int i=0; i < (N+1)/2; i++) {
			    q1.offer(st.nextToken());
			}
			
			while(st.hasMoreTokens()) {
				q2.offer(st.nextToken());
			}
			
			StringBuilder sb = new StringBuilder();
			
			
			while(!q1.isEmpty() || !q2.isEmpty()) {
				if (!q1.isEmpty()) sb.append(q1.poll()).append(" ");
			    if (!q2.isEmpty()) sb.append(q2.poll()).append(" ");
			}
				
			System.out.printf("#%d %s\n",test_case, sb);
		}
	}

}
