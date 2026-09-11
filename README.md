\# 💳 Digital Wallet Application



A console-based \*\*Digital Wallet Application\*\* developed using \*\*Java 17, JDBC, and MySQL 8\*\*.



The application simulates the basic functionality of a digital payment wallet. Users can register and log in, create wallets, add money, check balances, send money to other users, view transaction history, and manage personal expenses.



This project demonstrates practical implementation of \*\*Java, JDBC, MySQL, SQL CRUD operations, PreparedStatement, ResultSet, transaction management using commit and rollback, validation, aggregation queries, and monthly expense reporting\*\*.



\---



\## 📌 Project Overview



The Digital Wallet Application is designed to provide a simple wallet management system using Java and MySQL.



The application follows a layered package structure where different operations are separated into individual classes.



\### Main Modules



\* 👤 User Management

\* 💳 Wallet Management

\* 💰 Money Transfer

\* 📜 Transaction Management

\* 💸 Expense Management

\* 📊 Monthly Expense Reporting

\* 🗄️ Database Connectivity



The application is currently implemented as a \*\*console-based Java application\*\*.



\---



\## 🎯 Project Objectives



The main objectives of this project are:



1\. Build a Java-based digital wallet system.

2\. Connect Java with MySQL using JDBC.

3\. Implement SQL CRUD operations.

4\. Manage user registration and login.

5\. Create and manage digital wallets.

6\. Allow users to add money.

7\. Allow users to transfer money between wallets.

8\. Maintain transaction records.

9\. Implement transaction management using `commit()` and `rollback()`.

10\. Manage and track user expenses.

11\. Generate monthly expense reports.

12\. Demonstrate practical database-driven Java application development.



\---



\# 🚀 Features



\## 1. User Registration



Users can register by providing:



\* Name

\* Email

\* Password



Example:



```text

\--- User Registration ---



Enter Name: Avi

Enter Email: avi@gmail.com

Enter Password: 12345



User Registered Successfully

```



\---



\## 2. User Login



Registered users can log in using their email and password.



Example:



```text

\--- User Login ---



Enter Email: avi@gmail.com

Enter Password: 12345



Login Successful

Welcome Avi

```



\---



\## 3. Create Wallet



A registered user can create a digital wallet.



Example:



```text

\--- Create Wallet ---



Enter User ID: 5



Wallet Created Successfully

```



\---



\## 4. Add Money



Users can add money to their wallet.



Example:



```text

\--- Add Money ---



Enter User ID: 5

Enter Amount: 15000



Money Added Successfully



Before:

User 5 → ₹0



After adding ₹15,000:

User 5 → ₹15,000

```



\---



\## 5. Check Balance



Users can check their current wallet balance.



Example:



```text

\--- Check Balance ---



Enter User ID: 5



Current Balance: 15000.0

```



\---



\## 6. Send Money



Users can transfer money from their wallet to another user's wallet.



Example:



```text

\--- Send Money ---



Enter Sender User ID: 1

Enter Receiver User ID: 5

Enter Amount: 2000



Money Sent Successfully

```



The transfer operation uses database transaction management to ensure that the sender's deduction and receiver's addition are handled safely.



The transaction is committed using:



```java

connection.commit();

```



If an error occurs, the transaction can be rolled back using:



```java

connection.rollback();

```



\---



\## 7. Transaction History



Users can view their previous money transfer transactions.



Example:



```text

\--- Transaction History ---



Enter User ID: 1



\------------------------------



Transaction ID: 1

Sender ID: 1

Receiver ID: 5

Amount: 2000.0

Type: SEND

Date: 2026-08-19 11:47:26.0

```



\---



\## 8. Add Expense



Users can record their personal expenses.



The user provides:



\* User ID

\* Category

\* Amount

\* Expense Date

\* Description



Example:



```text

\--- Add Expense ---



Enter User ID: 1

Enter Category: food

Enter Amount: 500

Enter Expense Date (YYYY-MM-DD): 2026-08-19

Enter Description: Lunch



Expense Added Successfully

```



\---



\## 9. View Expenses



Users can view their recorded expenses.



Example:



```text

\--- View Expenses ---



Enter User ID: 1



\------------------------------



Expense ID: 1

Category: food

Amount: 500.0

Date: 2026-08-19

Description: Lunch

```



\---



\## 10. Monthly Expense Report



Users can generate a monthly expense report based on:



\* User ID

\* Year

\* Month



The application uses SQL aggregation functions such as `SUM()` and `GROUP BY` to calculate category-wise expenses.



Example:



```text

\--- Monthly Expense Report ---



Enter User ID: 1

Enter Year: 2026

Enter Month (1-12): 8



Category-wise Expenses



\------------------------------



food : 500.0



\------------------------------



Total Expense : 500.0

```



\---



\# 🖥️ Application Menu



```text

====================================

&#x20;      DIGITAL WALLET APPLICATION

====================================



1\. Register User

2\. Login User

3\. Create Wallet

4\. Add Money

5\. Check Balance

6\. Send Money

7\. Transaction History

8\. Add Expense

9\. View Expenses

10\. Monthly Expense Report

11\. Exit



Enter your choice:

```



\---



\# 🛠️ Technologies Used



| Technology        | Purpose                    |

| ----------------- | -------------------------- |

| Java 17           | Application development    |

| JDBC              | Java-Database connectivity |

| MySQL 8           | Database management        |

| MySQL Connector/J | JDBC driver                |

| Eclipse           | Development IDE            |

| SQL               | Database operations        |

| Windows           | Operating System           |



\---



\# 📂 Project Structure



```text

DigitalWallet/

│

├── src/

│   └── com/codegnan/wallet/

│       │

│       ├── connection/

│       │   └── DBConnection.java

│       │

│       ├── user/

│       │   └── UserOperations.java

│       │

│       ├── wallet/

│       │   └── WalletOperations.java

│       │

│       ├── transaction/

│       │   └── TransactionOperations.java

│       │

│       ├── expense/

│       │   └── ExpenseOperations.java

│       │

│       └── main/

│           └── DigitalWalletApp.java

│

├── sql/

│   └── DigitalWallet.sql

│

├── README.md

│

└── .gitignore

```



\---



\# 🗄️ Database Design



The application uses a MySQL database named:



```text

DigitalWallet

```



The database contains the following main tables:



\* `users`

\* `wallet`

\* `transactions`

\* `expenses`



\---



\## 👤 Users Table



Stores registered user information.



| Column     | Description   |

| ---------- | ------------- |

| `user\_id`  | Primary key   |

| `name`     | User name     |

| `email`    | Unique email  |

| `password` | User password |



\---



\## 💳 Wallet Table



Stores wallet information and balance.



| Column      | Description                   |

| ----------- | ----------------------------- |

| `wallet\_id` | Primary key                   |

| `user\_id`   | Foreign key referencing users |

| `balance`   | Current wallet balance        |



\---



\## 💸 Transactions Table



Stores money transfer information.



| Column           | Description               |

| ---------------- | ------------------------- |

| `transaction\_id` | Primary key               |

| `sender\_id`      | Sender user ID            |

| `receiver\_id`    | Receiver user ID          |

| `amount`         | Transfer amount           |

| `type`           | Transaction type          |

| `date`           | Transaction date and time |



\---



\## 🧾 Expenses Table



Stores user expense information.



| Column         | Description                   |

| -------------- | ----------------------------- |

| `expense\_id`   | Primary key                   |

| `user\_id`      | Foreign key referencing users |

| `category`     | Expense category              |

| `amount`       | Expense amount                |

| `expense\_date` | Date of expense               |

| `description`  | Expense description           |



\---



\# 🔗 Database Relationship



```text

&#x20;                    ┌──────────────────┐

&#x20;                    │      users       │

&#x20;                    ├──────────────────┤

&#x20;                    │ user\_id PK       │

&#x20;                    │ name             │

&#x20;                    │ email            │

&#x20;                    │ password         │

&#x20;                    └────────┬─────────┘

&#x20;                             │

&#x20;                ┌────────────┴────────────┐

&#x20;                │                         │

&#x20;                ▼                         ▼

&#x20;       ┌──────────────────┐      ┌──────────────────┐

&#x20;       │      wallet      │      │     expenses     │

&#x20;       ├──────────────────┤      ├──────────────────┤

&#x20;       │ wallet\_id PK     │      │ expense\_id PK    │

&#x20;       │ user\_id FK       │      │ user\_id FK       │

&#x20;       │ balance          │      │ category         │

&#x20;       └──────────────────┘      │ amount           │

&#x20;                                 │ expense\_date     │

&#x20;                                 │ description      │

&#x20;                                 └──────────────────┘





&#x20;                    ┌──────────────────────┐

&#x20;                    │    transactions      │

&#x20;                    ├──────────────────────┤

&#x20;                    │ transaction\_id PK    │

&#x20;                    │ sender\_id            │

&#x20;                    │ receiver\_id          │

&#x20;                    │ amount               │

&#x20;                    │ type                 │

&#x20;                    │ date                 │

&#x20;                    └──────────────────────┘

```



\---



\# 🔄 Application Flow



```text

&#x20;                   START

&#x20;                     │

&#x20;                     ▼

&#x20;             Digital Wallet Menu

&#x20;                     │

&#x20;         ┌───────────┴───────────┐

&#x20;         │                       │

&#x20;         ▼                       ▼

&#x20;   Register User            Login User

&#x20;                                 │

&#x20;                                 ▼

&#x20;                          User Authentication

&#x20;                                 │

&#x20;                                 ▼

&#x20;                          Wallet Operations

&#x20;                                 │

&#x20;             ┌───────────────────┼───────────────────┐

&#x20;             │                   │                   │

&#x20;             ▼                   ▼                   ▼

&#x20;         Add Money          Check Balance        Send Money

&#x20;                                                     │

&#x20;                                                     ▼

&#x20;                                             Transaction History

&#x20;                                                     │

&#x20;                                                     ▼

&#x20;                                             Expense Management

&#x20;                                                     │

&#x20;                                       ┌─────────────┴─────────────┐

&#x20;                                       │                           │

&#x20;                                       ▼                           ▼

&#x20;                                 Add Expense                View Expenses

&#x20;                                                                   │

&#x20;                                                                   ▼

&#x20;                                                       Monthly Expense Report

&#x20;                                                                   │

&#x20;                                                                   ▼

&#x20;                                                                  EXIT

```



\---



\# 🔌 JDBC Connectivity



The project uses \*\*JDBC (Java Database Connectivity)\*\* to connect the Java application with MySQL.



The database connection is handled by:



```text

DBConnection.java

```



The application uses:



\* `Connection`

\* `PreparedStatement`

\* `ResultSet`

\* SQL queries

\* `commit()`

\* `rollback()`



\---



\# 🔐 PreparedStatement



The project uses `PreparedStatement` for executing SQL queries.



Example:



```java

PreparedStatement ps =

&#x20;   connection.prepareStatement(

&#x20;       "SELECT \* FROM users WHERE email = ? AND password = ?"

&#x20;   );

```



Values are supplied using:



```java

ps.setString(1, email);

ps.setString(2, password);

```



Using `PreparedStatement` helps prevent SQL injection and provides a safer way to execute parameterized SQL queries.



\---



\# 🔄 Transaction Management



Money transfer is an important operation where multiple database updates need to be handled together.



For example:



```text

Sender Balance

&#x20;     ↓

Deduct Amount

&#x20;     ↓

Receiver Balance

&#x20;     ↓

Add Amount

&#x20;     ↓

Create Transaction Record

```



The application uses:



```java

connection.setAutoCommit(false);

```



If all operations are successful:



```java

connection.commit();

```



If an error occurs:



```java

connection.rollback();

```



This ensures that the money transfer operation is handled as a single database transaction.



\---



\# 📊 SQL Concepts Demonstrated



This project demonstrates several SQL concepts:



\* `CREATE DATABASE`

\* `CREATE TABLE`

\* Primary Keys

\* Foreign Keys

\* `INSERT`

\* `SELECT`

\* `UPDATE`

\* `DELETE`

\* `WHERE`

\* `JOIN`

\* `GROUP BY`

\* `SUM()`

\* Aggregate Functions

\* Parameterized Queries

\* Transaction Management



\---



\# 🧮 Aggregation Query



The monthly expense report uses aggregation to calculate category-wise expenses.



Conceptually:



```sql

SELECT category, SUM(amount)

FROM expenses

WHERE user\_id = ?

GROUP BY category;

```



This allows the application to display the total amount spent in each category.



\---



\# ⚙️ How to Run the Project



\## Step 1: Install Java



Install \*\*Java 17\*\* and verify:



```bash

java -version

```



Expected version:



```text

17

```



\---



\## Step 2: Install MySQL



Install \*\*MySQL 8\*\* and verify that the MySQL server is running.



\---



\## Step 3: Create the Database



Open MySQL Workbench or MySQL Command Line.



Run the SQL script:



```text

sql/DigitalWallet.sql

```



The script creates the required database and tables.



\---



\## Step 4: Configure Database Connection



Open:



```text

src/com/codegnan/wallet/connection/DBConnection.java

```



Configure your MySQL connection details.



Example:



```java

private static final String URL =

&#x20;       "jdbc:mysql://localhost:3306/DigitalWallet";



private static final String USER =

&#x20;       "root";



private static final String PASSWORD =

&#x20;       "your\_password";

```



Replace `your\_password` with your local MySQL password.



\*\*Do not commit your actual database password to GitHub.\*\*



\---



\## Step 5: Add MySQL Connector/J



Add the MySQL Connector/J JAR file to the Eclipse project's build path.



Example:



```text

mysql-connector-j-8.x.x.jar

```



In Eclipse:



```text

Project

&#x20;  ↓

Build Path

&#x20;  ↓

Configure Build Path

&#x20;  ↓

Libraries

&#x20;  ↓

Add External JARs

```



Select the MySQL Connector/J JAR file.



\---



\## Step 6: Run the Application



Run:



```text

DigitalWalletApp.java

```



The console will display:



```text

====================================

&#x20;      DIGITAL WALLET APPLICATION

====================================



1\. Register User

2\. Login User

3\. Create Wallet

4\. Add Money

5\. Check Balance

6\. Send Money

7\. Transaction History

8\. Add Expense

9\. View Expenses

10\. Monthly Expense Report

11\. Exit



Enter your choice:

```



\---



\# 🧪 Sample Complete Execution



```text

====================================

&#x20;      DIGITAL WALLET APPLICATION

====================================



1\. Register User

2\. Login User

3\. Create Wallet

4\. Add Money

5\. Check Balance

6\. Send Money

7\. Transaction History

8\. Add Expense

9\. View Expenses

10\. Monthly Expense Report

11\. Exit



Enter your choice: 1





\--- User Registration ---



Enter Name: Avi

Enter Email: avi@gmail.com

Enter Password: 12345



User Registered Successfully





\--- User Login ---



Enter Email: avi@gmail.com

Enter Password: 12345



Login Successful

Welcome Avi





\--- Create Wallet ---



Enter User ID: 5



Wallet Created Successfully





\--- Add Money ---



Enter User ID: 5

Enter Amount: 15000



Money Added Successfully





\--- Check Balance ---



Enter User ID: 5



Current Balance: 15000.0





\--- Send Money ---



Enter Sender User ID: 1

Enter Receiver User ID: 5

Enter Amount: 2000



Money Sent Successfully





\--- Transaction History ---



Enter User ID: 1



\------------------------------



Transaction ID: 1

Sender ID: 1

Receiver ID: 5

Amount: 2000.0

Type: SEND

Date: 2026-08-19 11:47:26.0





\--- Add Expense ---



Enter User ID: 1

Enter Category: food

Enter Amount: 500

Enter Expense Date (YYYY-MM-DD): 2026-08-19

Enter Description: Lunch



Expense Added Successfully





\--- View Expenses ---



Enter User ID: 1



\------------------------------



Expense ID: 1

Category: food

Amount: 500.0

Date: 2026-08-19

Description: Lunch





\--- Monthly Expense Report ---



Enter User ID: 1

Enter Year: 2026

Enter Month (1-12): 8



Category-wise Expenses



\------------------------------



food : 500.0



\------------------------------



Total Expense : 500.0

```



\---



\# 📚 Java Concepts Used



This project demonstrates:



\* Classes and Objects

\* Packages

\* Methods

\* Encapsulation

\* Exception Handling

\* `Scanner`

\* `Connection`

\* `PreparedStatement`

\* `ResultSet`

\* Conditional Statements

\* Loops

\* Input Validation

\* JDBC

\* Database Transactions



\---



\# 🗃️ Database Concepts Used



The project demonstrates:



\* Relational Database

\* Primary Keys

\* Foreign Keys

\* One-to-One / One-to-Many relationships

\* CRUD Operations

\* SQL Joins

\* Aggregate Functions

\* `GROUP BY`

\* Database Transactions

\* Commit and Rollback

\* Referential Integrity



\---



\# 🛡️ Validation



The application performs validation for important operations such as:



\* Valid user ID

\* Valid email

\* Valid password

\* Positive amount

\* Sufficient wallet balance

\* Existing sender

\* Existing receiver

\* Existing wallet

\* Valid expense date

\* Valid month value



\---



\# 📈 Future Enhancements



The following features can be added in future versions:



\* 🔐 Password hashing using BCrypt

\* 🔑 OTP-based authentication

\* 💳 Debit/Credit card integration

\* 📱 GUI using Java Swing or JavaFX

\* 🌐 Web version using Spring Boot

\* ⚛️ React frontend

\* 🔒 Spring Security authentication

\* 📊 Dashboard with charts

\* 📧 Email notifications

\* 📱 Mobile application

\* 💰 Budget management

\* 📈 Advanced financial analytics

\* ☁️ Cloud database deployment



\---



\# ⚠️ Security Note



This project is created for \*\*learning and demonstration purposes\*\*.



The current console application stores passwords in a basic form for simplicity.



For a production application, passwords should never be stored as plain text.



A production version should use:



```text

Password

&#x20;  ↓

BCrypt Hashing

&#x20;  ↓

Database

```



Database credentials should also be stored securely using environment variables or configuration management rather than hardcoding them in source code.



\---



\# 👩‍💻 Author



\*\*Mekala Pavithra\*\*



Computer Science \& Engineering Graduate



\### Skills Demonstrated



\* Java

\* JDBC

\* MySQL

\* SQL

\* OOP

\* Database Management

\* Exception Handling

\* Transaction Management



\---



\# 📄 License



This project is created for educational and learning purposes.



