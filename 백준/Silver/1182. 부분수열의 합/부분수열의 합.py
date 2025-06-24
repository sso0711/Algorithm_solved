import sys
input = sys.stdin.readline

# sys.setrecursionlimit(10000)  # 재귀 깊이 제한 증가 (필요 시)

n, s = map(int, input().split())
val = list(map(int, input().split()))
cnt = 0
cursum = 0

def dfs(cur):
    global cnt, cursum
    if cur == n:
        return
    if cursum + val[cur] == s:
        cnt += 1
    dfs(cur + 1)  # 현재 원소를 포함하지 않고
    cursum += val[cur]
    dfs(cur + 1)  # 현재 원소 포함
    cursum -= val[cur]  # 상태 복구

dfs(0)
print(cnt)