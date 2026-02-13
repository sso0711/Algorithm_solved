package com.ssafy.algorithm;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;
/* 미로1
 * 
일반적인 격자그래프 문제.

입출력형식은 늘 헷갈린다.. static 변수 선언/ 객체 생성 시점 구분 잘 하자.
그리고 visited, graph 객체 생성을 테케 10개마다 매번 생성해서 초기화 시간을 줄여야할지
한 번만 new 생성하고 재활용해서 메모리 이점을 가져갸아할지 헷갈린다
tradeoff 같은데 문제마다 다르려나...

+) 항상 (1,1)이 출발점인지 모르고 이중for문으로 출발점(2) 찾고있었다.. 문제에서 제대로 안알려줬다

1회차 시도 : 출구를 찾았을 때 모든 재귀가 종료되도록 if(res == 1) return; 상단에 추가가 필요했다.
2회차 시도 : visited배열은 테케마다 초기화해줘야하는데 깜박함
*/

public class Solution_1226_박소영
{
	static int res = 0;
	// static int N;
	static int[] dx = {0,1,0,-1}; // 우하좌상
	static int[] dy = {1,0,-1,0};
	
	static int[][] graph;
	static boolean[][] visited;
	
	public static void dfs(int x, int y) {
		if(res == 1) return; // 이게 없으면 출구를 찾아서 return을 해도 dfs재귀가 계속 돈다.
		
		visited[x][y] = true;
		
		for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || ny < 0 || nx >= 16 || ny >= 16) continue;
            if (visited[nx][ny]) continue;
            if (graph[nx][ny] == 1) continue;
            if (graph[nx][ny] == 3) {
            	res = 1;
            	return; // break를 하면, 출구를 찾아도 계속 dfs가 돈다.
            }

            dfs(nx, ny);
        }
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		graph = new int[16][16];
		visited = new boolean[16][16];
		
		for(int i=1; i<11; i++) {
			br.readLine(); // 테케 번호 무시
			
		    res = 0;
			
			// 미로 입력받기
			for (int j = 0; j < 16; j++) {
			    String line = br.readLine(); // "1111111111111111"
			    for (int j2 = 0; j2 < 16; j2++) {
			        graph[j][j2] = line.charAt(j2) - '0';
			    }
			}
			// visited 초기화
            for (int j = 0; j < 16; j++) {
			    for (int j2 = 0; j2 < 16; j2++) {
			        visited[j][j2] = false;
			    }
			}
            
            
			dfs(1,1);
			
			System.out.printf("#%d %d\n",i, res);
		}
		
	}
}
