

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


f_name = 'Tim'

def foo8():
    f_name = 'Nick'

print(f_name) # Tim
foo8()
print(f_name) # Tim

l_name = 'Buck'

def foo9():
    global l_name # Never Use it
    l_name = 'Muck'

print(l_name) # Buck
foo9()
print(l_name) # Muck

# Lambda [ Anonymous Function ]

x = lambda x: x+5 # Not the best way to use it
print(x(2)) # 7

x = lambda x,y: x+y # Not the best way to use it
print(x(5,5)) # 10

x = [1,2,4,5,6,6,7,21,312,321,312,5,432,643,4123,41,2312]
mp = map(lambda i:i+2,x) # add 2 to every element in x
print(list(mp)) # [3, 4, 6, 7, 8, 8, 9, 23, 314, 323, 314, 7, 434, 645, 4125, 43, 2314]


x = [1,2,4,5,6,6,7,21,312,321,312,5,432,643,4123,41,2312]
mp = filter(lambda i:i%2==0,x) # Function must return true/false
print(list(mp)) # 2, 4, 6, 6, 312, 312, 432, 2312]

x = [1,2,4,5,6,6,7,21,312,321,312,5,432,643,4123,41,2312]
def foo10(i):
    return i % 2==0;
mp = filter(foo10,x) 
print(list(mp)) # [2, 4, 6, 6, 312, 312, 432, 2312]