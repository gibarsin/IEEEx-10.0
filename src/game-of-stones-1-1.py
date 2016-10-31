T = int(input().strip())

for testcase in range(T):
    G = int(input().strip())
    total_piles = 0

    for game in range(G):
        piles = int(input().strip())

        stones = [int(pile) for pile in input().strip().split(' ')]

        total_piles += piles

    if total_piles % G == 1:
        print("Alice")
    else:
        print("Bob")
