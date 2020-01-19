import matplotlib.pyplot as plt
import numpy as np

def getFunction():
    function_txt = input("Podaj wzor funkcji >>> y=")
    return lambda x: eval(function_txt), function_txt


def getRange():
    while True:
        try:
            leftEnd = input("Podaj lewy koniec przedzialu >>>")
            leftEnd = float(leftEnd)
            rightEnd = input("Podaj prawy koniec przedzialu >>>")
            rightEnd = float(rightEnd)
            if leftEnd<rightEnd:
                break
            else:
                print("Lewy koniec musi byc mniejszy od prawego!")
        except:
            print("Nieprawidłowa wartość!")

    return np.linspace(leftEnd, rightEnd, 201)


if __name__ == "__main__":
    function, function_txt = getFunction()
    x = getRange()
    plt.plot(x, function(x))
    plt.title("y=" + function_txt)
    plt.show()
