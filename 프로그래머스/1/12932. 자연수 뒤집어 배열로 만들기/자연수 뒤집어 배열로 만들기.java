class Solution {
    public int[] solution(long n) {
        String str = Long.toString(n);
        StringBuilder sb = new StringBuilder(str);
        String reversed = sb.reverse().toString();
        String[] arr = reversed.split("");
        
        int[] answer = new int[arr.length];
        for(int i=0; i<arr.length; i++){
            answer[i] = Integer.parseInt(arr[i]);
        }
        return answer;
    }
}