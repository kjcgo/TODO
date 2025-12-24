import bisect

def main():
    temp = input().split(" ")
    n = int(temp[0])
    m = int(temp[1])

    tx = []
    line = input().split(" ")
    line2 = input().split(" ")

    for x in range(n):
        tx.append(int(line[x]))
    tx = sorted(tx)

    for x in range(m):
        p = int(line2[x])
        q = bisect.bisect(tx, p)
        #print(q)
        if q == 0:
            print(-1)
        else:
            print(tx[q-1])
            tx.remove(q-1)



if __name__ == '__main__':
    main()
