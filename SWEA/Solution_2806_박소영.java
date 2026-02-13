package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 강사님의 풀이 : 행마다
 * 그러나 생각해내기 어려운 풀이인 것 같아 일반적인 풀이로 풀어보았다.
 * 
 * 
 * 
 * 
 * */

public class Solution_2806_박소영 {
    static int N, ans; // 경우의 수
    static int[][] map;
    static boolean[][] visited;
    // static List<int[]> core = new ArrayList<>();
    static int[] dx = {1,1,1,0,0,-1,-1,-1};
    static int[] dy = {1,0,-1,1,-1,1,0,-1};


    public static void f(int cnt, int start) {
        if(cnt == N) {
            ans++;
            return;
        }

        for(int idx = start; idx < N*N; idx++) {
            int i = idx / N;
            int j = idx % N;

            if(!dfs(i,j)) {
                for(int a=0; a<N; a++ ) Arrays.fill(visited[a], false);
                continue;
            }

            map[i][j] = 1;
            for(int a=0; a<N; a++ ) Arrays.fill(visited[a], false);

            f(cnt+1, idx+1);   // ⭐ 항상 다음 칸부터만 탐색
            map[i][j] = 0;
        }
    }

    
    // x,y 위치에 놓을 수 있는가? (8방 탐색)
    public static boolean dfs(int x, int y) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[] {x,y});
        visited[x][y] = true;

        while (!stack.isEmpty()) {
            int[] v = stack.pop();

            // 인접 정점 push
            for (int d=0; d<8; d++) {
            	int nx = v[0] + dx[d];
            	int ny = v[1] + dy[d];

            	if (nx<0 || ny<0 || nx>=N || ny>=N || visited[nx][ny]) continue;
            	if (map[nx][ny] == 1) return false; // 말을 놓을 수 없음!
                
            	stack.push(new int[] {nx, ny});
                visited[nx][ny] = true;
            }
        }
        return true;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tcase=1; tcase<=T; tcase++){

            N = Integer.parseInt(br.readLine());
            ans = 0;
            map = new int[N][N];
            visited = new boolean[N][N];
            
            f(0,0);

            System.out.printf("#%d %d\n",tcase,ans);
        }
    }
}
