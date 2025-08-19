import sys
from collections import deque
# sys.setrecursionlimit(10**6)
input = sys.stdin.readline
t = int(input())


def f(num):
    if num==1:
        return 1
    if num==2 :
        return 2
    if num==3:
        return 4
    dp[num] = f(num-1) + f(num-2) + f(num-3)
    return dp[num]

for _ in range(t):
    n = int(input())
    dp = [0] * 11 # i를 나타내는 방법의 수
    print(f(n))