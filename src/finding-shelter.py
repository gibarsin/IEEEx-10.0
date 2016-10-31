import math
from itertools import product, groupby
from pprint import pprint

N = int(input().strip())

soldiers = []
shelters = []


def distance(p1, p2):
    return math.sqrt((p2[1] - p1[1]) ** 2 + (p2[0] - p1[0]) ** 2)


for scoord in range(N):
    x, y = [float(a) for a in input().strip().split(' ')]

    soldiers.append((x, y, scoord))

for shcoord in range(N):
    x, y = [float(a) for a in input().strip().split(' ')]

    shelters.append((x, y, shcoord))

# distances[soldier][shelter]
distances = [[_ for _ in range(N)] for __ in range(N)]

combinations = product(soldiers, shelters)

# grouped = groupby(combinations, lambda c: c[0][2])

pprint(list(combinations))

for combination in combinations:
    soldier = combination[0]
    shelter = combination[1]

    dist = distance(soldier, shelter)

    distances[soldier[2]][shelter[2]] = dist

# pprint(distances)

filtered_distances = []
for distance_list in distances:
    filtered_distances.append([a for a in distance_list if a > 0])

# pprint(filtered_distances)

print(max(min(dist2) for dist2 in filtered_distances))
print(sum(min(dist2) for dist2 in filtered_distances))
