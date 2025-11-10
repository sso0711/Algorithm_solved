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
            }
            answer = cnt; // 이 부분이 없으면 [5,6,7,8] -> 4 인 테스트케이스를 통과하지 못한다.
                          // for문이 다 끝나도 cnt >= citations[i] 인 경우를 찾지 못하기 때문
                          // 이 경우엔 cnt 번 이상 인용된 논문의 갯수가 cnt 개 이상인 것이므로 이것이 h-index가 된다.
        }
        return answer;
    }
}
