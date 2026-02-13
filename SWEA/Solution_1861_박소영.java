package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;


/* 
 * 1차 시도 - dfs + 백트래킹
 * 시간초과 (27개 중 11개 맞음)
 * 모든 지점을 dfs 돌렸는데, 더 효율적인 방법이 필요했다.
 * 
 * 
 * 2차 시도 - dfs + dp
 * 1949 등산로 조성 문제와 거의 유사해보여서 같은 알고리즘을 적용했는데, 등산로처럼 깎을 수 있는 조건이 사라지니 DP가 가능해졌다.
 * 
 * 등산로 문제에서는 어딜 깎느냐에 따라 최장 길이와 경로가 달라지기에, 특정 점에서 최장길이를 딱 저장해놓을 수가 없었다.(DP불가)
 * 하지만 이 문제는 그 조건이 사라지니 특정 점에서의 최장길이가 "항상 동일하게" 결정되고, 이를 재사용할 수 있게 되었다.
 * +) DAG에서의 최장경로 문제와 비슷
 *  
 * 3차 시도 - visited 삭제
 * 1 증가하는 곳으로만 dfs 탐색하기 때문에, 방문했던 곳을 또 탐색할 일은 없어서 visited가 불필요하다.
 * 
 * 
 * 
 * cf) 이 문제 조건에서 만약 +1이 아닌 더 큰 곳으로 이동(<), 방번호 숫자에 중복이 있었어도 "사이클"이 발생하지 않기 때문에 DP가 가능하다.
 * 	   그런데 만약 크거나 같은 곳으로 이동( <= ) 이었으면 사이클이 발생할 수 있어 DP로 풀 수 없는 문제가 된다.
 *     이걸 외울 필요는 없겟지만, 어떤 경우들이 나올 수 있을지 예시를 생각해보면서, 어차피 항상 동일한 결과가 보장되는데 반복계산이 되지 않는지 생각해보자 
 * 
 * */




public class Solution_1861_박소영 {

	static int N, start, ans; // 최장 이동 길이
	static int[][] map;
	static int[][] dp; // dp[i][j]는 해당 지점에서의 최장 길이
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
    
	// dp 적용
	static int dfs(int x, int y) {
		// 이전에 구한 값이면
		if(dp[x][y] != 0) return dp[x][y];
		// 초기값 : 모든 지점은 최소 1의 길이는 가진다.
		dp[x][y] = 1;
		
		for(int d=0; d<4; d++) {
	        int nx = x + dx[d];
	        int ny = y + dy[d];

	        if(nx<0 || ny<0 || nx>=N || ny>=N) continue;

	        if(map[nx][ny] == map[x][y] + 1) {
	            dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
	        }
	    }
		return dp[x][y]; // 
	}
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tcase=1; tcase<=T; tcase++){
        	
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            ans = start = 0;
            map = new int[N][N];
            dp = new int[N][N];
            
            for(int i=0; i<N; i++){
            	st = new StringTokenizer(br.readLine());
            	for(int j=0; j<N; j++){
            		map[i][j] = Integer.parseInt(st.nextToken());
            	}
            }
            
            for(int i=0; i<N; i++){
            	for(int j=0; j<N; j++){
            		int len = dfs(i,j);
            		
            		// 새로운 최장 길이라면?
                    if(len > ans) {
                        ans = len;
                        start = map[i][j];
                    }
                    
                    // 이전 최장길이와 같다면, 더 작은 숫자 선택
                    else if(len == ans) {
                        start = Math.min(start, map[i][j]);
                    }
                }
            }
            
            System.out.printf("#%d %d %d\n",tcase,start, ans);
        }
    }

}

