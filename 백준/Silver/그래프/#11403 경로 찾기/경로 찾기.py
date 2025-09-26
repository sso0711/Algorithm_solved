import sys
from collections import deque
# sys.setrecursionlimit(10**6)
input = sys.stdin.readline

n = int(input())  # 정점의 갯수
adj = [list(map(int, input().rstrip().split())) for _ in range(n)]
ans = [[0] * n for _ in range(n)]


def dfs(x):
  for i in range(n):
    if adj[x][i]==1 and not visited[i]:
      ans[a][i] = 1 # dfs(a)를 확인 중이므로
      visited[i] = True # 방문 체크를 여기서 해야 사이클이 있는 경우에 a->a 가 가능한지 확인할 수 있다. 
      dfs(i)


for a in range(n):
  visited = [False] * n
  dfs(a) # 한 정점 a에서 모든 정점으로 가는 경우 체크


for k in ans:
  print(*k)