import sys
input = sys.stdin.readline

# sys.setrecursionlimit(10000)  

N,K = list(map(int, input().split()))

kit = list(map(int, input().split())) # N개 키트의 중량 증가량

kg = 500 # 현재 중량
chk = [0] * N # N번째 키트 사용 여부
lst = [] # 현재까지 적용된 키트 순서
ans = 0 # 항상 500 이상인 경우의 수

def f():
  global ans, kg
  if len(lst) == N:
    ans += 1
    return
  for i in range(N):
    if chk[i] :
      continue
    if kg - K + kit[i] < 500:
      continue
    chk[i] = 1
    kg = kg + kit[i] - K
    lst.append(i)
    f()
    chk[i] = 0
    kg = kg - kit[i] + K
    lst.pop()

f()
print(ans)