# OOP-Spring-2022-Project

## Environment version
Must:
JAVA11up
JAVA17 [Recommend]
JRE System Library
javafx-sdk-18.0.1
mysql-connector-java-8.0.29

Other:
annotations-13.0
google-maps-services-2.0.0
grpc-context-1.27.2
gson-2.8.9
kotlin-stdlib-1.4.10
kotlin-stdlib-common-1.4.0
okhttp-4.9.3
okio-2.8.0
opencensus-api-0.30.0
slf4j-api-1.7.33

## Step1
Install the related jar above and setup them in eclipse.
The related jar can be search online or we have some jar in NeededJar.zip.

## Step2
Open the project with eclipse and modify some setting.
Run the below files.

## Step3
### DB Seting:
setting the db port, username and password in
src/sql/DBConnection.java
![](https://i.imgur.com/6WP8yF4.png)

src/sql/DBCreation.java
![](https://i.imgur.com/4T2r0V7.png)
and save it not run it.

### Test and Create DB:
Then run the 
src/sql/TestConnection.java
to create the databases

### Setting the path of stores_detail.json in ReadJava.java
setting in src/sql/ReadJava.java, modify the code below to ensure it can catch the stores_detail.json file, save it and run it.
![](https://i.imgur.com/TFE68kb.png)
Then you can find related information in db.

## Step4
Then you can try to run the src/application/Main.java(Maybe need to setting vm parameter to run javafx).

If success, Congratulations, then you will see the login page as below.

![](https://i.imgur.com/AX0BtcO.png)

If any error occur, delete the related data, and go to Step1.

## Quetion
If fail many time, you can try to open the application by our jar file.

