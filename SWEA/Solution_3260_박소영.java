package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution_3260_박소영 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			BigInteger A = new BigInteger(st.nextToken());
            BigInteger B = new BigInteger(st.nextToken());
			
			BigInteger sum = A.add(B);
			
			System.out.printf("#%d %s\n",test_case, sum);
		}
	}

}
