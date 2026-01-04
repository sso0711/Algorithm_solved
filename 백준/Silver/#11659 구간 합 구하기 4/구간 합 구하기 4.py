import sys

input = sys.stdin.readline

n,m = list(map(int,input().rstrip().split()))
nums = [0] + list(map(int,input().rstrip().split()))
sums=[]
added = 0

# i번째 숫자까지의 누적합을 모두 구한다
for i in range(n+1):
  added += nums[i]
  sums.append(added)

for _ in range(m):
  st,end = list(map(int,input().rstrip().split()))
  print(sums[end] - sums[st-1])