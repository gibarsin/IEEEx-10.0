board = [int(i) for i in input().strip().split(' ')]

boards = [board]


def get_next_board(board):
    seeds = board[0]

    newboard = board[1:]

    i = 0
    while seeds:
        if i < len(newboard):
            newboard[i] += 1
        else:
            newboard.append(1)

        seeds -= 1
        i += 1

    return newboard


depth = 0

while True:
    last_board = boards[-1]
    next_board = get_next_board(last_board)
    print(next_board)

    if next_board in boards:
        boards.append(next_board)
        depth = boards.index(next_board)
        break

    boards.append(next_board)


def find_previous(board, limit):
    for i in range(1, limit):
        board[i] -= 1

        


print(depth)
