package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_5648_박소영_강사님코드 {
	/*
	 * [방법1] - 구현
	 * 시간에 따라 이동하며 충돌여부확인 - 계산
	 * 
	 * <어려운 포인트>
	 * 충돌하는 경우를 생각해 볼 때, 같은 칸에서 만나는 경우도 있지만
	 * (2,?) , (3,?) -> (3,?) , (2,?) 로 지나가버리는 경우는 놓치게 된다.
	 * => 좌표 보정 : x2배가 필요했다. 
	 * 	  (총 에너지 합만 구하는 것이기 때문에, 좌표 변형해도 상관없다!)
	 * 
	 * <주의할 점>
	 * map이 크기 때문에 테스트케이스마다 매번 생성하면 메모리 초과가 난다.
	 * => 재활용하기
	 * 그런데 매번 map을 초기화해주지 않아도 된다!
	 * => 충돌 / 이동 후 마지막에 전부 0이 되기 때문!
	 *
	 * <시간복잡도>
	 * 4000번 이동하는 동안 최대1000개의 원자들이 충돌 X -> 400만
	 * 
	 * 
	 * 
	 * 
	 * [방법2]
	 * 모든 원자 조합 쌍을 구해서 충돌가능성이 있는 애들 리스트를 만들어놓자.
	 * 공간복잡도가 많이 줄어든다.
	 * 
	 * <시간복잡도>
	 * 		 조합    X 정렬 
	 * 최악 : 1000C2 X 50만log50만
	 * 최선 : 1000C2 X 3log3
	 * 
	 * => 조합의 결과에 따라 방법 1보다 느릴 수도, 빠를 수도 있다.
	 * 
	 * 
	 * */
	
	static int N, ans; // 경우의 수
    static int[][] map = new int[N][N];
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};


    static class Unit{
    	
    	int x,y,dir,e;
    	
    	public Unit(int x, int y, int dir, int e) {
    		super();
    		this.x = x;
    		this.y = y;
    		this.dir = dir;
    		this.e = e;
    	}
    	
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tcase=1; tcase<=T; tcase++){

        	int ans = 0;
        	int count = Integer.parseInt(br.readLine());
        	
        	ArrayDeque<Unit> dq = new ArrayDeque<>(); // 살아있는 원자 리스트
        	
        	for (int i=0; i<count; i++) {
        		st = new StringTokenizer(br.readLine());
        		int x = (Integer.parseInt(st.nextToken())+1000) << 1;
        		int y = (Integer.parseInt(st.nextToken())+1000) << 1;
        		int dir = Integer.parseInt(st.nextToken());
        		int e = Integer.parseInt(st.nextToken());
        		
        	}
        	
        	while(!dq.isEmpty()) {
        		Unit cur = dq.pollFirst();
        		
        		
        		if(map[cur.y][cur.x] != cur.e) {
        			ans += map[cur.y][cur.x];
        			map[cur.y][cur.x] = 0;
        			continue;
        		}
        		
        		// 충돌하지 않았으면 다음 방향에 기록 후 덱에 넣기
        		map[cur.y][cur.x] = 0;
        		
        		int nx = cur.x + dx[cur.dir];
        		int ny = cur.y + dy[cur.dir];
        		
        		if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
        		
        		cur.x = nx;
        		cur.y = ny;
        		map[cur.y][cur.x] += cur.e;
        		dq.addLast(cur);
        	}
        	System.out.println("#"+tcase+" "+ans);
        }
    }

}
