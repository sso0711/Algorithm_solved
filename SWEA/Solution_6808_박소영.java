package com.ssafy.algorithm;


import java.util.*;
import java.io.*;

// tip: each public class is put in its own file
public class Solution_6808_박소영 {
    static int T; 
    static int[] kyu = new int[9];

    static List<Integer> inyoung = new ArrayList<>();
    static boolean[] visited = new boolean[9];
    static int [] ans = new int [9];
    static int win, lose = 0;

    public static void f(int cnt, int kSum, int iSum){
        if(cnt == 9){
            if(kSum > iSum) win++;
            else lose++;
            return;   // 없으면 아래코드 무한반복 + ans[9] 인덱스오류
        }

        for(int i=0; i<9; i++){
            if(!visited[i]){
                visited[i] = true;
                int sum = kyu[cnt] + inyoung.get(i);

                if(kyu[cnt] > inyoung.get(i))
                    f(cnt+1, kSum + sum, iSum); // kSum + sum을 밖에 쓰면, f 뒤에 더한 sum을 다시 빼주어야 한다.
                else
                    f(cnt+1, kSum, iSum + sum);

                visited[i] = false;
            }
        }
    }


    // 해당 경우의 수에 대한 점수 계산
//    public static void countScore(){
//        int k = 0;
//        int in = 0;
//
//        for(int i=0; i<9; i++){
//            if(kyu[i] > ans[i]) k += kyu[i] + ans[i];
//            else in += kyu[i]+ ans[i];
//        }
//
//        if (in > k) win++;
//        else lose++;
//    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        T = Integer.parseInt(br.readLine());
    
        for(int i=1; i<=T; i++){
            st = new StringTokenizer(br.readLine());

            // 규영의 카드 순서 입력받기
            for(int j=0; j<9; j++){
                kyu[j] = Integer.parseInt(st.nextToken());
            }
            
			inyoung.clear();
            win = 0;
            lose = 0;
            Arrays.fill(visited, false);

            
            // 인영의 카드 목록
            /*
            for(Integer j=1; j<=18; j++){
                inyoung.add(j);
            }
            for(int j=0; j<9; j++){
                inyoung.remove(Integer.valueOf(kyu[j])); // 값 삭제
            }
*/
           
            boolean[] used = new boolean[19];

            for(int j=0;j<9;j++){
                used[kyu[j]] = true;
            }

            for(int j=1;j<=18;j++){
                if(!used[j]) inyoung.add(j);
            }


            f(0,0,0);
            
            System.out.printf("#%d %d %d\n",i,win,lose);
        }
    }
}


        
        
 

