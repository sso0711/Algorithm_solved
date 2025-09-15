import sys
# sys.setrecursionlimit(10**6)
input = sys.stdin.readline

all_num = list(range(1,10000))

# 완전탐색
# 1부터 10000까지 각 숫자가 만들 수 있는 모든 d(n)을 계산한다. 
for i in range(1,10001):
  dn = i
  l = list(map(int,list(str(i))))
  for j in l:
    dn += j

  # 만들어진 d(n)을 하나씩 제거한다.
  if dn in all_num:
    all_num.remove(dn)

# 남아있는 숫자들은 10000이하 어떤 수로도 만들어질 수 없으므로, 셀프 넘버이다.
for a in all_num:
  print(a, end="\n")