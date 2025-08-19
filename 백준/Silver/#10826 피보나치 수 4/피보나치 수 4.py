import sys
from collections import deque
# sys.setrecursionlimit(10**6)
input = sys.stdin.readline


n = int(input())
dp = [0] * 10001

# 첫 번째 피보나치 수와 두 번째 피보나치 수는 1
dp[0] = 0
dp[1] = 1

# 피보나치 함수(Fibonacci Function) 반복문으로 구현
for i in range(2, n + 1):
    dp[i] = dp[i - 1] + dp[i - 2]

print(dp[n])