#todo FIX THIS WHOLE THING
#https://cses.fi/problemset/result/15774917/
def main():
    n = int(input())
    nums = input().split(" ")

    towers = [int(nums[0])]

    for i in range(1, n):
        placed = False
        block = int(nums[i])
        # print(block, "block")
        for j in range(len(towers)):
            # print(towers[j], "tower")
            if block < towers[j]:
                towers[j] = block
                placed = True
                break
        if not placed:
            towers.append(block)

        # print(towers)

    print(len(towers))


if __name__ == '__main__':
    main()
