class Solution {
    private static final int[] dx = {0,1,-1}; // 아래, 오른쪽, 왼쪽 위
    private static final int[] dy = {1,0,-1};
    
    public int[] solution(int n) {
        
        int[][] triangle = new int[n][n];
        int num = 1; // 채워넣을 숫자. 1씩 증가.
        int x = 0; // 위치 변수
        int y = 0;
        int d = 0; // 방향 인덱스
        
        while(true){
            triangle[y][x] = num++;
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(nx==n || ny==n || nx==-1 || ny==-1 || triangle[ny][nx]!=0){
                d = (d+1) % 3;
                nx = x + dx[d];
                ny = y + dy[d];
                
                if(nx==n || ny==n || nx==-1 || ny==-1 || triangle[ny][nx]!=0)
                    break;
            }
            x = nx;
            y = ny;
        }
        
        int[] answer = new int[num-1];
        int idx = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<=i ; j++){
                answer[idx] = triangle[i][j];
                idx++;
            }
        }
        
        
        
        return answer;
    }
}