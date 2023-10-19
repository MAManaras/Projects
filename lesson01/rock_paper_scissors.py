from random import randrange


round = 0
score = [0, 0] # player - computer
history = []

while True:
    round +=1
    print("Round " + str(round))

    # Eisodos xristi

    player_input = input("Choose: ")
    while player_input not in ["rock", "scissors", "paper"]:
        print("Wrong input. Choices: rock, scissors, paper")
        player_input = input("Choose: ")

    # Epilogi ipologisti

    computer_random = randrange(3)
    if computer_random == 0:
        computer_choice = "rock"
    elif computer_random == 1:
        computer_choice = "scissors"
    else:
        computer_choice = "paper"

    # elegxos tou poios nikise
    if player_input == "rock":
        if computer_choice == "rock":
            winner = "no winner"
        elif computer_choice == "paper":
            winner = "computer"
        else:
            winner = "player"
    elif player_input == "paper":
        if computer_choice == "rock":
            winner = "player"
        elif computer_choice == "paper":
            winner = "no winner"
        else:
            winner = "computer"
    else:                               # player_input == scissors
        if computer_choice == "rock":
            winner = "computer"
        elif computer_choice == "paper":
            winner = "player"
        else:
            winner = "no winner"

    # Diorthwsi tou score

    if winner == "player":
        score[0] += 1
    elif winner == "computer":
        score[1] += 1

    # Enimerwsi history
    history.append(f"Round {round}: Player: {player_input}, Computer: {computer_choice}, Score: {score[0]} - {score[1]}")

    # Ektupwsi tou nikiti kai tou score

    print(f"Computer picks:{computer_choice}")
    print("Player-Computer: " + str(score[0]) + "-" + str(score[1]))
    print("The winner is: " + winner)


    if score[0] == 3:
        print("The winner is: " + winner)
        print("")
        for history_item in history:
            print(history_item)
        break
    elif score[1] == 3:
        print("The winner is: " + winner)
        print("")
        for history_item in history:
            print(history_item)
        break
    print("------------------\n")









