import sys
import itertools
input = sys.stdin.readline

T = int(input())

for i in range(T):
  print("Case # " + str(i+1) + ":")
  s = input().rstrip()
  ans = list(itertools.permutations(s, len(s)))
  for j in ans:
    print(''.join(j))