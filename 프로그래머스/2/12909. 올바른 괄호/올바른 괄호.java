class Solution {
    boolean solution(String s) {
        int count = 0; // 열린 괄호 '('의 개수를 세는 변수

        for (String c : s.split("")) {
            if (c.equals("(")) count++;
            else count--;

            // ')'가 '('보다 먼저 나온 경우 (음수되면 잘못된 괄호)
            if (count < 0) return false;
        }

        // 모든 괄호가 짝이 맞으면 0, 아니면 false
        return count == 0;
    }
}
