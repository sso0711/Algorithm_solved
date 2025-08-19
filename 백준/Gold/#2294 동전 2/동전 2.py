import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

n, k = map(int, input().split())
coins = [int(input()) for _ in range(n)]

dp = [-1] * (k + 1)  # (인덱스)i원을 만들었을 때의 동전의 최소 갯수

def f(w):
    if w < 0:
        return 10001
    if w == 0:
        return 0
    if dp[w] != -1:  # 이미 계산한 값이면 재사용
        return dp[w]

    res = 10001
    for coin in coins:
        res = min(res, f(w - coin) + 1)

    dp[w] = res
    return dp[w]

ans = f(k)
print(-1 if ans == 10001 else ans)
