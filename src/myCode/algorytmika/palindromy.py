def reverse(a):
    return int(str(a)[::-1])

def generatePalindrome(a, start, count):
    if a == reverse(a):
        print("Got palindrome {} from {} after {} transformations!". format(a, start, count))
    else:
        try:
            generatePalindrome( a + reverse(a), start, count+1)
        except:
            print("Cold not achieve a palindrome from {} after {} transformations...".format(start, count))

if __name__ == "__main__":
    for i in range(10, 200):
        generatePalindrome(i,i,0)