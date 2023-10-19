import socket
import ssl
import datetime

# start from line 33 (main)

def evaluate_key(date1, issuers, subject, version):
    hostname = 'www.hua.gr'     # define the hostname
    context = ssl.create_default_context()      # create an ssl context with default settings
    with context.wrap_socket(socket.socket(), server_hostname=hostname) as s:
        s.connect((hostname, 443))      # connect to the host
        certificate = s.getpeercert()       # get the certificate's info
        print(certificate)                  # print the certificate

        cert_start = datetime.datetime.strptime(certificate['notBefore'], r'%b %d %H:%M:%S %Y %Z')  # get the 'notBefore' attribute from the certificate into a specific format
        after_start = date1 - cert_start        # cert_start ('notBefore') attribute must be smaller than the date1 parameter. Store the deduction in 'after_start' variable

        cert_exp = datetime.datetime.strptime(certificate['notAfter'], r'%b %d %H:%M:%S %Y %Z')     # get the 'notAfter' attribute from the certificate into a specific format
        before_exp = cert_exp - date1       # cert_expr ('notAfter') attribute must be greater than the date1 parameter. Store the deduction in 'befor_exp'

        cert_issuer = dict(x[0] for x in certificate["issuer"]).get("organizationName")     # get issuer's organization name from the certificate's specific dictionary

        cert_subject = dict(x[0] for x in certificate["subject"]).get("organizationName")   # get subject's organization name from the certificate's specific dictionary

        if (after_start.days > 0) & (before_exp.days > 0) & (cert_issuer in issuers) & (    #check if 'after_start' variable in days format is positive number (MEANS THAT DATE1 IS BIGGER THAN NOTBEFORE)
            cert_subject == subject) & (certificate.get('version') == version):     # check if 'before_exp' variable in days format is also positive (MEANS THAT DATE1 IS SMALLER THAN NOTAFTER)
            print('Evaluation success.. Valid certificate!')         # check if certificate's issuer exists in our issuers parameter list
        else:                                           # check if certificate's subject is equal to our subject input parameter
            print('Evaluation error.. Invalid certificate!')         # check if version is 3
        s.close()                                                  # print a message if the ssl certicate evaluation is successful

# ------ main section ------
while True:
    opt = input("Press '1' to type the date or press '2' to get current system's date: ")     # 2 options for date input
    option = int(opt)
    if option == 1:         # 1st option: user input, ask user to type year-month-day (
        year = int(input('Enter year: '))
        month = int(input('Enter month: '))
        day = int(input('Enter day: '))
        date1 = datetime.datetime(year, month, day)     # create a date variable from user's input
        break
    elif option == 2:       #2nd option: system date
            date1 = datetime.datetime.now()
            print(date1)
            break
    else:
        print('Unsupported option. 1 for input date or 2 for system date')     #option must be 1 or 2 otherwise type option again

issuers_list = ['pedfaes', 'asdfff', 'TERENA']      # create an issuers list ('TERENA' is the valid hua issuer)

subject1 = input("Enter subject: ")         # ask user for the subject('Harokopio University' is the valid hua subject)
                                            # in order to match certificate's subject, user must type 'Harokopio University'
evaluate_key(date1, issuers_list, subject1, 3)      # pass the values into the function
                            #date1:input or systems date, issuers_list:the above list(line 49), subject1:subject input, version:3