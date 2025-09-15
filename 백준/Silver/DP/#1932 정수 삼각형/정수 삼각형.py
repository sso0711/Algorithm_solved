import sys
# sys.setrecursionlimit(10**6)
input = sys.stdin.readline

n = int(input())
dp = [list(map(int,input().rstrip().split())) for _ in range(n)]

for i in range(1,n):
    for j in range(i+1):
        if j==0: # 맨 왼쪽일 경우 항상 대각선 오른쪽이 더해짐
            dp[i][j] += dp[i-1][0]
        elif j==i: # 맨 오른쪽일 경우 항상 대각선 왼쪽이 더해짐
            dp[i][j] += dp[i-1][-1]
        else: # 가운데 경우
            dp[i][j] += max(dp[i-1][j-1], dp[i-1][j])

print(max(dp[n-1]))