string = "Sample String"
print((string + " ") * 3)           # mult
print(string[1])                    # indexing
print(string[1:4] + string[-4:-1])  # slicing
print(len(string))                  # length
print(max("sample"))                # max
print(min("String"))                # min
print(string.index("am"))          # searching
print(string.count("S"))            # counting

#str[2] = "C" not working.. einai immutable

new_str = string[:2] + "c" + string[3:]
print(new_str)

print(",".join(["a", "b", "c"]))

print("split by blanks:" + str(string.split()))   # epistrefei lista me tis sumvoloseires tou string




