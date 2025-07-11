import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

t = int(input())


def dfs(x, y):

  for i in range(4):
    nx, ny = x + dx[i], y + dy[i]
    if 0 <= nx < m and 0 <= ny < n and graph[ny][nx]:
      graph[ny][nx] = 0
      dfs(nx, ny)


for _ in range(t):
  cnt = 0
  m, n, k = list(map(int, input().rstrip().split()))
  graph = [[0] * m for i in range(n)]
  dx, dy = [1, 0, -1, 0], [0, 1, 0, -1]

  for _ in range(k):
    x, y = list(map(int, input().rstrip().split()))
    graph[y][x] = 1 # x y 반대

  for i in range(n):
    for j in range(m):
      if graph[i][j] == 1:
        dfs(j, i)
        cnt += 1

  print(cnt)