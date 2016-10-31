test_cases = int(input().strip())

for line in range(test_cases):
    instr = input().strip().split(" ")

    n = int(instr[0])
    word = instr[1]

    p = 0

    letters = list(word)

    letters.sort()

    letters = set(letters)

    for i in range(1, n + 1):
