# raise Exception('Bad') => Equivalent to throw

try:
    x = 6 / 0
except Exception as e:
    print('Exception:',e)
finally:
    print('Finally Block')

