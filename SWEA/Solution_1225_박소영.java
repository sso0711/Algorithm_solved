package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution_1225_박소영 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= 10; test_case++) {
			
			int subtract = 1;
			
			Queue<Integer> q = new ArrayDeque<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			br.readLine(); // n번째 테케 번호 입력
			
			// 큐에 담기
			while (st.hasMoreTokens()) {
			    q.offer(Integer.parseInt(st.nextToken()));
			}
			
			while(true) {
				Integer i = q.poll();
				i -= subtract;
				subtract++;
				
				if(subtract > 5) {
					subtract = 1;
				}
				
				if(i <= 0) {
					i = 0;
					q.offer(i);
					break;
				}
				q.offer(i);
			}
			
			StringBuilder sb = new StringBuilder();
			
			for (int x : q) {
			    sb.append(x).append(" ");
			}
			
			System.out.printf("#%d %s\n",test_case, sb.toString());
		}
	}

}
