# Harokopio Feeding Application System

Requirements:
* A mysql/mariaDB database.
* A working tomcat server


To setup, create `src/main/java/resources/application.properties` with the following content:
```properties
jdbc.url=jdbc:mysql://url:3306/dsa
jdbc.user=dsa
jdbc.password=password
```
Replacing the above with the connection information for your database.

After that simply compile and run on the server.