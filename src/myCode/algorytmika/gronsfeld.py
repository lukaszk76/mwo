def getLetter(a, shift):
    return (chr(ord(a)+ shift))

def getShift(key):
    index = -1
    while True:
        index += 1
        if index == len(key):
            index = 0
        yield int(key[index])

def storeFile(targetFileName, text):
    try:
        file = open(targetFileName, "w")
        file.write(text)
        file.close()
    except:
        print("Error while writing to {} file".format(targetFileName))

def code(sourceFileName, targetFileName, key):
    text = open(sourceFileName,"r").read()
    coded_text = ""

    for letter in text:
        coded_text += getLetter(letter, next(getShift(key)))

    storeFile(targetFileName, coded_text)

def decode(sourceFileName, targetFileName, key):
    coded_text = open(sourceFileName,"r").read()
    decoded_text = ""
    for letter in coded_text:
        decoded_text += getLetter(letter, -1*next(getShift(key)))

    storeFile(targetFileName, decoded_text)

if __name__ == "__main__":
    code("pan tadeusz.txt","coded.txt", "145238")
    decode("coded.txt", "decoded.txt", "145238")