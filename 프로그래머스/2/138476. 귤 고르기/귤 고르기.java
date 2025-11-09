import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        Map<Integer, Integer> sizeCount = new HashMap<>(); // (숫자, 갯수)
        
        // 각 숫자의 갯수 세기
        for(int n : tangerine){
            sizeCount.put(n, sizeCount.getOrDefault(n,0) + 1); // 원래 저장된 갯수에 +1 해줌
        }
        
        List<Integer> sizeCounts = new ArrayList<>(sizeCount.values()); // 갯수들(values)의 배열 생성
        Collections.sort(sizeCounts,Collections.reverseOrder());  // 내림차순 정렬
        
        // 최대 count들부터 채워야 종류가 최소가 될 것임
        for (int count : sizeCounts) {
            k -= count;
            answer++;  // 귤의 종류 +1
            
            if (k <= 0) {  // k가 0보다 작아졌다는 건 현재 count로 남은 갯수 모두 채울 수 있다는 뜻
                break;
            }
        }
        return answer;
    }
}
// 그리디