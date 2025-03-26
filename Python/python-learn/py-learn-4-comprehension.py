x = [x for x in range(5)] # Define a for loop inside of a list. The x on the left side is the element we add in the list
print(x) # [0, 1, 2, 3, 4]
x = [x+10 for x in range(5)]
print(x) # [10, 11, 12, 13, 14]
x = [0 for x in range(5)]
print(x) # [0, 0, 0, 0, 0]
x = [i for i in range(100) if i % 5 == 0] # if i is divisible by 5 we add into the list
print(x) # [0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95]
x = tuple(i for i in range(100) if i % 5 == 0)
print(x) # (0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95)