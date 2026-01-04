# 최선의 선택 : -와 - 사이의 수 괄호치기
# 전부 반복해서 최적의 해에 도달한다.
# + - ( + ) - + + - + + + - - -

import sys
input = sys.stdin.readline

s = str(input())


p = s.split("-")
ans = sum(map(int,p[0].split("+")))

for i in range(1,len(p)) : 
    ans -= sum(map(int,p[i].split("+")))

print(ans)