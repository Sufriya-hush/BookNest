# BookNest – Library Management System

A Java-based console application designed to simplify college library management.

## About the Project

BookNest is a menu-driven Library Management System that helps librarians manage books, register members, issue books, process returns, calculate late fines and search the book catalogue.

## Features

- Add new books with unique Book IDs.
- Register library members with unique Member IDs.
- Issue books to registered members.
- Return borrowed books.
- Restrict members to a maximum of 3 borrowed books.
- Automatically calculate late-return fines.
- Search books by ID or title keyword.
- Display books, members and library summaries.

## Technologies Used

- Java
- Object-Oriented Programming
- ArrayList
- Scanner
- Visual Studio Code

## Project Structure

```text
BookNest/
├── src/
│   ├── Book.java
│   ├── Member.java
│   ├── Library.java
│   └── LibraryApp.java
├── docs/
├── README.md
└── .gitignore
```

## Fine Calculation

The library charges Rs. 2 per day after the first 7 days.

Fine = (Days Kept - 7) × 2

If the book is returned within 7 days, no fine is charged.

## How to Run

1. Install the Java Development Kit (JDK).
2. Clone or download this repository.
3. Open the project folder in VS Code.
4. Open the terminal.
5. Compile the source files:

```bash
javac -d out src/*.java
```

6. Run the application:

```bash
java -cp out LibraryApp
```

## Project Constraints

- Console-based application.
- No database.
- No file handling.
- All data is stored in memory during execution.
- Uses Java's standard `java.util` and `java.lang` packages.

## Author

Sufriya Hussain R

## Project Type

College Academic Project
