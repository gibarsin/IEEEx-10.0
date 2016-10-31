import sys

from itertools import combinations

test_cases = int(input().strip())

for test_case in range(test_cases):
    dogs, employees = [int(x) for x in input().strip().split(' ')]

    dog_sizes = []

    for dog in range(1, dogs):
        dog_sizes += [dog]

    sorted_sizes = sorted(dog_sizes, reverse=True)

    buckets = [0 for _ in range(1, employees)]
