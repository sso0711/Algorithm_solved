import sys
input = sys.stdin.readline

n = int(input())
nlist = list(map(int,input().split()))
m = int(input())
mlist = list(map(int,input().split()))
d = dict()

mset = set(mlist)

# 출력형식 초기화
for i in mlist:
    d[i] = 0

for i in nlist:
    if i in mset:
        d[i] += 1

    

print(*[d[i] for i in mlist])
