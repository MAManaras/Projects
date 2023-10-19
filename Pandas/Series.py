import numpy as np
import pandas as pd

labels = ['a','b','c']
my_list = [10,20,30]
arr = np.array([10,20,30])
d = {'a':10,'b':20,'c':30}

pd.Series(data=my_list, index=labels)


ser1 = pd.Series([1,2,3,4], ['USA', 'Germany','USSR', 'Japan'])

ser2 = pd.Series([1,2,5,4], ['USA', 'Germany','Italy', 'Japan'])

print(ser1)

print(ser2)

print(ser1 + ser2)




