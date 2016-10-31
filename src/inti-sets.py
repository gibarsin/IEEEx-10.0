from math import gcd
from itertools import filterfalse


def iscoprimewith(a, n):
    return gcd(a, n) == 1


test_cases = int(input().strip())

for line in range(test_cases):
    n, a, b = [int(a) for a in input().strip().split(' ')]

    intiset = []

    for i in range(a, b + 1):
        if iscoprimewith(i, n):
            intiset += [i]


    def isinrange(n):
        return not ((a <= n) and (n <= b))


    #print("intiset=" + str(intiset))

    filtered = filterfalse(isinrange, intiset)

    #print("filtered=" + str(list(filtered)))

    result = sum(filtered) % 1000000007

    print(result)
