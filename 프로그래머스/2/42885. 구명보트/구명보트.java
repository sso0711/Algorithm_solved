import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        int last_idx = people.length-1;
        
        Integer[] newPeople = Arrays.stream(people).boxed().toArray(Integer[]::new);
        Arrays.sort(newPeople, Collections.reverseOrder());
        
        for(int i=0; i<newPeople.length ;i++){
            Integer sumOfWeight = newPeople[i] + newPeople[last_idx];
            
            if (i==last_idx){
                answer++;
                break;
            } else if (i>last_idx){
                break;
            }
            
            if (sumOfWeight <= limit) 
                last_idx--;
            
            answer++;
        }
        return answer;
    }
}