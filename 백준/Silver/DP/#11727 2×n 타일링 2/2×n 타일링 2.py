import sys
# sys.setrecursionlimit(10**6)
input = sys.stdin.readline

n = int(input())
dp = [0] * (n+1)

def f(x):
    if x == 1:
        dp[1] = 1
        return dp[x]
    
    if x == 2:
        dp[2] = 3
        return dp[x]
	  
    if dp[x] != 0:
        return dp[x]
    
    dp[x] = f(x-2) * 2 + f(x-1)
    return dp[x]

print(f(n) % 10007)
