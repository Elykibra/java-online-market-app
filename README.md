# Java Command-Line E-Commerce Application 🛒

This project is a console-based e-commerce simulation built entirely in Java. It demonstrates a complete user journey from authentication to order processing, showcasing strong Object-Oriented Programming (OOP) principles and core Java concepts.

## ✨ Core Features

-   **User Authentication:** A complete system for user registration and login.
-   **Product Catalog:** Users can browse items sorted into different categories.
-   **Shopping Cart:** A fully functional cart where users can add items and specify quantities.
-   **Voucher System:** Users can apply discount codes at checkout for a percentage off their total.
-   **File I/O for Receipts:** Generates a detailed `.txt` receipt for every transaction and saves it to a `receipts` folder, ensuring data persistence for order history.

---
## 🛠️ Key Learnings & Concepts Demonstrated

This project was an exercise in applying core software development principles:

-   **Object-Oriented Programming (OOP):** The application is built on core OOP principles, with distinct classes for `User`, `MarketItem`, `ShoppingCart`, and `Voucher`. Inheritance is used to create specific item types (e.g., `Appliance`, `Fruit`) from a base `MarketItem` class.
-   **Code Refactoring & Project Structure:** The initial prototype was refactored from a single-file script into a modular, multi-file structure within a `src` directory. This professional layout enhances code readability and maintainability.
-   **Data Structures:** The project heavily utilizes `java.util.List` and `java.util.ArrayList` to manage collections of users, items, and vouchers in memory.
-   **File I/O:** Employs `java.io.FileWriter` to create and write transaction details to external text files, demonstrating data persistence.
-   **Version Control (Git):** The project history showcases a professional workflow, including feature implementation, bug fixing (resolving compilation errors and warnings), and major refactoring, all documented with clear, conventional commit messages.

---
## 🚀 How to Run the Application

To run this application, you will need the Java Development Kit (JDK) installed.

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/YourUsername/java-online-market-app.git](https://github.com/YourUsername/java-online-market-app.git)
    cd java-online-market-app
    ```
    *(Replace `YourUsername` with your actual GitHub username)*

2.  **Compile the Java files:**
    From the main project directory, run the following command to compile all source files from the `src` folder.
    ```bash
    javac src/*.java
    ```

3.  **Run the main application:**
    Use the following command, which specifies the `src` folder as the classpath.
    ```bash
    java -cp src OnlineMarket
    ```
4.  Follow the on-screen prompts to register, log in, and shop!
