package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/*
dfs,백트래킹을 이용하는 건 알았으나 구현이 어려웠다.
(NQueen과 유사한 문제!)

1차 시도 : forward함수 내에서 전선을 더이상 놓을 수 없으면 지금까지 깐 전선을 다시 돌려놨다. (이건 ok)
그런데 해당방향의 forward 함수가 성공한 경우에도 (즉, 전선을 한방향으로 쭉 깔기 성공) (line47)
다음 dfs재귀 밑에 전선을 되돌려놓는 작업이 필요한 것을 놓쳤다.

2차 시도 : 가지치기를 해주지 않으면 시간초과.
문제에서 드러나는 가지치기 요소가 아니어서 찾기 어려웠다.

3차 시도 : lengthSum은 최소값을 구하는 것이기 때문에 MAX_VALUE로 초기화해줘야 한다. (0 아님!!)

4차 시도 : 특정 direction으로 놓기 전에, 가능한지 확인하는 checkLine 함수 추가
*/
public class Solution_1767_박소영 {
    static int N, maxCore, lengthSum;
    static int[][] map;
    static boolean[][] visited;
    static List<int[]> core = new ArrayList<>();
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};

    // 전선을 잇는 경우를 완전탐색(백트래킹)
    // now: 현재까지 연결시도한 코어 갯수, c:현재까지 연결된 코어 갯수, l:현재 연결된 전선의 길이
    public static void dfs(int now, int c, int l) {
        // 2차 시도 : 가지치기 필요
        if(c + (core.size() - now) < maxCore) return; // 남은 코어로도 최대 코어 도달못하면 탐색 X
        if(c == maxCore && l >= lengthSum) return; // 이미 최소길이를 넘어버렸다면 탐색 X

        if(now == core.size()) { // 모든 코어에 대한 연결 시도 완료
            if(c > maxCore) { // 이전까지의 최대코어(maxCore)보다 더 많이 연결했으면 갱신
                maxCore = c;
                lengthSum = l;
            } else if(c == maxCore) {
                lengthSum = Math.min(lengthSum, l);
            }
            return;
        }

        int coreX = core.get(now)[0];
        int coreY = core.get(now)[1];

        for(int direction=0; direction<4; direction++) { // A지점
//          List<int[]> path = new ArrayList<>(); // 전선 좌표 저장
            if(!checkLine(coreX,coreY,direction)) continue; // 4차시도 보완 : 먼저 가능한지 체크 후 전선놓기!
            
            int f = forward(coreX, coreY, direction, 2);//해당 방향으로 전선 쭉 놓기 시도
//          if(f != 0) { // 전선을 끝까지 놓아 전선의 length가 return됐다면
            dfs(now+1, c+1, l+f); // 다음 코어 탐색
            // 1차 시도 : 여기서 2로 전선 놓은 곳들을 다시 0으로 돌려놔야 함!
//          	for(int[] p : path){
//                  map[p[0]][p[1]] = 0;
//              }
            
            // 위처럼 하기보다, forward 함수 재활용 !
            forward(coreX, coreY, direction, 0);
        }
        // 4방향 다 돌았는데 연결 못함 
        dfs(now+1, c, l);
    }

    private static boolean checkLine(int x, int y, int d) {
    	int nx = x; int ny = y;
    	
    	while(true) {
            nx += dx[d]; // 같은 방향으로 한 칸씩 전진
            ny += dy[d];

            // 전선을 끝까지 놓은 상태
            if(nx<0 || ny<0 || nx>=N || ny>=N) break;
            else if(map[nx][ny]>=1) return false; // 가다가 막히면
        }
        
    	return true;
    }

    public static int forward(int x, int y, int d, int n) {
        int localLengthSum = 0;

        while(true) {
            x += dx[d]; // 같은 방향으로 한 칸씩 전진
            y += dy[d];

            // 전선을 끝까지 놓은 상태
            // 아래 if문보다 먼저 와야 함
            if(x<0 || y<0 || x>=N || y>=N){
                return localLengthSum;
            }

            // 전선을 놓을 수 없으면 다음방향 탐색 시작 (A지점)
//          if(map[x][y] != 0) {
//              // 지금까지 깔아둔 전선 되돌리기
//              for(int[] p : path){
//                  map[p[0]][p[1]] = 0;
//              }
//              return 0;
//          }

            map[x][y] = n;
//          path.add(new int[]{x,y}); // 백트래킹 시 전선 2->0 돌려놓기 위해 경로 저장
            localLengthSum ++;
        }
    }



    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tcase=1; tcase<=T; tcase++){
            st = new StringTokenizer(br.readLine());

            N= Integer.parseInt(st.nextToken());
            map = new int[N][N];
            visited = new boolean[N][N];
            core.clear();

            maxCore = 0;
            lengthSum = Integer.MAX_VALUE; // 3차 시도

            // 맵 정보 저장 + core 좌표 저장
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j]==1 && i>=1 && j>=1 && i<N-1 && j<N-1) { // 가장자리의 코어가 아니라면!!
                        core.add(new int[]{i,j});
                    }
                }
            }

            dfs(0,0,0);

            System.out.printf("#%d %d\n",tcase,lengthSum);
        }
    }
}