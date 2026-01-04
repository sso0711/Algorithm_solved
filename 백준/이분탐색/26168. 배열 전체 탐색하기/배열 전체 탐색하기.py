from bisect import bisect_left, bisect_right

n,m = map(int,input().split())
a = list(map(int,input().split()))
b = [list(map(int, input().split())) for _ in range(m)]
a.sort()




for arr in b:
    if arr[0]==1 :
        ans = n - bisect_left(a,arr[1])
    elif arr[0]==2 :
        ans = n - bisect_right(a,arr[1])
    elif arr[0]==3 :
        ans = bisect_right(a,arr[2]) - bisect_left(a,arr[1])
    print(ans)