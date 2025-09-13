import sys
input = sys.stdin.readline

n = int(input()) # row의 길이
board = [input().split() for _ in range(n)]
m = len(board[0]) # col의 길이
dp = [[0]*m for _ in range(n)] 
dp[0][0]=1
def sol():
    for i in range(n):
        for j in range(m):
            k = int(board[i][j])
            # 이동 가능한 거리가 0이라면 넘어간다.
            # 또는 dp가 0이라는 것은 현재 위치에 도달할 수 없음을 의미함으로 넘어간다.
            if k == 0 or dp[i][j] == 0: 
                continue
            if i+k < n: # 아래쪽으로 이동하는 경우, 게임판을 벗어나지 않는다면 경우의 수 추가
                dp[i+k][j] += dp[i][j]
            if j+k < m: # 오른쪽으로 이동하는 경우, 게임판을 벗어나지 않는다면 경우의 수 추가
                dp[i][j+k] += dp[i][j]
    return dp[-1][-1] # 가장 오른쪽 아래에 담긴 경우의 수를 return
print(sol())