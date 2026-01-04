import sys
input = sys.stdin.readline

n = int(input()) # 센서 갯수
k = int(input()) # 집중국 갯수
sens = list(map(int,input().split())) # n개의 센서들의 좌표
dist = [] # 간격들의 배열
lst = []

sens.sort()

for i in range(len(sens)-1):
    dist.append(sens[i+1] - sens[i])

dist.sort(reverse=True)

lst = dist[:k-1]
print(max(sens) - min(sens)-sum(lst))