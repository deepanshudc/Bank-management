# Bank-management 
This project is backend of a bank managment system consisting of Rest Apis.It consists of two entity Customer and Account.
In this, bank can create customer bank account whether it is Savings or Current account along with taking customers identity details.As of now it is taking firstname,lastname ,email and customer account details like account no. , balance etc.
Through this bank can also transfer money along banks account of different customers on the say on.
This project is for learning purpose of Springboot, Java and MySQL and for showcasing as a personal backend project on portfolio.

To run the project on your local system , take a clone on this repository and import it in Eclipse or IntelliJ as a Maven project and in application.prop file uncomment these line: 
#spring.datasource.username=root
#spring.datasource.password=
Here you have to have a Mysql server running on your local system ,make sure you have a username 'root' in your MySql db and set the same password in spring.datasource.password as same in your Mysql server running on your local system.

Also take care of the port running on your local server referencing it from this line in application.prop file : 
spring.datasource.url=jdbc:mysql://localhost:3306/bank?createDatabaseIfNotExist=true
Here make your  MySQL instance running on port 3306 and to use it and check your db , you can use MySQL Workbench on your local system.

