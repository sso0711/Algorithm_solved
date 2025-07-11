import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

t = int(input())
graph = [list(input()) for _ in range(t)]
visited = [[False]*t for _ in range(t)]
dx, dy = [1,0,-1,0], [0,-1,0,1]
cnt_o, cnt_x = 0,0

def dfs(x,y):
  visited[x][y] = True
  for i in range(4):
    nx, ny = x + dx[i], y + dy[i]
    if 0<=nx<t and 0<=ny<t and not visited[nx][ny] and graph[x][y]==graph[nx][ny]:
      dfs(nx, ny)
      
# 적록색약이 아닌 경우
for i in range(t):
  for j in range(t):
    if not visited[i][j]:
      dfs(i,j)
      cnt_x += 1


# 적록색약인 경우
visited = [[False]*t for _ in range(t)]

for i in range(t):
  for j in range(t):
    if graph[i][j]=='G':
      graph[i][j] = 'R' # 적록 통일
      
for i in range(t):
  for j in range(t):
    if not visited[i][j]:
      dfs(i,j)
      cnt_o += 1
      
print(cnt_x, cnt_o)