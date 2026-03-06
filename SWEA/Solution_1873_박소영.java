import java.util.*;
import java.io.*;
/*
 * 시뮬레이션 문제. 
 * 
 * 방향을 바꾸고 전차가 이동하는 U,D,L,R은 로직이 거의 비슷하다.
 * 그러나 S (shoot) 의 경우, 전차를 한 칸만 이동하고 끝나는 U,D,L,R과 달리, 전차를 이동하지 않고 & 횟수제한없이 포탄이 벽에 부딪힐때까지 한방향으로 탐색을 계속하므로 별도 함수로 분리 
 *
 * +) 재귀 시 스택메모리 계산 연습
 * 	  재귀 깊이는 명령의 길이인 N이고, dfs의 지역변수 5개(20bytes) + return address 등을 합하면 stack frame은 대략 40~80bytes로 잡으면
 * 	  N * 40~80 < 1MB 안에 가능
 *
 */
public class Solution_1873_박소영 {
    static int H, W, N, directionIdx;
    static char[][] map;
    static String[] inputArr;
    static int[] dx = {-1,1,0,0}; // 상하좌우
    static int[] dy = {0,0,-1,1};

    // 포탄의 탐색
    static void dfs_shoot(int x, int y, int directionIdx) {
        int nx = x;
        int ny = y;

        while(true){
            nx = nx + dx[directionIdx];
            ny = ny + dy[directionIdx];
            
            if(nx<0 || ny<0 || nx>=H || ny>=W) break;
            
            if(map[nx][ny] == '*') {
                map[nx][ny] = '.';
                break;
            }
            if(map[nx][ny] == '#') break;
            // 이 외에 물/땅 이면 while문을 계속 진행한다.
        }
    }

    // 전차의 탐색 (userInputIdx는 inputArr의 몇번째 동작을 실행할건지)
    static void dfs(int x, int y, int userInputIdx) {
        if(userInputIdx == N) return;

        if(inputArr[userInputIdx].equals("S")) {
            if(map[x][y] == '^') directionIdx = 0;
            else if(map[x][y] == 'v') directionIdx = 1;
            else if(map[x][y] == '<') directionIdx = 2;
            else if(map[x][y] == '>') directionIdx = 3;

            dfs_shoot(x,y,directionIdx);
            dfs(x,y,userInputIdx+1);

        } else if(inputArr[userInputIdx].equals("U")) {
            map[x][y] = '^'; // 1.방향을 바꾼다.
            directionIdx = 0;
            int nx = x + dx[directionIdx];
            int ny = y + dy[directionIdx];
            
            if(nx>=0 && nx<H && ny>=0 && ny<W) {
            	// 2.평지면 한 칸 이동
                if(map[nx][ny] == '.') {
                    map[nx][ny] = '^';
                    map[x][y] = '.';
                    dfs(nx,ny,userInputIdx+1);
                    return; // 1차 시도 보완 : 아래의 같은 인덱스의 dfs가 중복 실행되지 않도록!!
                }
            }
            // 맵을 벗어났거나 / 물, 벽돌벽, 강철벽이면 이동하지 않고(x,y위치 그대로) 다음 동작 실행
            dfs(x,y,userInputIdx+1);

        } else if(inputArr[userInputIdx].equals("D")) {
            map[x][y] = 'v';
            directionIdx = 1;
            int nx = x + dx[directionIdx];
            int ny = y + dy[directionIdx];

            if(nx>=0 && nx<H && ny>=0 && ny<W) {
                if(map[nx][ny] == '.') {
                    map[nx][ny] = 'v';
                    map[x][y] = '.';
                    dfs(nx,ny,userInputIdx+1);
                    return;
                }
            }
            dfs(x,y,userInputIdx+1);

        } else if(inputArr[userInputIdx].equals("L")) {
            map[x][y] = '<';
            directionIdx = 2;
            int nx = x + dx[directionIdx];
            int ny = y + dy[directionIdx];

            if(nx>=0 && nx<H && ny>=0 && ny<W) {
                if(map[nx][ny] == '.') {
                    map[nx][ny] = '<';
                    map[x][y] = '.';
                    dfs(nx,ny,userInputIdx+1);
                    return;
                }
            }
            dfs(x,y,userInputIdx+1);

        } else if(inputArr[userInputIdx].equals("R")) {
            map[x][y] = '>';
            directionIdx = 3;
            int nx = x + dx[directionIdx];
            int ny = y + dy[directionIdx];

            if(nx>=0 && nx<H && ny>=0 && ny<W) {
                if(map[nx][ny] == '.') {
                    map[nx][ny] = '>';
                    map[x][y] = '.';
                    dfs(nx,ny,userInputIdx+1);
                    return;
                }
            }
            dfs(x,y,userInputIdx+1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int tcase=1; tcase<=T; tcase++) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            StringBuilder sb = new StringBuilder();
            sb.append("#").append(tcase).append(" ");

            map = new char[H][W];

            // 맵 입력받기 (split 제거)
            for(int i=0; i<H; i++) {
                String str = br.readLine();
                for(int j=0; j<W; j++) {
                    map[i][j] = str.charAt(j);
                }
            }

            N = Integer.parseInt(br.readLine());
            inputArr = br.readLine().split("");

            int flag = 0;
            for(int i=0; i<H; i++) {
                for(int j=0; j<W; j++) {
                    if(map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<' || map[i][j] == '>') {
                        dfs(i,j,0);
                        flag = 1;
                        break;
                    }
                }
                if(flag==1) break;
            }

            for(int i=0; i<H; i++) {
                for(int j=0; j<W; j++) {
                    sb.append(map[i][j]);
                }
                if(i != H-1) {
                	sb.append("\n");
                }
            }

            System.out.println(sb);
        }
    }
}