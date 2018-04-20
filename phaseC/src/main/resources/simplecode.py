import os
import sys

def sum(a,b):
    return a+b

def mult(x,y):
    return x*y


def main():
    total = sum(8,12)
    print("The addition of 8 and 12 is:"+str(total))

    mult_result = mult(3,4)
    print("The multiplication of 3 and 4 is:"+str(mult_result))

main()