package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 어떤 알고리즘을 적용할지 접근이 어려웠다.
 * 특히 자신이 몇 번째인지 확실히 알 수 있다고 판단할 수 있는 기준이 무엇인가?가 고민되었다.
 * 
 * 4번 정점과 다른 정점들과의 차이를 기준으로 생각해보았다

[접근방법1 - 거리계산]
시작점에서 자신까지 올 수 있는 최단거리를 계산했을 때, 
1. 나와 같은 거리가 없으면 확정
2. 같은 거리가 2개 이상이면 제일 큰 애만 확정 (a의 자식이 n개, b의 자식이 n개, a<-b라면 a = 2n+1)
=> but 문제예시에선 맞지만, 다른 예시에서 반례가 나온다.

[접근방법2 - 위상정렬]
키순서에는 사이클이 발생할 수 없으므로 그래프의 형태가 DAG이다.
따라서 키순서로 위상정렬이 가능하고, 모든 위상정렬의 경우의 수를 구해서 항상 고정인 애가 있으면 순서가 확정이다.
=> but 간선이 거의 없는 경우, 가능한 키순서 배치를 구하는 경우의 수는 N!에 수렴하게 된다..(N은 최대 500)

[접근방법3 - 정답]
나보다 작은애랑 큰애가 몇 명인지 확실하다면??
즉, 나보다 작은애 + 나보다 큰애 가 자신을 제외한 N - 1이 된다면 자기순서가 고정일 것이다.
이걸 깨닫고나니 구현은 쉬웠다.

시간복잡도: 정방향 bfs O(N+M), 역방향 bfs O(N+M) -> 이걸 N번 반복하면 O(N(N+M))

 * 
 * */
public class Solution_5643_박소영{
	    static int ans, N;
	    static int cnt = 0;
	    static List<List<Integer>> li = new ArrayList<>(); // 정방향 인접리스트
	    static List<List<Integer>> li2 = new ArrayList<>(); // 역방향 인접리스트
	    static Queue<Integer> q = new LinkedList<>();
	    static boolean[] visited; 

	    public static void bfs(int start) {
	        int small = 0; // 자신보다 작은 학생 수
	        int tall = 0; // 자신보다 큰 학생 수

	        Arrays.fill(visited,false);
	        
	        q.offer(start);
	        visited[start] = true;

	        while(!q.isEmpty()) {
	            int x = q.poll();
	            for(int i = 0; i < li.get(x).size(); i++) {
	                int y = li.get(x).get(i);
	                if(!visited[y]) {
	                    q.offer(y);
	                    visited[y] = true;
	                    tall += 1;
	                }
	            }
	        }


	        // 역방향 그래프 탐색
	        Arrays.fill(visited,false);

	        q.offer(start);
	        visited[start] = true;

	        while(!q.isEmpty()) {
	            int x = q.poll();
	            for(int i = 0; i < li2.get(x).size(); i++) {
	                int y = li2.get(x).get(i);
	                if(!visited[y]) {
	                    q.offer(y);
	                    visited[y] = true;
	                    small += 1;
	                }
	            }
	        }

	        // 순서가 확정된 학생 count + 1
	        if(tall+small == N-1) ans++;
	    }

	    public static void main(String[] args) throws Exception
	    {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        StringTokenizer st;
	        
	        int T = Integer.parseInt(br.readLine());

	        for(int t=1; t<=T; t++){
	            ans = 0;
	            N = Integer.parseInt(br.readLine()); // 학생 수
	            int M = Integer.parseInt(br.readLine()); // 키 비교 횟수
	            visited = new boolean[N+1];
	            
	            // 인접리스트 2개에 저장
	            for(int m=0; m<M; m++){
	                st = new StringTokenizer(br.readLine());
	                int a = Integer.parseInt(st.nextToken());
	                int b = Integer.parseInt(st.nextToken());

	                li.get(a).add(b);
	                li2.get(b).add(a);
	            }

	            // 모든 학생에 대해, 자신보다 큰/작은 학생 수를 bfs로 탐색
	            for(int i=1; i<=N; i++){
	                bfs(i);
	            }

	            System.out.printf("#%d %d\n",t,ans);
	        }
	    }
	}