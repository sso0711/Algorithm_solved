n = int(input())
road = list(map(int,input().split()))
cost = list(map(int,input().split()))

ans=0
m = cost[0]
for i in range(n-1):
  if cost[i] < m:
    m = cost[i]
  ans += m*road[i]

print(ans)