# OCP8 Preparation

## Chapter 10 - JDBC

### The Code to Set Up the Database

Derby installation see: https://db.apache.org/derby/papers/DerbyTut/install_software.html

Move starting from the project root into:

```
$ cd src/main/java
$ javac de/holhar/ocppreparation/ch10_jdbc/sec01_intro/SetupDerbyDatabase.java
$ java -cp "/opt/Apache/db-derby-10.15.1.3-bin/lib/derby.jar:." de.holhar.ocppreparation.ch10_jdbc.sec01_intro.SetupDerbyDatabase
```