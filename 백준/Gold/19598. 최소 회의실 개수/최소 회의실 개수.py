import sys, heapq
input = sys.stdin.readline

n = int(input()) # 강의 갯수
arr = [list(map(int,input().split())) for _ in range(n)] 
q = []

arr.sort(key=lambda x: x[0])

for st, end in arr:
    if q and q[0] <= st:
        heapq.heappop(q)
    heapq.heappush(q, end)


print(len(q))