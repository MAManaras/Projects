import nacl.pwhash
import ast

# here is the dictionary attack

def verifyhashes(file1, file2):     # 2 inputs: the file with the dictionary(username-hashes), the file with popular passwords
        f2 = open(file2, 'r')       # open the file with the popular password for reading
        lines2 = f2.read().split('\n')      # spit with new line and read every line of the popular passwords file
        for l2 in lines2:                   # for every line of the popular passwords file
            passwd = l2.encode('utf-8')     # encode to bytes every password in the popular passwords file
            f1 = open(file1, 'r')           # open the first file with the hashed passwords for read
            for line in f1.readlines():        # read all the lines of the first file
                dictionary = ast.literal_eval(line)         # each line contains a dictionary, so take every line as a dictionary
                for key, value in dictionary.items():       # for the items of every dictionary in the file
                    try:
                        res = nacl.pwhash.verify(value, passwd)     # check if value(hashed password of dictionary) matches with our encoded popular password
                        print(res)                                  # print the result if true
                        print("A matching password found")
                        fnew = open('newfile.txt', 'a')             # create a new file for append
                        fnew.write(dictionary['Username'] + '\t' + passwd.decode('utf-8') + '\n')      # if a matched password found, save it to the new file with its username
                        print("Matching username and password added to the new file")
                        fnew.close()
                    except:
                        print("Passowrd: " + passwd.decode('utf-8') + " does not match")       # in the except part print a message if passwords dont match
            f1.close()
        f2.close()

# -------- main section ---------
verifyhashes("passwords.txt", "verifyhashed.txt")       # call the function with parameters the first file(username-hashes) and the second file(popular passwords)
