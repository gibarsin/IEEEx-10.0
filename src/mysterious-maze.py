import sys

maze_size = int(input().strip())

maze = [[False for _ in range(maze_size)] for __ in range(maze_size)]

open_rooms = 0


def has_path_to_bottom(row, col, lookup):
    if row == maze_size - 1:
        return True

    if row < 0 or col < 0 or col == maze_size - 1:
        return False

    if maze[row][col] and not lookup[row][col]:
        lookup[row][col] = True
        ret = has_path_to_bottom(row, col - 1, lookup) or \
              has_path_to_bottom(row, col + 1, lookup) or \
              has_path_to_bottom(row + 1, col, lookup) or \
              has_path_to_bottom(row - 1, col, lookup)

        lookup[row][col] = False
        return ret

    return False


for line in sys.stdin:
    line = line.strip()
    if line == "-1":
        print("-1")
        exit()

    # print("[%s]" % line)

    x, y = [int(num) for num in line.strip().split(" ")]
    x, y = x - 1, y - 1

    maze[x][y] = True

    open_rooms += 1

    i = 0

    lookup = [[False for ___ in range(maze_size)] for ____ in range(maze_size)]

    while i < maze_size:

        j = i
        while j + 1 < maze_size and maze[0][j + 1]:
            lookup[0][j + 1] = False
            j += 1

        if has_path_to_bottom(0, i, lookup):
            print(open_rooms)
            exit()
        i += 1
