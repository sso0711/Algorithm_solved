package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 지금까지 문제를 순열/조합 유형과 부분집합 유형으로 나누어서 생각했었는데,
 * 이건 둘 다 해당하지 않는 유형이었다.
 * 
 * 1.조합	 - 몇 개 고르나?
 * 2.부분집합- 고르냐/안 고르냐?
 * 3.이 문제- 각 자리에 몇 개 넣나?
 * 
 * 
 * 참고) f(x1,...xn) <= k의 정수해 찾기 문제와 유사하다.
 * =============================================================
 * 
 * 1차 시도
 * 처음에 graph = new int[N+1][N+1]; // graph[i][j] = i부터 j까지 햄스터의 수
 * 와 같이 이차원배열에 저장했는데, 메모리초과가 났다. -> 시작,끝,마리수를 저장하는 2차원 배열로 변경 (Mx3)
 * 
 * 2차 시도
 * ans = arr.clone()이 아닌 ans = arr로 쓰면, arr가 계속 재귀를 통해 업데이트되면서 ans도 같이 바뀜.
 * 그 순간의 arr를 새로운 배열로 만들어줘야.
 * 
 * 3차 시도
 * 구간합을 dp로 계산 (그래도 시간초과)
 * 
 * 
 * */


// 
public class Solution_8275_박소영 {

	static int N,X,M, maxHamCnt; 
	static int[] woori, ans;
	static int[][] record;
	
    public static void f(int[] arr, int cnt, int hamCnt){
    	
    	if(cnt == N+1) { // N개 우리 모두 결정됨 (부분집합 완성)
    		// 기록과 일치 && 최대 햄스터 수 갱신
    		if(checkM(arr)!=0 && maxHamCnt < hamCnt) {
    			maxHamCnt = hamCnt;
    			ans = arr.clone(); // 
    		}
    		// 일치하지 않는 경우
    		return;
    	}
 
// 1차 시도 : 부분집합 문제가 아니므로 이렇게 풀면 안됨!
//    	arr[cnt] += 1;
//    	f(arr, cnt+1, hamCnt+1);
//    	arr[cnt] -= 1;
//    	f(arr, cnt+1, hamCnt);
// 수정 :
    	for(int i=0; i<=X; i++){ // 한 우리당 최대 마리 수 (X)
    	    arr[cnt] = i;
    	    f(arr, cnt+1, hamCnt+i);
    	}

    }

    // M개의 기록과 일치하는지 확인하는 함수
    public static int checkM(int[] arr) {
    	int[] dp = new int[N+1];
    	
    	// dp로 구간합 계산
    	for(int i=1; i<=N; i++){
            dp[i] = dp[i-1] + arr[i];
        }
    	
    	for(int i=0; i<M; i++){
    		int d = record[i][0];
    		int e = record[i][1];
    		int f = record[i][2];
    		
    		if(dp[e] - dp[d-1] != f) return 0;
    	}
    	return 1;
    }

    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
    
        for(int tcase=1; tcase<=T; tcase++){
            st = new StringTokenizer(br.readLine());
            
            N = Integer.parseInt(st.nextToken()); // 우리 갯수
            X = Integer.parseInt(st.nextToken()); // 우리 내의 최대 햄스터 갯수
            M = Integer.parseInt(st.nextToken()); // 기록 갯수
            maxHamCnt = -1;
            woori = new int[N+1]; // 중간 결과 저장
            ans = new int[N+1];   // 정답 배열
            record = new int[M][3]; // graph[i][j] = i부터 j까지 햄스터의 수
            
            
            // M개 기록 저장
            for(int j=0; j<M; j++){
            	st = new StringTokenizer(br.readLine());
                int l = Integer.parseInt(st.nextToken()); // l부터
                int r = Integer.parseInt(st.nextToken()); // r까지
                int s = Integer.parseInt(st.nextToken()); // s마리
                
                record[j] = new int[]{l,r,s};
            }
            
            f(woori,1,0);
            
            StringBuilder sb = new StringBuilder();
            
            if(maxHamCnt == -1) sb.append(-1);
            else{
            	for(int s=1; s<=N; s++) {
            		sb.append(ans[s]).append(" ");
            	}
            }
            System.out.printf("#%d %s", tcase, sb.toString());
        }
    }
}
