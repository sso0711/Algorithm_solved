import sys
# sys.setrecursionlimit(10**6)
input = sys.stdin.readline

n = int(input())
arr = [0]
dp = [0] * (n+1)

for _ in range(n):
  arr.append(int(input()))

def f(x):
  if x<1:
    return 0
  
  if x==1:
    return arr[1]
    
  if x==2:
    return arr[1]+arr[2]
    
  if dp[x] != 0:
    return dp[x]

  dp[x] = max(arr[x]+f(x-2), arr[x]+arr[x-1]+f(x-3))
  return dp[x]

print(f(n))