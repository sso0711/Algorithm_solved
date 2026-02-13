package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Solution_4130_박소영 {

	/* 중복순열 문제같지만, 주어진 회전 순서에 따라 계산만 하면 되는 구현 문제이다.
	 * 시계, 반사계 방향에 따라 앞뒤 모두 출입이 가능해야하므로 덱을 써야한다.
	 * 
	 * 주의 !!
	 * 톱니 회전 전에 자석의 상태를 보고 돌릴 자석을 선택해야한다. (돌리고 옆에 보고 돌리고 옆에보고 X)
	 * 
	 * [방법1] 구현
	 * 돌릴 자석 확인(인덱스 2,6)해서 여부 저장
	 * 이에 따라 회전
	 * 
	 *
	 * [방법2] 재귀 
	 * 회전하는 자석 관점에서 주변자석 회전시키기         f(자석번호, 방향)
	 * (주의 : 자석마다 좌,우,좌우 모두 이렇게 회전시켜야 하는 자석의 방향이 정해져있다.) -> 그냥 양쪽 다 돌려버리도, visited 배열로 자동 제외됨
	 * 
	 * 재귀함수로 타고 들어가서 회전이 다 끝날 때까지 자신은 회전하지 않기 때문에, 회전확인 후 회전이 보장된다 !
	 * 
	 * 
	 * */
	
	static int T,ans;
	static boolean visited[] = new boolean[5];
	static LinkedList<Integer>[] arr = new LinkedList[5];


	static LinkedList<Integer> deque1;
	static LinkedList<Integer> deque2;
	static LinkedList<Integer> deque3;
	static LinkedList<Integer> deque4;
	
	public static void f(int num, int clockWise) {
		if(num<=0 || num>4) {
			return;
		}
		
		if(!visited[num]) {
			visited[num] = true;
			
			// 1차 시도 보완 (num=4, num=1인 양끝톱니는 더 이상 옆 자석을 회전시킬 필요가 없다.)
			if(num<4 && !arr[num].get(2).equals(arr[num+1].get(6))) f(num+1,clockWise==1?-1:1);
			if(num>1 && !arr[num].get(6).equals(arr[num-1].get(2))) f(num-1,clockWise==1?-1:1);
			
			// 마지막에 자신 회전
			if(clockWise==1) {
				arr[num].addFirst(arr[num].removeLast());
			} else if(clockWise == -1) {
				arr[num].addLast(arr[num].removeFirst());
			}
			// vistied[]=false;  // 돌린 자석을 다시 원상복귀할 필요가 없다.
		}
	}
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        for(int j=1; j<=4; j++) arr[j] = new LinkedList<>();


        T = Integer.parseInt(br.readLine());
    
        for(int i=1; i<=T; i++){
            st = new StringTokenizer(br.readLine());

            int count = Integer.parseInt(st.nextToken());
            
            
            for(int j=1;j<=4;j++) arr[j].clear(); // 배열은 초기화필요 없지만, LinkedList는 계속 add된다.
            
            for (int j = 1; j <= 4; j++) {
    			st = new StringTokenizer(br.readLine());
    			for(int k=0; k<8; k++) {
    				arr[j].add(Integer.parseInt(st.nextToken()));
    			}
            }
            
            // count 횟수만큼 회전
            for(int j=0; j<count; j++) {
            	st = new StringTokenizer(br.readLine());
            	
	            int num = Integer.parseInt(st.nextToken());
	            int clock = Integer.parseInt(st.nextToken());
	            
	            Arrays.fill(visited, false); // 위치 수정
	            f(num,clock);
            }
            
            ans = 0;
            
            // 회전 후 점수계산
            for(int j=1; j<=4; j++) {
            	Integer a= arr[j].get(0);
            	if(a==1) {
            		ans += (1 << (j-1)); // 의도 : ans += 2^(j-1);
            	}
            }
            
            System.out.printf("#%d %d\n",i,ans);
        }
    }

}

