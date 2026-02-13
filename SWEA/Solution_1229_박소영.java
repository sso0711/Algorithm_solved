package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1229_박소영 {
	
	static int N; // 원본 암호문 길이
	static List<Integer> li; // 암호문 배열
	static int n; // 명령어 갯수
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int j = 1; j <= 10; j++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			li = new ArrayList<>();

			for(int i=0; i<N; i++) {
				li.add(Integer.parseInt(st.nextToken()));
			}
			
			n = Integer.parseInt(br.readLine());
			
			// st = new StringTokenizer(br.readLine()); (X)
			// 명령어가 매우 길어서 여러 줄로 나뉠 수 있다!!
			
			// 수정 : 명령어 토큰을 다 받을 때까지 읽기
			StringBuilder cmd = new StringBuilder();
			while (cmd.toString().split(" ").length < n * 3) {
			    cmd.append(" ").append(br.readLine());
			}
			st = new StringTokenizer(cmd.toString());


			for (int k = 0; k < n; k++) {    // while(st.hasMoreTokens()) {
				
				String IorD = st.nextToken();
				
				if(IorD.equals("I")) {
					int idx = Integer.parseInt(st.nextToken());
					int len = Integer.parseInt(st.nextToken());
					List<Integer> li2 = new ArrayList<>();
					
					for (int i = 0; i <len ; i++) {
						li2.add(Integer.parseInt(st.nextToken()));
					}
					li.addAll(idx, li2); // li리스트의 인덱스 idx에 다른 리스트 통째로 끼워넣기
				}
				
				else if (IorD.equals("D")) {
					int idx2 = Integer.parseInt(st.nextToken());
					li.subList(idx2, idx2+Integer.parseInt(st.nextToken())).clear();
				}
			}
			
			StringBuilder sb = new StringBuilder();
			for (int x=0; x<10; x++) {
			    sb.append(li.get(x)).append(" ");
			}
			
			System.out.printf("#%d %s\n",j,sb.toString().trim());
		}
		
	}

}
