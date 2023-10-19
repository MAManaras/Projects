import socket

# start from line 35 (main)
# you can try www.hua.gr or your ip address or both together separated by comma (without space)

def portscanning(addrorurl, port):      # 2 parameters: 1st--> can be address or url(one or many) , 2nd-->the specifc port scanned between the range
    print("Scanning " +addrorurl+ " ...")           # print a message(which address or url is scanned this time)
    socket_obj = socket.socket(socket.AF_INET, socket.SOCK_STREAM)      # create socket connection and set a default timeout
    socket.setdefaulttimeout(1)
    res = socket_obj.connect_ex((addrorurl, port))              # here we return the result number from socket connection
    socket_obj.close()
    if res == 0:                # if the result of the connection is 0, then an open port found
        targetaddr = socket.gethostbyname(addrorurl)    # in targetaddr is holded the ip address
        targethost = socket.gethostbyaddr(addrorurl)       # in targethost is holded the host
        print("Open port found: " +str(port)+ ", for host: " +str(targethost)+ " , on ip address: " +targetaddr)     # print the port, the host and the ip
        return port     # return the open port
    else:
        print("Port " +str(port)+ " closed")    # else print a message that the scanned port is closed
        return None

def bannergrabbing(addr, port):     # 2 parameters: 1st--> can be address or url, 2nd--> the specific OPEN PORT of the ports list(contains all the open ports)
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)       # create a socket connection
    socket.setdefaulttimeout(1)
    try:                            # go inside the try block is something was grabbed
        s.connect((addr, port))     # create the connection
        s.send(b'Hello\r\n')        # send other useful information about the open port to the socket (in bytes format otherwise nothing is sent. 'Hello' is not printed)
        bannergrab = s.recv(1024)       # receive these information (the thing that is grabbed)
        s.close()
        print("Grabbed")            # print a message that grabbed some information
        print(str(bannergrab))          # print the grabbed information
    except:
        print("Nothing grabbed")


# -------- main section ---------
addr = input("Enter ip address(es) or/and url(s): ")    # first ask user to type one or more ip addresses or urls
start = int(input("Enter start port: "))        # ask user to type the start port
end = int(input("Enter end port: "))            # ask user to type the end port
ports = []                                      # create a list
for ip in addr.split(","):                      # separate the addr inputs (1st input) by comma in order to separate many addresses or urls and user can type many
    for p in range(start, end):                     # for port in the range of start port - end port
        open = portscanning(ip, p)                  # call the portscanning function with ip and p as inputs (open contains open ports that found)
        if open is None:                            # if open returns nothing, check the next port
            continue
        else:                                       # else append the open port to the ports list
            ports.append(open)
    for portf in ports:                             # for every value in the ports list (ports list contains all the open ports), call the bannergrabbing func
        bannergrabbing(ip, portf)                   # 1st param--> input address or url, 2nd param--> specific OPEN PORT
