🚀 Java CRM System (Console Application + MySQL)

A console-based CRM (Customer Relationship Management) system developed in Java, using JDBC for database integration with MySQL.

This project was created as an educational portfolio project, focusing on backend development, database design, and clean, structured Java code.

👨‍💻 About the Project

The goal of this project is to demonstrate practical knowledge of:

Java programming and object-oriented principles

JDBC and relational database integration

SQL-based CRUD operations

Basic system architecture and configuration management

⚠️ This is a study project only. All data used is fictional and does not represent real personal information.

✨ Key Features

✔ Automatic database creation
✔ Automatic table creation (usuarios)
✔ Full CRUD operations (Create, Read, Update, Delete)
✔ Search users by CPF
✔ Unique validation for CPF and email at database level
✔ Clean console-based user interface
✔ Externalized database configuration (config.properties)

🧠 Technical Highlights (What Recruiters Will Notice)

Use of PreparedStatement to prevent SQL injection

Proper use of try-with-resources for database connections

Separation of configuration logic (DatabaseConfig)

Relational database constraints (PRIMARY KEY, UNIQUE)

Clear and readable console output

Scalable structure for future improvements (services, DAO, validation layers)

🛠️ Technologies & Tools

Java (JDK 17+ recommended)

JDBC (MySQL Connector/J)

MySQL

IntelliJ IDEA / VS Code

Git & GitHub

📂 Project Structure
CRM/
├── src/
│   ├── Main.java
│   └── DatabaseConfig.java
├── lib/
│   └── mysql-connector-j-x.x.x.jar
├── config.properties
├── .gitignore
└── README.md

⚙️ Database Configuration
1️⃣ Create the config.properties file
db.urlBase=jdbc:mysql://localhost:3306/
db.database=sistema_java
db.user=root
db.password=your_password_here

2️⃣ Configuration Handling

The DatabaseConfig class is responsible for reading the configuration file and loading database credentials dynamically, allowing easy environment changes without code modification.

🔌 Required Dependency

Download the official MySQL JDBC driver:

🔗 https://dev.mysql.com/downloads/connector/j/

Add the .jar file to the lib/ directory and configure it as a project dependency.

▶️ How to Run

Ensure MySQL is running

Configure config.properties

Run the Main class

Console menu:

========== JAVA SYSTEM (CRM) ==========
1 - Register new user
2 - List users
3 - Update user
4 - Delete user
5 - Search user by CPF
6 - Exit

🧪 Database Schema Example
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE
);

🔮 Possible Improvements (Future Enhancements)

Input validation layer (CPF and email format)

DAO pattern implementation

Logging system

Authentication and user roles

REST API version (Spring Boot)

Frontend integration

📄 License

This project is licensed under the MIT License.

⭐ Final Note

This project reflects my commitment to learning backend development, Java, and database-driven systems, following industry practices and clean code principles.
