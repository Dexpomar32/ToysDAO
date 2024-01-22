# ToysDAO

ToysDAO is a Java project that provides a simple and efficient way to manage toy operations using JDBC driver and MySQL database connection. It follows the CRUD (Create, Read, Update, Delete) operations and incorporates other essential functions. The project employs a singleton pattern through a connection creator class to maintain a single connection to the MySQL database.

## Features

- **CRUD Operations**: Perform basic Create, Read, Update, and Delete operations on toy records.
- **Singleton Pattern**: Utilizes a connection creator class to ensure a single and efficient connection to the MySQL database.

## Requirements

- Java Development Kit (JDK)
- MySQL Database
- JDBC Driver for MySQL

## Installation

1. Clone the repository:
   
    ```bash
    git clone https://github.com/Dexpomar32/ToysDAO.git
    ```

2. Compile the project:

   ```bash
    javac -cp "path/to/mysql-connector.jar" src/*.java -d out/
    ```

3. Run the application:

   ```bash
   java -cp "path/to/mysql-connector.jar;out/" Main
   ```

## Contributing

Contributions are welcome! Feel free to open issues or submit pull requests to improve MailBridge.
