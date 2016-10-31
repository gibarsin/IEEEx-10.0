from numpy import log2, floor, float

test_cases = int(input().strip())


def min_power_of_2(n):
    return 2 ** floor(log2(n))


for line in range(test_cases):
    n = int(input().strip())

    if float(log2(n)).is_integer():
        print(1)
        continue

    print(int((n - min_power_of_2(n)) * 2 + 1))
