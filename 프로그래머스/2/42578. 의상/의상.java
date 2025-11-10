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

        /* stream 풀이
        return Arrays.stream(clothes)
        .collect(groupingBy(p -> p[1], mapping(p -> p[0], counting())))
        .values()
        .stream()
        .collect(reducing(1L, (x, y) -> x * (y + 1))).intValue() - 1;
        */
    }
}
