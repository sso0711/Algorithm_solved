import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        int cnt = 0;
        Arrays.sort(citations);
        
        for(int i=citations.length-1; i>=0; i--){
            cnt ++;
            if(cnt > citations[i]){
                answer = cnt-1;
                break;
            } else if(cnt == citations[i]){
                answer = cnt;
                break;
            }
            answer = cnt;
        }
        return answer;
    }
}