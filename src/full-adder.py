from collections import deque

line1 = input()
print(line1)

base, symbols = line1.strip().split(' ')
base = int(base)

line2 = input()
line3 = input()

print(line2)
print(line3)

num1 = line2.strip()
num2 = line3[1:].strip()

qnum1 = deque(num1)

vnum1 = 0
exp = 0
while len(qnum1) > 0:
    n = qnum1.pop()

    val = symbols.index(n)
    vnum1 += val * (base ** exp)
    exp += 1

qnum2 = deque(num2)

vnum2 = 0
exp2 = 0
while len(qnum2) > 0:
    n = qnum2.pop()
    print(n)
    val = symbols.index(n)
    vnum2 += val * (base ** exp2)
    exp2 += 1

line4 = input()

print(line4)

final = vnum1 + vnum2

out = []

while final > 0:
    cur = final % base
    out.append(symbols[cur])

    final = final // base

output = "".join(out[::-1])

print(output.rjust(len(line4)))
