

def foo():
    print('Run')

foo() # Run


def foo2():
    print('Run')
    def fun():
        print('Run2')
    fun()

foo2() 

# Run
# Run2


def foo3(x,y):
    print('Run',x,y) 

foo3(2,2) # Run 2 2

def foo3(x,y):
    return x*y;

print(foo3(2,2)) # 4

def foo3(x,y):
    return x*y,x/y;

print(foo3(2,2)) # (4, 1.0)
r1,r2 = foo3(2,2) # Assigned to variables
print(r1,r2) # 4 1.0

def foo3(x,y,z=None): # z is an optional Parameter
    print(z)
    return x*y,x/y;

foo3(2,2) # None


# Functions are objects , so we can return them

def foo4(x):
    def foo5():
        print(x)
    
    return foo5

foo4(5)() #5 --> foo4(5) returns a function. We invoke the function using () 
x = foo4(5)
x() #5 --> foo4(5) returns a function. We invoke the function using ()


# Unpack Operator
x = [1,23,2312321,2312321]
print(x) # [1, 23, 2312321, 2312321]
print(*x) # 1 23 2312321 2312321 -> Unpack the list and pass them in the print statement as arguments

def foo6(x,y):
    print(x,y)

pairs = [(1,2),(3,4)]
for p in pairs:
    foo6(p[0],p[1]) # 1 ,2 is passed in the 1st iteration : 3,4 is passed in next

# 1 2
# 3 4

for p in pairs:
    foo6(*p) # 1 ,2 is passed in the 1st iteration : 3,4 is passed in next --> USING UNPACK OPERATOR


# 1 2
# 3 4


# For Dictionary -> **

foo6(**{'x':2,'y':5}) # 2 5

# Below lets us pass an unlimited amount of arguments

def foo7(*args,**kwargs):
    print(args,kwargs)

foo7(1,2,3,4,5,one=0,two=1) # (1, 2, 3, 4, 5) {'one': 0, 'two': 1}