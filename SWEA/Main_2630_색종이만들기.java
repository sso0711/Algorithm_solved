package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2630_색종이만들기 {

	static int N, blue, white, ans; // 잘라진 색종이 갯수
	static int[][] map;
	
	// 분할 정복 함수
    public static void f(int x, int y, int size) {
    	int cnt = 0;
		int sum = 0;
		boolean mixed = false;
		
    	// 부분 문제의 합을 구한다.
    	for(int i=x; i<x+size; i++){
        	for(int j=y; j<y+size; j++){
        		sum += map[i][j];
        		cnt++;
        		
        		// 파란색/흰색 섞인 상황 가지치기
        		if(0 < sum && sum < cnt) {
        			mixed = true;
        			break;
        		}
        	}
        	if (mixed) break;
        }
    	
    	// 모두 파란색
    	if(sum == size*size) {
    		blue++;
    
    	// 모두 하얀색
    	}else if(sum == 0) {
    		white++;
    	
    	// 색이 섞여있음
    	}else {
    		f(x, y, size/2);
    		f(x, y+size/2, size/2);
    		f(x+size/2, y, size/2);
    		f(x+size/2, y+size/2, size/2);
    	}
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        ans = blue = white = 0;
        map = new int[N][N];
        
        for(int i=0; i<N; i++){
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<N; j++){
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        f(0,0,N);
        

        System.out.println(white);
        System.out.println(blue);
    }
}
