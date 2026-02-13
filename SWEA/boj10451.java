package com.ssafy.algorithm;

public class boj10451 {
	
	static boolean[] visited;
	static int N;
	static int T;
	static int[] arr;
	
	public static int notVisitedIdx(boolean[] visited) {
		for (int i=0; i<N ; i++) {
			if(!visited[i]) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=1; i<T+1; i++) {
			int cyc = 0; // 순열사이클 갯수
			int idx = 0;
			
			// visited = new int[N] {false};
			
			// 모든 배열 요소 방문 !
			for(int j=0; j<N; j++) {
				if(idx == arr[idx]) {
					cyc++;
				}
				
				idx = arr[idx];
				
				if(notVisitedIdx(visited)==-1) {
					// return cyc;
				}
			}
			
		}
	}

}
