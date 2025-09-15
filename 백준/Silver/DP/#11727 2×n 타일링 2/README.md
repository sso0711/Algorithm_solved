# <img width="20px"  src="https://d2gd6pc034wcta.cloudfront.net/tier/8.svg" class="solvedac-tier"> [2×n 타일링 2](https://www.acmicpc.net/problem/11727) 

풀이참고 X

# 🪄 문제 탐색하기

2xn 타일링 문제를 단순화시켰을 때 n을 1또는 2의 합으로 나타낼 수 있는 경우의 수를 구하는 문제와 같았다.

2xn 타일링 2 문제에서 달라진 점은, 1x2, 2x1 타일 뿐만 아니라 2x2 타일이 추가되었다는 것이다.

2x1 을 채우는 경우의 수는 1가지

2x2는 3가지

2x3은 5가지

2x4는 11가지

2x5는 21가지

2x6는 43가지 …

규칙을 찾아내어 dp 점화식을 세울 수 있었다.

<br>

<br>

# 🪄 코드 설계하기

dp[i] 는 2 x i 를 채우는 경우의 수.

dp[i] = dp[i-2] * 2 + dp[i-1]

<br>

<br>

# 🪄 회차별 수정사항

## 1회

```python
점화식을 dp[i] = dp[i-1] * 2 + 1 로 잘못 세움.
```

## 2회차

```python
import sys
# sys.setrecursionlimit(10**6)
input = sys.stdin.readline

n = int(input())
dp = [0] * (n+1)
dp[1] = 1
dp[2] = 3

def f(x):
  if x==1 or x==2:
    return dp[x]
    
  if dp[x] != 0:
    return dp[x]
    
  dp[x] = f(x-2) * 2 + f(x-1)
  return dp[x]

print(f(n)%10007)
```

런타임 에러.

n=1 인 경우도 있는데, 나는 미리 dp[2]=3 을 할당해버렸기 때문.

dp[1] = 1, dp[2] = 3 를 f(x)안의 분기문으로 넣어줌.

<br>

## 3회차

정답

<br>

<br>

# 🪄 새로 알게 된 사실

2xn 타일링 1,2 모두 나는 첫째항부터 나열해서 풀었는데, 좀 더 논리적으로 점화식을 도출해낸 사람의 글을 보았다.

2xn 타일링 1의 문제를 다음과 같이 푼다면, 2xn 타일링 2 문제의 점화식도 매우 쉽게 생각해낼 수 있었다.

<br>

참고) https://kosaf04pyh.tistory.com/222

정리하자면, 2xn 의 경우의 수를 구할 때, 

1. 2x(n-1)에 2x1타일을 하나 붙인 것
2. 2x(n-2)에서 1x2타일을 세로로 두개 붙인 것

<br>

이렇게 두 가지 경우를 합한 것이 결국 2xn 의 경우의 수이다.

이를 적용해 2xn 타일링 2 문제를 푼다면, 

위에서 2x2타일이 하나 추가된 것이므로 2번과 동일한 경우의 수가 한 번 더 더해지는 것이다. (1번 + 2번 x 2)

따라서 점화식이 dp[i] = 2 * dp[i-2] + dp[i-1] 가 되는 것이다!

DP문제에서 거꾸로 생각하기가 참 유용한 것 같다.

<br>

<br>

<br>

<br>









| 제출 번호 | 닉네임 | 채점 결과 | 메모리 | 시간 | 언어 | 코드 길이 |
|---|---|---|---|---|---|---|
|98548977|qkrthdud6755|맞았습니다!! |32556KB|36ms|Python 3|347B|

## 문제
<p>2×n 직사각형을 1×2, 2×1과 2×2 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.</p>

<p>아래 그림은 2×17 직사각형을 채운 한가지 예이다.</p>

<p style="text-align: center;"><img alt="" src="https://www.acmicpc.net/upload/images/t2n2122.gif" style="height:59px; width:380px"></p>

## 입력
<p>첫째 줄에 n이 주어진다. (1 ≤ n ≤ 1,000)</p>

## 출력
<p>첫째 줄에 2×n 크기의 직사각형을 채우는 방법의 수를 10,007로 나눈 나머지를 출력한다.</p>

