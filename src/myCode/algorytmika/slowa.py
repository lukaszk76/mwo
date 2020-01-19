import operator

text = open("pan tadeusz.txt", "r").readlines()
words = {}
for line in text:
    for word in line.split():
        word = word.strip(" .,;:!?()[]-").lower()

        if (len(word) > 4):
            try:
                words[word] += 1
            except:
                words[word] = 1

sorted_words = sorted(words.items(), key=operator.itemgetter(1), reverse=True)
counter = 1
for item in sorted_words[:20]:
    print("{}. slowo \"{}\" wystepuje {} razy.".format(counter, item[0], item[1]))
    counter += 1
