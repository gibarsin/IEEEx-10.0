password = input().strip()

operations = int(input().strip())

letters = 'abcdefghijklmnopqrstuvwxyz'

for op in range(operations):
    command = [int(a) for a in input().strip().split(' ')]

    opcode = command[0]
    i, j = command[1:3]

    if opcode == 1:
        k = command[3]

        str1 = password[i - 1:j]

        str2 = password[k - 1:k - 1 + j - i + 1]

        print("%s == %s" % (str1, str2))

        if str1 == str2:
            print("Y")
        else:
            print("N")

    elif opcode == 2:
        k = command[3]

        replacement = password[k - 1:k - 1 + j - i + 1]

        print("rep=%s" % replacement)

        newpassword = password[:i - 1] + replacement + password[j - 1:]
        password = newpassword
        print("a " + password)

    elif opcode == 3:
        replacement = list(password[i - 1:j])

        for i, letter in enumerate(replacement):

            if letter != 'z':
                replacement[i] = letters[letters.index(letter)]
            else:
                replacement[i] = 'a'

        newpassword = password[:i] + "".join(replacement) + password[j:]
        password = newpassword

        print("b " + password)
