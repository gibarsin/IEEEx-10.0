from pprint import pprint

t, p, n = [int(i) for i in input().strip().split(' ')]
input()

p_rankings = []

for line in range(p):
    l = input().strip()

    if l == '?':
        p_rankings += [None]
    else:
        p_rankings += [int(l)]

input()

np_rankings = []

for line2 in range(n):
    l = input().strip()

    if l == '?':
        np_rankings += [None]
    else:
        np_rankings += [int(l)]

pprint(p_rankings)
pprint(np_rankings)

# p_values = [e for e in p_rankings if e is not None]
# np_values = [f for f in np_rankings if f is not None]

for i in range(len(p_rankings)):
    for k in range(len(np_rankings)):
        