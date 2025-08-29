import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

m,n = list(map(int, input().rstrip().split())) # 세로,가로
graph = [list(map(int,input().rstrip().split())) for _ in range(m)]
dx,dy = [1,0,-1,0],[0,1,0,-1]
dp = [[-1]*n for _ in range(m)]

def dfs(x,y):
  if x==m-1 and y==n-1:
      return 1
  if dp[x][y]==-1:
      dp[x][y] = 0 # 메모
      for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if 0<=nx<m and 0<=ny<n and graph[nx][ny] < graph[x][y]:
            dp[x][y] += dfs(nx,ny)

  return dp[x][y]

print(dfs(0,0))