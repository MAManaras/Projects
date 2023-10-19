my_list = [   # dipli lista
    [1,2,3],
    [4,5,6],
    [7,8,9]
]

array = []
rows = int(input("Give number of rows: "))
cols = int(input("Give number of columns: "))
for i in range(rows):
     array.append([])
     for j in range (cols):
        elem = int(input("Give " + str(i) + " " + str(j) + " element: "))
        array[i].append(elem)

print(array)
print(my_list)






