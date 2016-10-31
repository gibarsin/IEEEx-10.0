import sys
from pprint import pprint


def find_root(post, parent, parents, threads):
    if parent == 0:
        return post

    print(parent)
    print(post)

    return find_root(post, threads[post - 1], parents, threads)


for line in sys.stdin:

    threads = []
    parents = {}

    line = line.strip()

    intended_posts, posts_remaining = [int(inp) for inp in line.split(' ')]

    for post, parent in zip(range(posts_remaining), sys.stdin):
        parent = int(parent)
        threads.append(parent)
        pprint(threads)
        pprint(parents)

        parents[post] = find_root(post, parent, parents, threads)

    pprint(threads)
    pprint(parents)
