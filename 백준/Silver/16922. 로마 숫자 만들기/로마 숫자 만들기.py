import sys
input = sys.stdin.readline

# sys.setrecursionlimit(10000)  

# N,K = list(map(int, input().split()))

N = int(input())

num_list = [1, 5, 10, 50]
num = 0 # 현재까지 만들어진 수
cnt = 0 # 사용한 숫자 갯수
lst = [0] * 1001

def f(idx):
  global num, cnt
  if cnt == N:
    lst[num] = 1 
    return

  for i in range(idx,len(num_list)):
    cnt += 1
    num += num_list[i]
    
    f(i)
    cnt -= 1
    num -= num_list[i]

f(0)
print(sum(lst))