import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> kindToCountMap = new HashMap<>(); // (kind, count)
        
        for(int i=0; i<clothes.length; i++){
            String kind = clothes[i][1];
            kindToCountMap.put(kind ,kindToCountMap.getOrDefault(kind,0) + 1);
        }
        
        List<Integer> counts = new ArrayList<>(kindToCountMap.values());
        
        for (int count:counts){
            answer *= (count+1);
        }

        return answer-1;
    }
}