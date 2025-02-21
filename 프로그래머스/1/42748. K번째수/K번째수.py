def solution(array, commands):
    answer = []
    for x in range(len(commands)):
        i,j,k = commands[x][0],commands[x][1],commands[x][2]
        sub_arr = array[i-1:j]
        sub_arr.sort()
        answer.append(sub_arr[k-1])
    return answer