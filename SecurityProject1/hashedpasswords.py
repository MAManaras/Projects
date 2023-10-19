import nacl.pwhash
import os
import ast
import re

# IMPORTANT: in the SecurityProject1 folder are included 3 .txt files (passwords.txt, verifyhashed.txt, newfile.txt)

# passwords.txt: contains dictionaries (usernames and hashed passwords)
# the passwords.txt contains already one dictionary-> username: anastasis123 , password: pass123

# verifyhashed.txt: contains popular passwords (one of them is pass123 which is the correct)

# newfile.txt: contains the username and the password if the dictionary attack was successful (now it is empty)

# dictattack.py: the dictionary attack program (it has also comments)

def passwordhashes():
    while True:
        username = input ('Enter username: ')   #ask user for username input
        reg = re.compile("^[A-Za-z]+[0-9]{3}$")     # check if input starts with letters and ends with 3 numbers (i.e format userXXX)
        if reg.match(username):         #if input matches the format break the while loop otherwise type again
            print("Valid username format")
            break
        else:
            print("Invalid username. Format should be userXXX")
    while True:
        password = input ('Enter password: ')   # ask user for password input
        if len(password) == 0:      # if user types no password (empty field), ask him to type again otherwise break the loop
            print("Empty password. Enter password again")
        else:
            break
    file1 = 'passwords.txt'     # define a passwords.txt file
    if os.path.exists(file1):      # if the file already exists open it for read and append
        mode = 'r+'

        passwd = password.encode('utf-8')   # encode the password input-->bytes
        hashed = nacl.pwhash.str(passwd)    # hash the bytes password
        f = open(file1, mode)       # open the file for r+
        dictionary = {      #define the dictionary
            "Username" : username,
            "Hashed" : hashed
        }
        if os.stat(file1).st_size > 0:          # if file1(passwords.txt is not empty
            for line in f.readlines():          # read all the lines of the file
                dict = ast.literal_eval(line)       #store the content of every line into a dictionary
                if username in dict.values():       # if the input username exists in a dictionary of the file, return
                    print("Cannot add user. User already exists")
                    return
        f.write(str(dictionary))                #otherwise append the file with the input values in the defined dictionary
        f.write('\n')
        print("username and hashed password saved")
    else:                       # if the file does not exist, create it and write(mode='w')
        mode = 'w'
        passwd = password.encode('utf-8')       #same process: encode the input password-->bytes
        hashed = nacl.pwhash.str(passwd)        # hash the bytes password
        f = open(file1, mode)               # open the file for w
        dictionary = {
            "Username": username,
            "Hashed": hashed
        }                                   # here we dont check if username exists because file is empty
        f.write(str(dictionary))            # and write input values in a dictionary to the file
        f.write('\n')
        print("username and hashed password saved")
    f.close()

# ------ main section -------
passwordhashes()    #call the function
