import java.util.*;

class Solution {
    int[] answer = {0,0};
    // answer에 담기 위한 변수
    int totalZeroCount = 0;
    int totalChangeCount = 0;
    
    public int[] solution(String s) {

        int zeroCount = countZero(s);
        int oneCount = s.length() - zeroCount;
        String binaryNum = Integer.toBinaryString(oneCount); // 이진수 변환 함수
        
        totalZeroCount += zeroCount;
        totalChangeCount++;
            
        if (binaryNum.equals("1")){
            answer[0] = totalChangeCount;
            answer[1] = totalZeroCount;
            return answer;
        }
        return solution(binaryNum); // 재귀
    }
    
    public int countZero(String s){
        int count = 0;
        for (int i=0; i<s.length(); i++){
                if(s.charAt(i)=='0'){
                    count++;
                }
            }
        return count;
    }
}

