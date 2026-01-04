import sys
from collections import deque
input = sys.stdin.readline

v, e = map(int, input().split())
indegree = [0] * (v + 1)
graph = [[] for _ in range(v + 1)]
semester = [1] * (v + 1)  # 최소 1학기부터 시작

# 선수과목 정보 입력
for _ in range(e):
    a, b = map(int, input().split())
    graph[a].append(b)
    indegree[b] += 1

def topology_sort():
    q = deque()

    for i in range(1, v + 1):
        if indegree[i] == 0:
            q.append(i)

    while q:
        now = q.popleft()
        for next in graph[now]:
            indegree[next] -= 1
            # 선수과목보다 최소 1학기 뒤에 수강 가능
            semester[next] = max(semester[next], semester[now] + 1)
            if indegree[next] == 0:
                q.append(next)

    print(*semester[1:])

topology_sort()
