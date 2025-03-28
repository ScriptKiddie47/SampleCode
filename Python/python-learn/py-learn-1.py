print(4.5,"Hello",end='|') # end='|' -> Carriange Return. Default is '\n' which is a newline
print(4.5,"Hello")

f_name = 'john'
l_name = 'doe'
print(f_name,l_name) # john doe

# age = input('Age: ')


# num = int(input('n1: '))
# print( num - 10 )


print(type(f_name)) # <class 'str'> -> Object of str
print(f_name.upper()) # JOHN
print(f_name.count('j')) # 1


x = 'H'
y = 3
print(x * y) # HHH -> This is unique


print('a' > 'Z')
print(ord('Z'),ord('a')) # 90 97 -> ASCII

f_name = 'john'

if f_name == 'cool':
    print('You are great')
elif f_name == 'john':
    print('You are john')
else:
    print('You are a fool')




# F Strings

x = f'Hello {6+8}'
print(x) # Hello 14