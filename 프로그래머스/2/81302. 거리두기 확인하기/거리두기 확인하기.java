class Solution {
    private static final int dx[] = {0, -1 ,1 ,0}; // 상,좌,우,하
    private static final int dy[] = {-1, 0, 0, 1};
    
    private boolean isDistanced(char[][] room){
        for(int y=0; y<room.length; y++){
            for(int x=0; x<room[y].length; x++){
                if(room[y][x] != 'P') continue; // 모든 P에 대해 검사
                if(!isDistanced(room,x,y)) return false;
            }
        }
        return true;
    }
    
    
    
    private boolean isDistanced(char[][] room, int x, int y){
        for(int d=0; d<4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(ny<0 || ny>=room.length || nx<0 || nx>=room[ny].length) 
                continue; // 다른방향 찾기
            if(room[ny][nx]=='P') return false;
            if(room[ny][nx]=='O') {
                if(isDistanced(room,nx,ny,3-d)) 
                    return false;
            }
        }
        return true;
    }
    
    
    private boolean isDistanced(char[][]room, int x, int y, int exclude){
        for(int d=0; d<4; d++){
            if(d==exclude) continue;
            
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(ny<0 || ny>=room.length || nx<0 || nx>=room[ny].length) 
                continue; // 다른방향 찾기  
            if(room[ny][nx] == 'P') return true;
        }
        return false;
    }
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        
        for(int i=0; i<answer.length; i++){ // 5개의 테스트케이스에 대해 반복
            String[] place = places[i]; // i번째 테스트케이스
            char[][] room = new char[place.length][];
            for(int j=0; j<room.length; j++){ 
                room[j] = place[j].toCharArray();
            }
            
            if(isDistanced(room)){
                answer[i] = 1;
            }else{
                answer[i] = 0;
            }
            
        }
        
        return answer;
    }
}