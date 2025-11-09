class Solution {
    public String solution(String s) {
        String answer = "";
        String[] words = s.split(" ",-1); 
        for(int i=0; i<words.length; i++){
            String word = words[i];
            if (!word.isEmpty()){
                String first = word.substring(0,1);
                String rest = word.substring(1);
                
                word = first.toUpperCase() + rest.toLowerCase();

            }
            words[i] = word;
            
        }
        answer = String.join(" ",words);
        return answer;
    }
}