package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1289_박소영 {
	
	static int T;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<T+1; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			
			char[] arr = str.toCharArray();
			int[] nums = new int[arr.length];
			
			int temp = 0;
			int cnt = 0;
			
			for (int k=0; k<arr.length; k++) {
				nums[k] = arr[k] - '0';
			}
			
			
			for(int j=0; j<str.length(); j++) {
				if(nums[j] != temp) {
					cnt ++;
					temp = nums[j];
				}else {
					continue;
				}
				
			}
			
			System.out.printf("#%d %d",i,cnt);
		}
	}

}
