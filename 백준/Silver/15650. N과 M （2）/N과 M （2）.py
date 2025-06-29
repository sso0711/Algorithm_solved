import sys
import itertools

input = sys.stdin.readline

N,M = list(map(int, input().split()))

arr = list(range(1,N+1))

ans = list(itertools.combinations(arr,M))

for j in ans:
  print(*j)