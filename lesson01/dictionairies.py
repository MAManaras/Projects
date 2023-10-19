person = {
    "name": "Tony Stark",
    "alias": "Iron Man",
    "power": 20
}
print(person)

# prosvasi se timi kleidiou

n = person["power"]
print(n)
# tropopooisi kleidiou name

person["name"] = "Harvey"

# neo kleidi

person["fly_meters"]= "100"

print(person)

# epanalipsi se dictionairies

for key,value in person.items():
    pass

for key in person.keys():
    pass

for values in person.values():
    pass


person["name"] = [1,2,3]
print(person)





