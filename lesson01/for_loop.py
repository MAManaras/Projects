players = ["jordan", "maik", "lebron"]

for name in players:
    print(name + "plays!")


numbers = [1,2,3,4,5,6,7]
for i in range(1,len(numbers)+1,2):   # paei mexri 5-1
    print(i)


# dynamicaly create list

N = int(input("give N:"))

while N<3 or N>20:
    N = int(input("give me a number between 3 and 20"))

numbers = []

for cnt in range(0,N):
    numbers.append(int(input("Give me the " + str(cnt) + " number: ")))

print(numbers)

# mporw na xrisimopoihsw else sto telos tou for san teleutaia entoli meta apo oles tis epanalipseis

N = 5
for i in range(0, N):
     for j in range(0, N-i-1):
         print(" ", end=" ")
     for j in range(0, 2*i+1):
        print("*", end=" ")
     print(" ")
