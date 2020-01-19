import numpy as np

def create_file():
    file = open("matrix.txt", "w")
    matrix = [3,
              [1, 2, 3, 7],
              [-1, 2, 4, 6],
              [2, 1, 1, 13]]

    for line_number in range(len(matrix)):
        if line_number == 0:
            file.write(str(matrix[0]) + "\n")
        else:
            for number_index in range(len(matrix[line_number])):
                file.write(str(matrix[line_number][number_index]))
                if number_index != (len(matrix[line_number]) - 1):
                    file.write(" ")
            if line_number != (len(matrix) - 1):
                file.write("\n")
    file.close()

def read_file():
    matrix_txt = open("matrix.txt", "r").read()
    first_line = True
    matrix_A = []
    matrix_B = []

    for line in matrix_txt.split("\n"):
        if first_line:
            number_of_factors = int(line)
            first_line = False
        else:
            matrix_line = []
            numbers = line.split(" ")
            for number_index in range(len(numbers)):
                if number_index < number_of_factors:
                    matrix_line.append(float(numbers[number_index]))
            matrix_A.append(matrix_line)
            matrix_B.append([float(numbers[number_of_factors])])
    return np.array(matrix_A), np.array(matrix_B)

def solve():
    matrix_A, matrix_B = read_file()
    matrixes_Wi = []

    for i in range(matrix_A.shape[1]):
        matrix_Wi = matrix_A.copy()
        matrix_Wi[:, i] = matrix_B[:, 0]
        matrixes_Wi.append(matrix_Wi)

    Wis = []
    all_Wi_is_zero = True;
    for matrix_Wi in matrixes_Wi:
        Wi = np.linalg.det(matrix_Wi)
        Wis.append(Wi)
        if Wi != 0:
            all_Wi_is_zero = False

    W = np.linalg.det(matrix_A)

    if W == 0 and all_Wi_is_zero:
        print("Uklad rownan ma nieskonczenie wiele rozwiazan")
    elif W == 0:
        print("Uklad rownan sprzecznych")
    else:
        for i in range(matrix_B.shape[0]):
            print("x{}={}".format(i + 1, Wis[i] / W))

if __name__ == "__main__":
    # create_file()
    solve()
