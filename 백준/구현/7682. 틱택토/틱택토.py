def o_is_bingo(str):
  # 가로빙고
  for i in range(0, 9, 3):
    if str[i] == str[i + 1] == str[i + 2] == "O":
      return True
  # 세로빙고
  for i in range(0, 3):
    if str[i] == str[i + 3] == str[i + 6] == "O":
      return True
  # 대각선빙고
  if str[0] == str[4] == str[8] == "O":
    return True
  if str[2] == str[4] == str[6] == "O":
    return True


def x_is_bingo(str):
  # 가로빙고
  for i in range(0, 9, 3):
    if str[i] == str[i + 1] == str[i + 2] == "X":
      return True
  # 세로빙고
  for i in range(0, 3):
    if str[i] == str[i + 3] == str[i + 6] == "X":
      return True
  # 대각선빙고
  if str[0] == str[4] == str[8] == "X":
    return True
  if str[2] == str[4] == str[6] == "X":
    return True


# end가 입력될 때까지 무한반복
while True:
  testcase = str(input())
  if testcase == "end":
    break

  # 1. O가 이기는 경우
  if (testcase.count("O") == testcase.count("X")
      ) and o_is_bingo(testcase) and not x_is_bingo(testcase):
    print("valid")
    continue

# 2. X가 이기는 경우
  if (testcase.count("O") + 1 == testcase.count("X")
      ) and not o_is_bingo(testcase) and x_is_bingo(testcase):
    print("valid")
    continue

# 3. 무승부인 경우
  if testcase.count("O") == 4 and testcase.count(
      "X") == 5 and not o_is_bingo(testcase) and not x_is_bingo(testcase):
    print("valid")
    continue

  print("invalid")
