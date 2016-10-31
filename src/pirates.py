import math

N, M, Q = [int(i) for i in input().strip().split(' ')]

map = []

for line in range(N):
    map.append(input().strip())


def traverse(x1, y1, x2, y2):
    weights = []

    if x1 == y1 and x2 == y2:
        return 0

    lasttype = map[x1][y1]

    for i in [-1, 0, 1]:
        for j in [-1, 0, 1]:
            if i == 0 and j == 0:
                continue

            newx, newy = x1 + i, y1 + j

            if newx == M or newy == N or newx < 0 or newy < 0:
                continue

            thistype = map[newx][newy]

            weight = 0

            if lasttype != thistype and lasttype == '~':
                weight = 1

            weights.append(traverse(newx, newy, x2, y2, ) + weight)

    return min(weights)


for line2 in range(Q):
    x1, y1, x2, y2 = [int(i) for i in input().strip().split(' ')]

    print(traverse(x1 - 1, y1 - 1, x2 - 1, y2 - 1))
