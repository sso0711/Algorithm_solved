import sys
input = sys.stdin.readline

ans = 0
cnt = 0
s = int(input())

while(ans <= s):
    cnt += 1
    ans += cnt
  

print(cnt-1)