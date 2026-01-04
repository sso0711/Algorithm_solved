import sys
from collections import deque

input = sys.stdin.readline

n = int(input())
nums = list(map(int,input().rstrip().split()))
dp = [0] * n
# dp[i]는 i번째 수열까지의 LIS
dp[0] = 1

for i in range(1,n):
  dp[i] = 1
  # 인덱스 i보다 작은 원소들 모두 검사
  for j in range(i):
    if nums[j] < nums[i]:
      dp[i] = max(dp[i],dp[j] + 1)


print(max(dp))