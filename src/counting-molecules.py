C, H, O = [int(i) for i in input().strip().split(' ')]

origO, origH, origC = O, H, C

glucose = carbon = water = 0

######### GCW

if H / 12 != 0 and C / 6 != 0 and O / 6 != 0:
    glucose = min(H / 12, C / 6, O / 6)

    H -= 12 * glucose
    C -= 6 * glucose
    O -= 6 * glucose

if C != 0 and O / 2 != 0:
    carbon = min(C, O / 2)

    C -= 1 * carbon
    O -= 2 * carbon

if O != 0 and H / 2 != 0:
    water = min(O, H / 2)

    O -= 1 * water
    H -= 2 * water

if O == 0 and H == 0 and C == 0:
    print("%s %s %s" % (water, carbon, glucose))
    exit()

######### GWC

O, H, C = origO, origH, origC

glucose = carbon = water = 0

if H / 12 != 0 and C / 6 != 0 and O / 6 != 0:
    glucose = min(H / 12, C / 6, O / 6)

    H -= 12 * glucose
    C -= 6 * glucose
    O -= 6 * glucose

if O != 0 and H / 2 != 0:
    water = min(O, H / 2)

    O -= 1 * water
    H -= 2 * water

if C != 0 and O / 2 != 0:
    carbon = min(C, O / 2)

    C -= 1 * carbon
    O -= 2 * carbon

if O == 0 and H == 0 and C == 0:
    print("%s %s %s" % (water, carbon, glucose))
    exit()

######### WGC

O, H, C = origO, origH, origC

glucose = carbon = water = 0

if O != 0 and H / 2 != 0:
    water = min(O, H / 2)

    O -= 1 * water
    H -= 2 * water

if H / 12 != 0 and C / 6 != 0 and O / 6 != 0:
    glucose = min(H / 12, C / 6, O / 6)

    H -= 12 * glucose
    C -= 6 * glucose
    O -= 6 * glucose

if C != 0 and O / 2 != 0:
    carbon = min(C, O / 2)

    C -= 1 * carbon
    O -= 2 * carbon

if O == 0 and H == 0 and C == 0:
    print("%s %s %s" % (water, carbon, glucose))
    exit()

######### CWG

O, H, C = origO, origH, origC

glucose = carbon = water = 0

if C != 0 and O / 2 != 0:
    carbon = min(C, O / 2)

    C -= 1 * carbon
    O -= 2 * carbon

if O != 0 and H / 2 != 0:
    water = min(O, H / 2)

    O -= 1 * water
    H -= 2 * water

if H / 12 != 0 and C / 6 != 0 and O / 6 != 0:
    glucose = min(H / 12, C / 6, O / 6)

    H -= 12 * glucose
    C -= 6 * glucose
    O -= 6 * glucose

if O == 0 and H == 0 and C == 0:
    print("%s %s %s" % (water, carbon, glucose))
    exit()

######### CGW

O, H, C = origO, origH, origC

glucose = carbon = water = 0

if C != 0 and O / 2 != 0:
    carbon = min(C, O / 2)

    C -= 1 * carbon
    O -= 2 * carbon

if H / 12 != 0 and C / 6 != 0 and O / 6 != 0:
    glucose = min(H / 12, C / 6, O / 6)

    H -= 12 * glucose
    C -= 6 * glucose
    O -= 6 * glucose

if O != 0 and H / 2 != 0:
    water = min(O, H / 2)

    O -= 1 * water
    H -= 2 * water

if O == 0 and H == 0 and C == 0:
    print("%s %s %s" % (water, carbon, glucose))
    exit()

######### WCG

O, H, C = origO, origH, origC

glucose = carbon = water = 0

if O != 0 and H / 2 != 0:
    water = min(O, H / 2)

    O -= 1 * water
    H -= 2 * water

if C != 0 and O / 2 != 0:
    carbon = min(C, O / 2)

    C -= 1 * carbon
    O -= 2 * carbon

if H / 12 != 0 and C / 6 != 0 and O / 6 != 0:
    glucose = min(H / 12, C / 6, O / 6)

    H -= 12 * glucose
    C -= 6 * glucose
    O -= 6 * glucose

if O == 0 and H == 0 and C == 0:
    print("%s %s %s" % (water, carbon, glucose))
    exit()

print("Error")
