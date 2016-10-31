scenarios = int(input().strip())


def lazy_search(list, needle, start):
    try:
        return list.index(needle, start)
    except ValueError:
        return 1000


for scenario in range(scenarios):
    colors_len = int(input().strip())

    color_seq = [int(color) for color in input().strip().split(' ')]

    brushes = color_seq[0:1]

    j = 0

    while brushes[0] == color_seq[j]:
        j += 1

    brushes += [color_seq[j]]

    color_seq = color_seq[j + 1:]

    changes = 2

    for i in range(0, len(color_seq)):

        this_color = color_seq[i]

        if this_color in brushes:
            continue

        indexes = [
            lazy_search(color_seq, brushes[0], i),
            lazy_search(color_seq, brushes[1], i),
        ]

        index = 1 if indexes[0] < indexes[1] else 0

        brushes[index] = this_color
        changes += 1

    print(changes)
