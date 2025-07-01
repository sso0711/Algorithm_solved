import sys
input = sys.stdin.readline

n = int(input())
arr = [list(map(int,input().split())) for _ in range(n)]

# 구간의 시작점을 기준으로 정렬
arr.sort(key=lambda x: x[0])
st = arr[0][0]
end = arr[0][1]
ans = 0

for i in range(1,len(arr)):
    # 끝점이 현재 끝점보다 작은 경우 (선분 완전히 겹침)
    if end >= arr[i][1]:
        continue
        
    # 구간이 이미 색칠된 곳부터 시작한다면
    elif end >= arr[i][0]:
        end = arr[i][1]
        
    # 구간이 새로운 곳부터 시작한다면
    else:
        ans += end - st
        st = arr[i][0]
        end = arr[i][1]

ans += end - st
print(ans)