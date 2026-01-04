import sys
input = sys.stdin.readline

ans=0
n = int(input())
a = list(map(int, input().split()))
a.sort()


for i in range(n):
    ans += (n-i) * a[i]  

print(ans)