x = [4,True,'Hi'] # List -> Bunch of diff elements , Ordered , Mutable
print(x) # [4,True,'Hi']
print(len(x)) # 3
x.append(False);
print(x) # [4, True, 'Hi', False]
x.extend([2,3,4]);
print(x) # [4, True, 'Hi', False, 2, 3, 4]
x.pop() # Remove last element
print(x) # [4, True, 'Hi', False, 2, 3]
x.pop(2) # 2 is the index
print(x) # [4, True, False, 2, 3]
print(x[0]) # 4
x[0] = 'Hello' # List are mutable
print(x) # ['Hello', True, False, 2, 3]

# Tuple -> Immutable List , cannot append

x = (4,True,'Hi')
print(x) # (4, True, 'Hi')

# Tuple Inside List

x = [('H','I'),1]
print(x[0]) # ['H', 1]


# Sclice Operator -> sliced = [start:stop:step]

x = [0,1,2,3,4,5,6,7,8,9]
y = ['hi','hello','goodbye','cya','sure']
s = 'Hello'

sliced = x[0:4:2] # [0, 2] -> So we start at 0 index , go to 4th index but do not include it, increment by 2
sliced = x[:4] # [0, 1, 2, 3] -> 4 is stop here , and automatically the start is 0 , increment is 1
sliced = x[2:] # [2, 3, 4, 5, 6, 7, 8, 9] -> 2 is start here, and automatically the stop is last index, increment is 1
sliced = x[4:2:-1]  # [4, 3] We go in reverse. Simple
sliced = x[::-1] # [9, 8, 7, 6, 5, 4, 3, 2, 1, 0] -> Whole list Reversed
print(sliced) 

s = 'Hello'
sliced = s[::-1] # olleH -> String Reversed
print(sliced)


# SETS -> Great for Loop-ups , no order

x = set()
s = {4,32,2,2}
print(type(x)) # <class 'set'>
print(type(s)) # <class 'set'>
print(type({})) # <class 'dict'> -> Diff from set
print(s) # {32, 2, 4}
s.add(10)
print(s) # {32, 2, 10, 4}
s.remove(2); # 2 -> Element
print(s) # {32, 10, 4}
print(4 in s) # True -> Check element in Set


s = {1,2,3,4}
s2 = {3,4,5,6}

print(s.union(s2)) # {1, 2, 3, 4, 5, 6}
print(s.intersection(s2)) # {3, 4}


# Dictionary -> Similar to HashTable , no specific data type

x = {'A':1}
print(x['A']) # 1
print(x) # {'A': 1}
x['B'] = 2
print(x) # {'A': 1, 'B': 2}
x[3] = 3
print(x) # {'A': 1, 'B': 2, 3: 3}
x[4] = [1,2,3,4]
print(x) # {'A': 1, 'B': 2, 3: 3, 4: [1, 2, 3, 4]}

print('A' in x) # True -> Check key in dic
print(x.values()) # dict_values([1, 2, 3, [1, 2, 3, 4]])
print(list(x.values())) # [1, 2, 3, [1, 2, 3, 4]] -> Practical
print(list(x.keys())) # ['A', 'B', 3, 4]

del x['B']
print(x) # {'A': 1, 3: 3, 4: [1, 2, 3, 4]}

for k,v in x.items():
    print(k,v)


# A 1
# 3 3
# 4 [1, 2, 3, 4]

for k in x:
    print(k,x[k])

# A 1
# 3 3
# 4 [1, 2, 3, 4]