import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

n = int(input())
graph = [list(map(int, input().split())) for _ in range(n)]
visited = [False]*n
res = float("inf")

def dfs(cnt,idx) :
  global res, n
  # 팀원이 절반으로 나눠짐
  # 이제 능력치 계산
  if cnt == n // 2 :
    start, link = 0,0
    for i in range(n-1): # n-1까지인 이유는 n=5인 경우에 graph[3][4]가 최대이므로
      for j in range(i+1,n):
        if visited[i] and visited[j]:
          start += graph[i][j] + graph[j][i]
        elif not visited[i] and not visited[j]:
          link += graph[i][j] + graph[j][i]
    res = min(res, abs(start-link))
    
  # 팀 구성 과정. visited가 1인 팀과 0인 팀으로 나뉘게 됨.
  for i in range(idx,n):
    if not visited[i] :
      visited[i] = True
      dfs(cnt+1, i+1)
      visited[i] = False
      
  return res

print(dfs(0,0))
