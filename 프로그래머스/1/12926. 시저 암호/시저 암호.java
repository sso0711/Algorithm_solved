class Solution {
    public String solution(String s, int n) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        char[] arr = s.toCharArray();
        
        for(char c:arr){
            if(c==' ') sb.append(c);
            else
            sb.append(move(c,n));
        }
        
        
        answer = sb.toString();
        return answer;
    }
    
    private char move(char c,int n){
        int offset = Character.isUpperCase(c) ? 'A' : 'a' ;
        int position = c - offset;
        position = (position + n) % 26;
        return (char)(offset + position);
    }
}