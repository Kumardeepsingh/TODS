Tiffin Order & Delivery System (TODS)

Project Description:
The Tiffin Order & Delivery System (TODS) is a POS system designed for managing food orders in schools. It allows employees to take orders over the phone and deliver them efficiently to school students. The system handles customer information, stores orders in a MySQL database, and helps schedule drivers for deliveries. The goal is to improve order processing, record keeping, and overall efficiency.

Features:
Order Management: Employees can take orders from customers, store details like customer information, order items, and delivery addresses in the database.
Driver Scheduling: The system helps management schedule drivers for deliveries to schools.
Customer Information: It stores customer names, addresses, billing information, and preferences for recurring orders.
Security: Ensures the security of customer data by restricting access to staff members with unique IDs and passwords.
User-Friendly Interface: Designed for cashiers and drivers to easily manage orders and deliveries.

Technology Stack:
Programming Language: Java (JavaFX)
Database: MySQL
Tools: Eclipse IDE, Scene Builder, MySQL Workbench, XAMPP
Operating System: Windows 10
Installation:
Clone the repository from GitHub.
Install Java and MySQL.
Set up MySQL using the provided database schema.
Run the JavaFX application using Eclipse or any compatible IDE.

How to Use:
Employee Login: Log in using employee credentials (cashier or driver).
Order Process: Cashiers create or search for customer profiles, add items to the order, and complete the order.
Delivery: Drivers can log in to view and confirm order deliveries.
Order Modification: Customers can modify their orders before they are finalized.

Authors:
Gurkamal Bassi,
Sukhveer Sohi,
Prabhjot Saddi,
Balmohkam Singh,
Kumardeep Singh

Screenshots:

Employee Login:
![image](https://github.com/user-attachments/assets/33ecd682-a6ed-4f51-95af-d003f7dae8c8)
If Employee Forget Password:
![image](https://github.com/user-attachments/assets/a2d81e5e-9260-42ab-b89b-f4257b38c85e)
Type of  Customer :
![image](https://github.com/user-attachments/assets/f82c8c15-3ab5-4e3d-962f-b71c5703452f)
New Customer:
![image](https://github.com/user-attachments/assets/acd5e780-dcfe-4e3c-aec7-5fa6cd8f13c6)
New customer added to the customer table in the database:
![image](https://github.com/user-attachments/assets/63f58f77-830d-49be-9e20-170fe28d7bda)
New dependant added to the dependant table in the database:
![image](https://github.com/user-attachments/assets/0335e777-8c8a-4c59-a5a1-cd889cef4c89)
Existing Customer:
![image](https://github.com/user-attachments/assets/b690518f-8ea6-4214-84ab-c009e3754b41)
![image](https://github.com/user-attachments/assets/fb0e225b-1537-4053-9c91-ae0eb64efb5b)
![image](https://github.com/user-attachments/assets/eff19314-c187-4a28-826c-cca815384fa9)
![image](https://github.com/user-attachments/assets/838d18f4-6c23-4e8d-a65c-b97e19827850)
![image](https://github.com/user-attachments/assets/af000b6e-6561-4ef2-afa5-0c3f2fe59e3b)
![image](https://github.com/user-attachments/assets/ac368f02-45b4-4e8d-93c2-8debd090b83d)
Driver Confirm Pickup:
![image](https://github.com/user-attachments/assets/df0e5595-8fda-4c94-8901-07b7f6e7706f)

SQL Files

Database Structure:


Create DATABASE tods;

Use tods;

CREATE TABLE Customer(
    CustomerID int(4) AUTO_INCREMENT NOT NULL,
    FirstName varchar(50),
    LastName varchar(50),
    PhoneNumber bigint(10),
    Email varchar(50),
    Address varchar(50),
    City varchar(50),
    Province varchar(2),
    PostalCode varchar(6),
    PRIMARY KEY(CustomerID)
);

CREATE TABLE School(
    SchoolID int(4) AUTO_INCREMENT NOT NULL,
    SchoolName varchar(50),
    PhoneNumber bigint(10),
    Address varchar(50),
    City varchar(50),
    Province varchar(2),
    PostalCode varchar(6),
    PRIMARY KEY(SchoolID)
);

CREATE TABLE Dependant(
    DependantID int(4) AUTO_INCREMENT NOT NULL,
    FirstName varchar(50),
    LastName varchar(50),
    CustomerID int NOT NULL,
    SchoolID int NOT NULL,
    PRIMARY KEY(DependantID),
    FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID),
    FOREIGN KEY(SchoolID) REFERENCES School(SchoolID)
);

CREATE TABLE EmployeeType(
    EmployeeTypeID int(4) AUTO_INCREMENT NOT NULL,
    TypeName varchar(50),
    PRIMARY KEY(EmployeeTypeID)
);

CREATE TABLE Employee(
    EmployeeID int(4) AUTO_INCREMENT NOT NULL,
    FirstName varchar(50),
    LastName varchar(50),
    PhoneNumber bigint(10),
    Email varchar(50),
    SIN bigint(9),
    Address varchar(50),
    City varchar(50),
    Province varchar(2),
    PostalCode varchar(6),
    Username varchar(50),
    Password varchar(50),
    EmployeeTypeID int NOT NULL,
    PRIMARY KEY(EmployeeID),
    FOREIGN KEY(EmployeeTypeID) REFERENCES EmployeeType(EmployeeTypeID)
);

CREATE TABLE MyOrder(
    OrderID int(4) AUTO_INCREMENT NOT NULL,
    TotalPrice decimal(10,2),
    OrderDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    DependantID int NOT NULL,
    PRIMARY KEY(OrderID),
    FOREIGN KEY(DependantID) REFERENCES Dependant(DependantID)
);

CREATE TABLE ProductCategory(
    CategoryID int(4) AUTO_INCREMENT NOT NULL,
    Name varchar(50),
    PRIMARY KEY(CategoryID)
);

CREATE TABLE Product(
    ProductID int(4) AUTO_INCREMENT NOT NULL,
    Name varchar(50),
    Price decimal(10,2),
    CategoryID int NOT NULL,
    PRIMARY KEY(ProductID),
    FOREIGN KEY(CategoryID) REFERENCES ProductCategory(CategoryID)
);

CREATE TABLE Order_Product(
    OrderProductID int(4) AUTO_INCREMENT NOT NULL,
    OrderID int NOT NULL,
    ProductID int NOT NULL,
    Quantity int,
    PRIMARY KEY (OrderProductID),
    FOREIGN KEY (OrderID) REFERENCES MyOrder(OrderID),
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);


Raw Data Input:

USE tods;

INSERT INTO Customer(CustomerID, FirstName, LastName, PhoneNumber, Email, Address, City, Province, PostalCode)
VALUES(1001,'Jhon','Wick',6045492812,'jhon.wick@gmail.com', '12339 72 Avenue', 'Surrey', 'BC', 'V3W1K6'),
       (1002,'Tony','Stark',6045492711,'tonystark@gmail.com', '14825 68 Avenue', 'Delta', 'BC', 'V3W1Y5'),
  (1003,'Bruce','Wayne',6045492612,'brucewayne@gmail.com', '15268 74 Avenue', 'Surrey', 'BC', 'V3W2l6'),
  (1004,'Cory','Gen',6045412371,'cory123@gmail.com', '12154 56 Avenue', 'Surrey', 'BC', 'V2Y6W0'),
  (1005,'Billy','Ban',6045492222,'ban2411@gmail.com', '7983 118 Street', 'Delta', 'BC', 'V3W1L5'),
  (1006,'Lilly','Jane',6048992721,'janelilly@gmail.com', '14825 68 Avenue', 'Delta', 'BC', 'V3W1Y5'),
  (1007,'Chulbul','Pandey',6045493721,'chulbulpandey@gmail.com', '8045 112 Street', 'Delta', 'BC', 'V3W2Z5'),
  (1008,'Ganesh','Gaitonde',6045494556,'ganeshgaitonde@gmail.com', '15932 56 Avenue', 'Surrey', 'BC', 'V3W6R9');

INSERT INTO EmployeeType(EmployeeTypeID, TypeName)
VALUES(8001, 'staff'),
      (8002, 'Driver');

INSERT INTO Employee ( EmployeeID , FirstName, LastName, PhoneNumber, Email , SIN , Address , City, Province , PostalCode,Username, Password, EmployeeTypeID)
VALUES (3001 , 'Ram' , 'Nait' , 6045652256, 'ramnait@gmail.com' , 940852741 , '6925 126A st' , 'Surrey' , 'BC' , 'V3W1K6', 'Nait', 'nait', 8001),
       (3002 , 'Kamal' , 'Kaur' , 6045651478, 'kamalg@gmail.com' , 94052725 , '6970 127A st' , 'Surrey' , 'BC' , 'V3W2K6','Kamal', 'Kamal', 8001),
  (3003 , 'Raman' , 'Preet' , 6045653356, 'raman@gmail.com' , 940856741 , '7925 140A st' , 'Surrey' , 'BC' , 'V3W2P6', 'Raman', 'Raman', 8001),
  (3004 , 'Khushi', 'Sharma' , 6045652256, 'khushigami@gmail.com' , 940878741 , '12439 68 Avenue' , 'Surrey' , 'BC' , 'U3K1P9',  'Khushi', 'Khushi',8001),
  (3005 , 'Gurpreet' , 'Sahi' , 7785652256, 'sahisaab@gmail.com' , 940123741 , '12440 70 Avenue', 'Surrey' , 'BC' , 'V31K6','Gurpreet' , 'Gurpreet' , 8002),
  (3006 , 'Bal' , 'Singh' , 6044852256, 'balsingh@gmail.com' , 940952741 , '12880 75 Avenue' , 'Surrey' , 'BC' , 'C1P1K6','Bal' ,'Bal' , 8002),
  (3007 , 'Sapna' , 'Gagg' , 2365652256, 'sapna1@gmail.com' , 940252741 , '13285 80 Avenue', 'Surrey' , 'BC' , 'C3O1K6','Sapna' ,'Sapna' , 8002),
  (3008 , 'Arsh' , 'Deep' , 236652856, 'deeparsh@gmail.com' , 940652741 , '15287 84 Avenue' , 'Surrey' ,'BC' , 'B9P1K6',  'Arsh' , 'Arsh' ,8002);

INSERT INTO School(SchoolID, SchoolName, PhoneNumber, Address, City, Province, PostalCode)
VALUES(2172,'North Ridge Elementary', 6045993900,'13460 62 Avenue','Surrey','BC','V3X2J2' ),
      (2173,'Khalsa School Newton', 6045912248,'6933 124 St','Surrey','BC','V3W1P2' ),
      (2174,'Princess Margaret Secondary School', 6045945458,'12870 72 Avenue','Surrey','BC','V3W2M9' ),
      (2175,'Hyland Elementary', 6045439347,'6677 140 St','Surrey','BC','V3W5J3' ),
      (2176,'Frank Hurt Secondary School', 6045901211,'13940 77 Avenue','Surrey','BC','BCV3W' );

INSERT INTO Dependant( DependantID, FirstName, LastName, CustomerID, SchoolID)
VALUES (2001 , 'Garry', 'Bassi' , 1001 , 2172 ),
       (2002 , 'Sukh ', 'Gill' , 1002 , 2173 ),
       (2003 , 'Raman', 'Malik' , 1003 , 2174 ),
       (2004 , 'Meera', 'Rai' , 1004 , 2175 ),
       (2005 , 'Sham', 'Singh' , 1005 , 2176 ),
       (2006 , 'Sharan', 'Baath' , 1006 , 2175 ),
       (2007 , 'Manik', 'Sethi' , 1007 , 2176 ),
       (2008 , 'Kumar', 'Sidhu' , 1008 , 2173 );

INSERT INTO ProductCategory ( CategoryID, Name )
   VALUES ( 123, 'Salad'),
          ( 124, 'Drinks'),
          ( 125, 'Desert' ),
          ( 126, 'Dish' );
INSERT INTO Product ( ProductID , Name , Price , CategoryID )
    VALUES          ( 15000 , 'Caesar Salad' , 27.00 , 123 ),
                    ( 15001 , 'Green Salad' , 29.00 , 123 ),
                     ( 15002 , 'Cobbl Salad' , 35.00 , 123 ),
                     ( 15003 , 'Chicken Salad' , 26.50 , 123 ),
                     ( 15004 , 'Juice' , 6.15 , 124 ),
                     ( 15005, 'Energy drink' , 10.45 , 124 ),
                     ( 15006 , 'Pepsi' , 4.00 , 124 ),
                     ( 15007 , 'Coca-Cola' , 3.00 , 124 ),
                     ( 15008, 'Pasta' , 10.00 , 126 ),
                     ( 15009, 'Curry' , 15.00 , 126 ),
                     ( 15010 , 'Soup' , 5.00 , 126 ),
                     ( 15011 , 'Burgers' , 7.00 , 126 ),
                     ( 15012 , 'Macaroni' , 15.45 , 126 ),
                     ( 15013 , 'Dhokla' , 25.00 , 126 ),
                     ( 15014 , 'Masala Dosa' , 10.00 , 126 ),
                     ( 15015 , 'Hyderabadi biriyani' , 30.00 , 126 ),
                     ( 15016 , 'Masala Dosa' , 25.00 , 126 ),
                     ( 15017 , 'Sushi' , 14.00 , 126 ),
                     ( 15018 , 'Teriyaki' , 7.77 , 126 ),
                     ( 15019 , 'Spring Rolls' , 5.55 , 126 ),
                     ( 15020 , 'Kung Pao Chicken' , 17.00 , 126 ),
                     ( 15021 , 'Spicy Tofu' , 13.00 , 126 ),
                     ( 15022 , 'Strawberry Cheesecake' , 1.00 , 125 ),
                     ( 15023 , 'Apple Pie' , 2.00 , 125 ),
                     ( 15024 , 'Rasgulla' , 3.00 , 125 ),
                     ( 15025 , 'Rabri' , 12.00 , 125 ),
                     ( 15027 , 'Rasmalai' , 8.00 , 125 ),
                     ( 15028 , 'Dragon beard candy' , 4.00 , 125 ),
                     ( 15029 , 'Mooncake' , 8.00 , 126 ),
                     ( 15030 , 'Sweet rice balls' , 6.00 , 125 ),
                     ( 15031 , 'Prosperity cake ' , 5.00 , 125 );


INSERT INTO MyOrder( OrderID, TotalPrice, OrderDate, DependantID)
VALUES ( 8892, 35.55 , '2020/03/09' , 2001 ),
        ( 8893, 40.00 , '2020/03/15' , 2002 ),
        ( 8894, 95.35 , '2020/03/09' , 2003 ),
        ( 8895, 100.00 , '2020/03/20' , 2004 ),
        ( 8896, 35.55 , '2020/03/31' , 2005 ),
        ( 8897, 25.66 , '2020/03/26', 2006 ),
        ( 8898, 80.55, '2020/03/28' , 2007 ),
        ( 8899, 185.00 , '2020/03/05' , 2008 );

INSERT INTO Order_Product (OrderProductID , OrderID , ProductID , Quantity)
VALUES                    (12345, 8892   , 15000     ,  2 ),
                           (12346, 8892   , 15001    ,  5 ),
                           (12347, 8893 , 15004 , 2 ),
                           (12348, 8894   , 15005     ,  2 ),
                           (12349, 8894   , 15007     ,  4 ),
                           (12350, 8892   , 15006     ,  3 ),
                           (12351, 8892   , 15008     ,  2 ),
                           (12352, 8895   , 15012     ,  7 ),
                           (12353, 8892   , 15015     ,  2 ),
                           (12354, 8896   , 15016    ,  2 ),
                           (12355, 8893   , 15017     ,  3 ),
                           (12356, 8896   , 15018     ,  4 ),
                           (12357, 8892   , 15019     ,  2 ),
                           (12358, 8897   , 15020     ,  2),
                           (12359, 8898   , 15021     ,  5),
                           (12360, 8898   , 15021     ,  2 ),
                           (12361, 8892   , 15025    ,  6 );









