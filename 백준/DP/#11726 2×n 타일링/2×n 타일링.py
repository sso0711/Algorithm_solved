import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

n = int(input())
dp = [0]*1001

dp[1] = 1
dp[2] = 2

for k in range(3,n+1):
    dp[k] = dp[k-1] + dp[k-2]

print(dp[n] %10007)