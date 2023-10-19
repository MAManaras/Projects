import numpy as np

arr_2d = np.array([[0,1,2],[3,4,5],[6,7,8]])
print(arr_2d)

print(arr_2d[1,2])      # komma notation  picking cell

print(arr_2d[:2, 1:])       # line, column , slicing from start until 2 ///// slicing from 1 until end

array = np.arange(10)
print(array)

# IMPORTANT

print(array[array < 3])



