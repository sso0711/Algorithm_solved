import sys
from collections import deque
input = sys.stdin.readline

n = int(input())
graph = [list(map(int,input().rstrip().split())) for _ in range(n)]
visited = [[False]*n for _ in range(n)]
max_val = max(map(max,graph)) # 최대 높이 구하기
dx,dy = [1,0,-1,0],[0,-1,0,1]
cnt = [] # 내리는 비 양에 따른 안전한 영역 갯수
count = 0

def bfs(x,y):

  q = deque([[x,y]]) # 시작 노드 큐에
  visited[x][y] = 1
  
  while q: # 큐가 빌 때 까지
    x,y = q.popleft()

    for i in range(4):
        nx, ny = x+dx[i], y+dy[i]
        if 0<=nx<n and 0<=ny<n and not visited[nx][ny] and graph[nx][ny]:
            visited[nx][ny] = 1
            q.append([nx,ny])
          
  return

for k in range(1,max_val):
  for i in range(n):
    for j in range(n):
      if graph[i][j] == k:
        graph[i][j] = 0

  for i in range(n):
    for j in range(n):
      if graph[i][j] and not visited[i][j]:
        bfs(i,j)
        count += 1

  cnt.append(count)
  count = 0
  visited = [[False]*n for _ in range(n)]

if len(cnt)==0 :
  print(1)
else:
  print(max(cnt))