import sys
from collections import deque
input = sys.stdin.readline

n = int(input())
graph = [list(map(int,input().rstrip())) for _ in range(n)]
dx,dy = [1,0,-1,0],[0,-1,0,1]
cnt = [] # 구역별 집 갯수 저장
ans = 0 # 총 구역갯수

def bfs(x,y):

  q = deque([[x,y]]) # 시작 노드 큐에
  graph[x][y] = 0 # 다시 방문하지 않도록
  count = 1 # 한 구역 내 집의 갯수
  
  while q: # 큐가 빌 때 까지
    x,y = q.popleft()

    for i in range(4):
        nx, ny = x+dx[i], y+dy[i]
        if 0<=nx<n and 0<=ny<n and graph[nx][ny]==1:
            graph[nx][ny]=0
            q.append([nx,ny])
            count += 1
          
  return count


for i in range(n):
  for j in range(n):
    if graph[i][j] == 1:
      cnt.append(bfs(i,j))
      ans += 1

cnt.sort()
print(ans)
for i in cnt:
  print(i)