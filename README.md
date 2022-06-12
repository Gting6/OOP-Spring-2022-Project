# Footopia

## Authors
- [jimshao1999](https://github.com/jimshao1999)
- [Gting6](https://github.com/Gting6)
- [Ming0609](https://github.com/Ming0609)
- [yu02200059](https://github.com/yu02200059)

## Introduction
The Footopia system provides a friendly user interface and login function for “Members”, “Delivery Men” and “Restaurants”.

**Members:**

Members can place one or more orders to the restaurants. After the restaurants receive the orders, they can choose whether to accept the order or not, and inform the system after making the meal. The system will match the appropriate delivery driver, and the order will be tracked until the meal reaches the customer or the order expires.

**Restaurants:**

The restaurant can receive orders from different members, see the detailed information of the order, and choose whether to accept the order. After making the meal, the restaurant can choose the appropriate time to match the delivery men, and it will enter the matching system, until the order has been received by delivery men. Once the delivery man accepts it, the restaurant can immediately update and track the status of the order.

**Delivery Men:**

The delivery men can see the order and detailed information issued by the restaurant in the matching system, and can choose whether to accept the order or not. If they accept the order, they can choose to make a statement after the meal is delivered.

**Structure:**
We use the MVC pattern as shown in the figure below to develop our system. The system is executed through the Controller. Each Controller will be bound to a front-end view, and the presentation of the page will be controlled by jumping between Controllers. In addition, the Controller can also request the Model to return the required data, which acts as an important bridge in the front-end and databases, and also allows the development to be carried out in parallel, ensuring that each performs its own duties.

![](https://i.imgur.com/PNnRNNN.png)

## Environment version

1. Run in Java SE 11 or Java SE 17.
2. Download `JavaFx` and `mysql-connector` and link to reference library.

***Dependencies:***

![Dependencies](https://user-images.githubusercontent.com/46078933/173243517-75d19841-daf8-4efd-bd9f-cf24f6f019d8.png)

### Detail
Must:

`JAVA11`up

`JAVA17` [Recommend]

`JRE System Library`

`javafx-sdk-18.0.1`

`mysql-connector-java-8.0.29`

Other:

`annotations-13.0`

`google-maps-services-2.0.0`

`grpc-context-1.27.2`

`gson-2.8.9`

`kotlin-stdlib-1.4.10`

`kotlin-stdlib-common-1.4.0`

`okhttp-4.9.3`

`okio-2.8.0`

`opencensus-api-0.30.0`

`slf4j-api-1.7.33`

## How to Run the code
1. Clone the project.
2. Go to `\src\sql\DBConnection` and `\src\sql\DBCreation` and change the ***username*** and ***password*** to fit your **mysql** account.
3. Run `\src\sql\TestConnection` to build the database.
4. Run `\src\Application\Main`

The Detail can see the below Steps.

## Step1
Install the related jar above and setup them in eclipse.

The related jar can be search online or we have some jar in branch `feature/JarFiles`, go to the branch and you can see the jar files in NeededJar Folder(If fail to load this folder, you may need to download the related file by google).

## Step2
Open the project with eclipse and modify some setting.

Run the below files.

## Step3
### DB Seting:
setting the db port, username and password in

`src/sql/DBConnection.java`

![](https://i.imgur.com/6WP8yF4.png)

`src/sql/DBCreation.java`

![](https://i.imgur.com/4T2r0V7.png)

and save it not run it.

### Test and Create DB:
Then run the 
`src/sql/TestConnection.java`
to create the databases

### Setting the path of stores_detail.json in ReadJava.java

Setting in `src/sql/ReadJava.java`, modify the code below to ensure it can catch the stores_detail.json file, save it and run it.

![](https://i.imgur.com/TFE68kb.png)

Then you can find related information in db.

## Step4
Then you can try to run the src/application/Main.java(Maybe need to setting vm parameter to run javafx).

If success, Congratulations, then you will see the login page as below.

![](https://i.imgur.com/AX0BtcO.png)

If any error occur, delete the related data, and go to Step1.

## Quetion
If fail many time, you can try to open the application by our jar file.



