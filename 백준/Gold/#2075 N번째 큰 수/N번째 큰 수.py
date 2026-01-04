import sys
import heapq

input = sys.stdin.readline

n = int(input())
q = [] # 최댓값 5개만 저장된다.

for _ in range(n):
  for x in list((map(int,input().rstrip().split()))):
    # 처음 5개는 전부 힙에 push
    if len(q) < n:
      heapq.heappush(q,x)

    # 힙의 최솟값보다 크면
    if x > q[0]:
      # pop -> push(x)
      heapq.heapreplace(q,x)

    # 힙의 최솟값보다 작은 경우는 저장하지 않고 무시하게 된다.


print(q[0])