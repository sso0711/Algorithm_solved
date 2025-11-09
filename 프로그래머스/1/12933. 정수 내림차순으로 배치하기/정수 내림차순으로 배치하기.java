import java.util.*;

class Solution {
    public long solution(long n) {
        long answer = 0;
        
        // 내 풀이
        String str = Long.toString(n);
        String[] arr = str.split("");
        Arrays.sort(arr,Collections.reverseOrder());
        String str2 = String.join("",arr);
        answer = Long.parseLong(str2);

        /*
        String str = Long.toString(n);
        String[] arr = str.split("");
        Arrays.sort(arr); // 오름차순 정렬
        
        String str2 = String.join("",arr);
        
        // 4. 내림차순으로 뒤집어서 문자열 생성
        StringBuilder sb = new StringBuilder(str2);
        sb.reverse(); // 내림차순으로 변경
        
        // 5. 다시 long 타입으로 변환하여 리턴
        answer = Long.parseLong(sb.toString());
        */
        
        return answer;
    }
}