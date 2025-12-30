# Car Gallery Management System 

##  Project Description
This project is a desktop automation application developed to facilitate vehicle tracking and sales processes for car galleries. Built with **Java Swing** for the interface and **MySQL** for the database, it utilizes the **DAO (Data Access Object)** design pattern for efficient data management.

 **Note:** For detailed architecture diagrams, flowcharts, and project details, please refer to the **`Presentation.pptx`** file included in this repository.

##  Key Features
* **User Authentication:** Secure admin login and registration system.
* **Inventory Management:**
    * Add new cars with details (Brand, Model, KM, Price, Fault Status).
    * Upload and view car images.
    * Delete vehicles from the inventory.
* **Sales System:**
    * Sell cars to customers with a dedicated interface.
    * Automatically move sold cars to the "Sales History" table.
    * Store customer contact information (Name, Email, Phone).
* **Search:** Filter cars dynamically by brand or model.

##  Tech Stack
* **Programming Language:** Java (Swing)
* **Database:** MySQL
* **IDE:** Eclipse
* **Dependencies:** MySQL Connector/J (JDBC)

##  Installation Guide

### 1. Database Setup
The project requires a MySQL database to function.
1.  Open **MySQL Workbench** or any SQL editor.
2.  Run the `database_setup.sql` script provided in this repository.
3.  This will create the `car_gallery` database and the necessary tables (`users`, `cars`, `sold_cars`).

### 2. Project Configuration
1.  Clone or download this repository.
2.  Import the project into **Eclipse**.
3.  Add the included **`mysql-connector-j-9.3.0.jar`** file to your project's **Build Path**.

### 3. Connection Settings
Open `src/pack1/DBConnection.java` and ensure the credentials match your local setup:
```java
private static final String URL = "jdbc:mysql://localhost:3306/car_gallery";
private static final String USER = "root";
private static final String PASSWORD = ""; // Leave empty if you don't have a password
```
### 4. Running the Application
1.  Navigate to src/pack1/AppMain.java.
2.  Right-click and select Run As > Java Application.
3.  Register a new account via the "Sign Up" button to get started.
