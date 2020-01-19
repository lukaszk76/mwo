import numpy as np

n = 7
powers = np.zeros((10, n), dtype=int)

for x in range(10):
    for y in range(n):
        powers[x][y] = x ** (y + 1)

for liczba in range(10, 10 ** n, 10):
    temp = 0
    length = len(str(liczba))
    liczba_temp = liczba

    for i in range(length - 1, 0, -1):
        digit = int(liczba_temp / 10 ** i)
        liczba_temp -= digit * 10 ** i
        temp += powers[digit][length - 1]
        if (temp > liczba + 9):
            break

    if (temp < liczba + 9):
        for i in range(10):
            if (temp + powers[i][length-1]) == (liczba + i):
                print(liczba + i)
