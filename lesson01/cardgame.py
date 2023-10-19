import random


kind = {"heart", "diamond", "spade", "club"}
number = {"ace", 2, 3, 4, 5, 6, 7, 8, 9, 10,"jack", "queen", "king"}

deck = {(k,n) for k in kind for n in number}

player1 = set()
player2 = set()

list_deck = list(deck)
print(list_deck)

for _ in range(5):
    pos1 = random.randrange(len(list_deck))
    player1.add(list_deck.pop(pos1))
    pos2 = random.randrange(len(list_deck))
    player2.add(list_deck.pop(pos2))

# kare tou assou
cnt = 0          # metrima asswn
for card in player1:                #player1
    if card[1] == "ace":
        cnt += 1
if cnt == 4:
    print("O paixtis Player 1 exei kare")

for card in player2:                #player2
    if card[1] == "ace":
        cnt += 1
if cnt == 4:
    print("O paixtis Player 2 exei kare")

# elenxw an exw kenta

hand_numbers = []
for card in player1:                #player1
    if card[1] == "ace":
        hand_numbers.append(1)
    elif card[1] == "jack":
        hand_numbers.append(11)
    elif card[1] == "queen":
        hand_numbers.append(12)
    elif card[1] == "king":
        hand_numbers.append(13)
    else:
        hand_numbers.append(card[1])

hand_numbers.sort()

# elegxos an einai kata 1 megalutero to epomeno
for pos1 in range(4):
    if (hand_numbers[pos1 + 1]  - hand_numbers[pos1]) == 1:
        break
else:
    print(" o paixtis 1 exei kenta")




print(hand_numbers)


print(player1)
print(player2)
