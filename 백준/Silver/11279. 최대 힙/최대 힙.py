import sys
import heapq
input = sys.stdin.readline

n = int(input()) # 연산 갯수
arr = [int(input()) for _ in range(n)]
q = []

for i in arr:
    
    if i==0:
        if len(q) > 0:
            print(-heapq.heappop(q))
        else:
            print(0)
    # x가 자연수
    else:
        heapq.heappush(q,-i)