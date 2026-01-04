import sys
input = sys.stdin.readline

ans=0
n = int(input())
a = list(map(int, input().split()))
b = list(map(int, input().split()))
a.sort()
newb= sorted(b,reverse=True)

for i in range(n):
    ans += a[i]*newb[i]

print(ans)