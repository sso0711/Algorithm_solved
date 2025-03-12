import sys
input = sys.stdin.readline

m = int(input())
S = set()


for _ in range(m):
    op = input().split()
    
    if len(op) == 2: # 파라미터 x가 있을 경우 int로 변환
        op[1] = int(op[1])
        
    if op[0]=="add":
        if op[1] not in S:
            S.add(op[1])    
    elif op[0]=="remove":
        if op[1] in S:
            S.remove(op[1])
    elif op[0]=="check":
        if op[1] in S:
            print(1)
        else:
            print(0)
    elif op[0]=="toggle":
        if op[1] in S:
            S.remove(op[1])
        else:
            S.add(op[1])
    elif op[0]=="all":
        S = set(range(1,21))
    elif op[0]=="empty":
        S.clear()