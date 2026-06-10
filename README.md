Customer Management System
A robust console-based Java application designed for efficient customer record management.
This project follows a layered architecture to ensure clean code and maintainability.

Key Functionalities
The application provides a straightforward CLI menu to perform the following operations:
Customer Records: Add, list, search (by Name/Family), and update details.
Data Management: Delete records and view a history of deleted entries.
Persistence: Save current data to a JSON file and reload it upon request.

User Interface (Menu)
text
0. Exit
1. Add customer
2. Print all customers
3. Search Customers By Name
4. Search Customers By Family
5. Edit Customer By ID
6. Deleted Customer By ID
7. Print all deleted customers
8. Save data
9. Load data
    
Technical Overview
Language: Java21(LTS)
Build Tool: Apache Maven
Data Format: JSON(via[Jackson Databind]
(https://github.com/FasterXML/jackson)
for JSON processing)
Architecture: Layered approach
(Model, Service, and Data layers)

Getting Started
To run the project, ensure you have Java and Maven installed.
1. Build the project:
bash
mvn clean install

2. Run the application:
bash
mvn exec:java
