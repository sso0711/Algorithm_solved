import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        int last_idx = people.length - 1;
        
        // 내림차순 정렬
        Integer[] newPeople = Arrays.stream(people).boxed().toArray(Integer[]::new);
        Arrays.sort(newPeople, Collections.reverseOrder());
        
        for(int i=0; i<newPeople.length ;i++){
            Integer sumOfWeight = newPeople[i] + newPeople[last_idx];
            
            if(i>=last_idx) {
                answer++;
                break;
            }
            
            if (sumOfWeight <= limit) { 
                last_idx--; // 두 번째 탈 사람을 구했으므로
                answer++;
                
                if(i==last_idx) break;

            } else{
                answer++; // 두 명이 탈 수 없음. (last_idx는 그대로, 다음 반복의 i번 사람과 태울 수 있는지 찾는다)
            }
            
        }
        return answer;
    }

}
// 그리디
