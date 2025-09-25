import sys
from collections import deque
# sys.setrecursionlimit(10**6)
input = sys.stdin.readline

n = int(input()) # 테스트 케이스 갯수
dx, dy = [1,1,2,2,-1,-1,-2,-2], [2,-2,1,-1,2,-2,1,-1]


def bfs(x,y):
  q = deque([[x,y]])

  while q: # 큐가 빌 때 까지
    x,y = q.popleft()

    if x == c and y == d:
      return visited[x][y]
      
    for i in range(8):
        nx, ny = x+dx[i], y+dy[i]
        if 0<=nx<l and 0<=ny<l and not visited[nx][ny]:
            q.append([nx,ny])
            visited[nx][ny] = visited[x][y] + 1
  


for _ in range(n):
    l = int(input()) # 체스판 한 변의 길이
    visited = [[0]*l for _ in range(l)] # 방문표시 대신 이동 횟수 저장
    cnt = 0
    a,b = list(map(int,input().rstrip().split()))
    c,d = list(map(int,input().rstrip().split()))
    print(bfs(a,b))