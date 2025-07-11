import sys
from collections import deque
input = sys.stdin.readline

n,m = list(map(int,input().rstrip().split()))
visited = [False] * (n+1)
adj = [[] for _ in range(n+1)]
cnt = 0 # 연결 요소 갯수

for _ in range(m):
  u,v = list(map(int,input().rstrip().split()))
  adj[u].append(v)
  adj[v].append(u)

def bfs(x):
  q = deque([x])
  visited[x] = True

  while q:
      v = q.popleft()
      for i in adj[v]:
          if not visited[i]:
              q.append(i)
              visited[i] = True


for i in range(1,n+1):
  if not visited[i]:
    bfs(i)
    cnt += 1

print(cnt)