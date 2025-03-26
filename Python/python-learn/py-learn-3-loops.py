# Basic for loop
# range is function that creates a collection of number based on the input
# input for range is (start,stop,step). If only 1 argument is passed its considered a stop arg.
# So default for start is 0.
# step -> Increment by how much , default  is 1.

for i in range(10):
    print(i,end=',') # 0,1,2,3,4,5,6,7,8,9,  -> We don't print 10 because we never reach it.

print()

for i in range(0,11):
    print(i,end=',') # 0,1,2,3,4,5,6,7,8,9,10,

print()

for i in range(1,10,2):
    print(i,end=',') # 1,3,5,7,9,

print()

for i in range(10,-1,-1):
    print(i,end=',')  # 10,9,8,7,6,5,4,3,2,1,0,
 
print()

# Loop through a List
for i in [3,4,5,6,7,8]:
    print(i,end=',') # 3,4,5,6,7,8,

print()

x = [3,4,5,6,7,8]
for i in range(len(x)):
    print(x[i],end=',') # 3,4,5,6,7,8,

print()

# Enumerate -> Create values & Index for our List. This is unique

x = [3,4,5,6,7,8]
for i,e in enumerate(x): # i -> Index , e -> Element
    print(i,e)


# 0 3
# 1 4
# 2 5
# 3 6
# 4 7
# 5 8

print()

# While loop

i = 0
while i < 10:
    print('run',end=',') # run,run,run,run,run,run,run,run,run,run,
    i+=1

print()

# While with a break

i = 0
while True:
    print(i,end=',') # 0,1,2,3,4,5,6,7,8,9,
    i+=1
    if i == 10:
        break # Breaks while loop within scope

print()

