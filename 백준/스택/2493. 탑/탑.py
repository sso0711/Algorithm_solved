import sys

input = sys.stdin.readline

from collections import deque

n = int(input())
building = list(map(int, input().split()))
s = deque()  # 탑을 저장할 스택
ans = [0] * n  # 신호를 수신하는 탑의 번호 저장 (building인덱스 +1)

s.append([0, building[0]])

for i in range(1, n):
  # 3-1.자신의 높이가 더 높다면, 스택의 top을 pop하고, 다음 스택의 top을 확인한다.
  while len(s) > 0 and building[i] > s[-1][1]:
    s.pop()

  # 3-2.스택의 top이 더 높다면,

  # 4.
  # 스택이 빈 경우
  if len(s) == 0:
    ans[i] = 0
  else:
  # 수신할 탑을 찾음. 자신의 신호를 해당 탑(스택의 top)이 수신한다.
    ans[i] = s[-1][0] + 1
    
  s.append([i, building[i]])

print(*ans)