import sys
from collections import deque
input = sys.stdin.readline

n,k = list(map(int, input().split())) # 수빈, 동생 위치
visited = [0] * (10**5 +1)


def bfs(x):
    q = deque([x])

    while q:
        v = q.popleft()
        if v == k:
            print(visited[v])
            break
        for i in (v-1,v+1,2*v):
            if 0<=i<=10**5 and not visited[i]:
                q.append(i)
                visited[i] = visited[v] +1
                
bfs(n)