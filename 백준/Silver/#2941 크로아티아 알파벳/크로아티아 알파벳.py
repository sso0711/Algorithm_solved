import sys
# sys.setrecursionlimit(10**6)
input = sys.stdin.readline
croatia = ['c=','c-','dz=','d-','lj','nj','s=','z=']

str = input().rstrip()

str = str.replace('c=','x')
str = str.replace('c-','x')
str = str.replace('dz=','x')
str = str.replace('z=','x')
str = str.replace('d-','x')
str = str.replace('lj','x')
str = str.replace('nj','x')
str = str.replace('s=','x')


print(len(str))