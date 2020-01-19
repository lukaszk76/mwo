import numpy as np
import time

def eratostenes_list(n):
    numbers = list(range(2, n))
    i = 0
    while (i < len(numbers)):
        number = numbers[i]
        x = number
        while (x < n):
            x += number
            try:
                numbers.remove(x)
            except:
                pass
        i += 1
    return numbers


def eratostenes_dict(n):
    numbers = {i: i for i in range(2, n)}
    i = 2
    while (i < len(numbers)):
        x = i
        while (x < n):
            x += i
            try:
                del numbers[x]
            except:
                pass
        i += 1
    return numbers.keys()


def eratostenes_numpy(n):
    numbers = np.array(range(2, n))
    i = 0
    while (i < len(numbers)):
        number = numbers[i]
        x = number
        while (x < n):
            x += number
            try:
                numbers = numbers[numbers != x]
            except:
                pass
        i += 1
    return numbers

def tester(function, n):
    start = time.perf_counter()
    print(function(n))
    end = time.perf_counter()
    print("Completed with the {} in {} s.\n".format(function.__name__, end - start))

if __name__ == "__main__":
    n = 10000
    print("Erathosthenes sieve for range 2 to", n, "\n")
    functions = (eratostenes_list, eratostenes_dict, eratostenes_numpy)
    for function in functions:
        tester(function, n)
