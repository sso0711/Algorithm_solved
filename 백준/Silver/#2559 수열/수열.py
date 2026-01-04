import sys
import heapq

input = sys.stdin.readline

n,k = list(map(int,input().rstrip().split()))
arr = list(map(int,input().rstrip().split()))

sum_k = sum(arr[:k])
max_sum = sum_k

for i in range(n-k):
  sum_k += arr[k+i] - arr[i]
  max_sum = max(sum_k, max_sum)

print(max_sum)