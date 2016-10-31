import sys
from datetime import datetime
from math import cos, sin, asin, sqrt, radians


def parse_date(date):
    return datetime.strptime(date, '%m/%d/%Y %H:%M')


r = 6378.137


def calc_distance(lat1, lon1, lat2, lon2):
    lon1, lat1, lon2, lat2 = map(radians, [lon1, lat1, lon2, lat2])
    return 2.0 * r * asin(sqrt(sin((lat1 - lat2) / 2.0) ** 2 + cos(lat1) * cos(lat2) * sin((lon1 - lon2) / 2.0) ** 2))


truck_lat, truck_long = [float(inp) for inp in input().strip().split(',')]

radius = float(input().strip())

# print(radius)

headers = input().split(',')
header_time = headers.index("Date&Time")
header_latitude = headers.index("Latitude")
header_longitude = headers.index("Longitude")
header_phone = headers.index("PhoneNumber")

# time, distance
clientes = {}

for line in sys.stdin:

    line_array = line.strip().split(',')

    date = parse_date(line_array[header_time])
    latitude = float(line_array[header_latitude])
    longitude = float(line_array[header_longitude])
    phone = int(line_array[header_phone])

    # print(type(phone))

    # print("lat=%s, lon=%s" % (latitude, longitude))

    distance = calc_distance(
        lat1=truck_lat,
        lon1=truck_long,
        lat2=latitude,
        lon2=longitude
    )

    if phone in clientes:
        if date > clientes[phone][0]:
            clientes[phone] = (date, distance)

    elif distance < radius:
        clientes[phone] = (date, distance)

phones = []

for client, value in clientes.items():
    if value[1] < radius:
        phones += [client]
phones.sort()

print(",".join(map(str, phones)))
