# [Silver V] 집합 - 11723 

[문제 링크](https://www.acmicpc.net/problem/11723) 
풀이 참고 X

# 🪄 문제 탐색하기


# 🪄 회차별 수정사항
## 1회차

arr에는 empty와 all의 경우는 파라미터 x가 없는 길이 1의 배열이 저장되는데,
for a,b in arr 에서 b에 해당하는 것이 없어 오류가 난다.

```
import sys
input = sys.stdin.readline

m = int(input())
S = set()

arr=[list(input().split()) for _ in range(m)]

for a,b in arr:
    
    if a=="add":
        if b not in S:
            S.add(b)    
    elif a=="remove":
        if b in S:
            S.remove(b)
    elif a=="check":
        if b in S:
            print(1)
        else:
            print(0)
    elif a=="toggle":
        if b in S:
            S.remove(b)
        else:
            S.add(b)
    elif a=="all":
        S = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20}
    elif a=="empty":
        S.clear()
```



## 2회차

a,b 대신 for문 내부에서 op[0], op[1]로 바꿔주었다. (op[1]이 있는 경우에만 사용하도록)
예제 출력과 동일하게 나오지만 메모리 초과.

알고보니 백준에서 입력을 한 번에 받아서 한번에 출력해야만 하는줄 알았는데 한 줄씩 입력받아 처리해도 되는거였다,,

```python
import sys
input = sys.stdin.readline

m = int(input())
S = set()

arr=[list(input().split()) for _ in range(m)]

for op in arr:
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
        S = set([i for i in range(1,21)])
    elif op[0]=="empty":
        S.clear()
```

## 3회차
정답


# 🪄 새로 알게 된 사실

- set에서 remove는 존재하지 않는 요소를 제거하려고 하면 에러 발생하는데, discard는 그렇지 않다.
- S=set(range(1,21)) → 1~20의 원소를 가지는 집합 생성

<br>
<br>
<br>
<br>

### 성능 요약

메모리: 32412 KB, 시간: 3344 ms

### 분류

비트마스킹, 구현

### 제출 일자

2025년 3월 12일 11:55:26

### 문제 설명

<p>비어있는 공집합 S가 주어졌을 때, 아래 연산을 수행하는 프로그램을 작성하시오.</p>

<ul>
	<li><code>add x</code>: S에 x를 추가한다. (1 ≤ x ≤ 20) S에 x가 이미 있는 경우에는 연산을 무시한다.</li>
	<li><code>remove x</code>: S에서 x를 제거한다. (1 ≤ x ≤ 20) S에 x가 없는 경우에는 연산을 무시한다.</li>
	<li><code>check x</code>: S에 x가 있으면 1을, 없으면 0을 출력한다. (1 ≤ x ≤ 20)</li>
	<li><code>toggle x</code>: S에 x가 있으면 x를 제거하고, 없으면 x를 추가한다. (1 ≤ x ≤ 20)</li>
	<li><code>all</code>: S를 {1, 2, ..., 20} 으로 바꾼다.</li>
	<li><code>empty</code>: S를 공집합으로 바꾼다.</li>
</ul>

### 입력 

 <p>첫째 줄에 수행해야 하는 연산의 수 M (1 ≤ M ≤ 3,000,000)이 주어진다.</p>

<p>둘째 줄부터 M개의 줄에 수행해야 하는 연산이 한 줄에 하나씩 주어진다.</p>

### 출력 

 <p><code>check</code> 연산이 주어질때마다, 결과를 출력한다.</p>

