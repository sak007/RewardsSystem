# DBMSProject1

Team Name- T14

Team Members:
Ashok Kumar Selvam
Arvind Srinivas Subramanian
Sumedh Sanjay Salvi
Aakash Satish Poliyath

The default database instance is asubram9. If you are changing the database instance please follow below steps else if you are keeping the DB instance default, 
then jump directly to Step 2
Step 1:
The database username and password is stored in application.properties file under src/main/resources folder.
If you are changing the database then please edit the username/password in above file and execute below files in order.
1)DDL.sql
2)DML.sql

Step 2:
Connect using NCSU network/VPN
Under 540-P1-T14\DBMSProject1\ folder
Oracle JDBC is added as part of maven dependency, so no need to add OJDBC in classpath. Run below command directly.
java -jar DBMSProject1-1.0-SNAPSHOT-jar-with-dependencies.jar

Application will be up and running.

Available users are as below
Admin username and password is admin,admin.
Brand usernames are Brand01,Brand02,Brand03 and password is abcd1234
Customer usernames are C0001,C0002,C0003,C0004,C0005 and password is abcd1234

Follow steps as per Application flow document to test all functionalities

