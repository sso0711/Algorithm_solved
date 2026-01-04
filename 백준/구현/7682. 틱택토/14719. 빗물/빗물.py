import sys
input = sys.stdin.readline

h,w = map(int,input().split()) # 세로, 가로 길이
block = list(map(int,input().split()))
ans = 0

for i in range(1,w-1):
  if(max(block[0:i]) > block[i]) and (max(block[i+1:]) > block[i]):
    water=min(max(block[0:i]),max(block[i+1:]))-block[i]
    ans += water

print(ans)