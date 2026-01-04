import sys
input = sys.stdin.readline

ans = []
n = int(input())
l = [int(input()) for i in range(n)]
l.sort(reverse=True)

for i in range(n):
  ans.append(l[i] * (i+1))

print(max(ans))