package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main_2493_박소영 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		Deque<Integer> q = new ArrayDeque<>();
		
		for (int i=0; i<N; i++) {
		    q.offer(Integer.parseInt(st.nextToken()));
		}
		
		for (int i=0; i<N; i++) {
		    q.offer(Integer.parseInt(st.nextToken()));
		}
		
	}

}
