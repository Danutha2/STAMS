# Student Management System

This is a Student Management System that allows users to register students, store and load student details, manage student records, and generate reports. The system supports the following features:

## Features

- **Check available seats**: View the available seats for new student registrations.
- **Register student**: Register a student by providing their ID and name.
- **Delete student**: Delete a student's record by their ID.
- **Find student**: Search for a student by their ID and view their details.
- **Store student details**: Save the student records to a text file.
- **Load student details**: Load student records from a text file.
- **View student names**: View all registered student names sorted alphabetically.
- **Sub menu**:
  - Add name and module marks to a student.
  - Generate a summary of the system.
  - Generate a complete report of student marks, averages, and grades.

## Prerequisites

- **Java 8 or later**: Make sure you have Java installed to run the program.

## How to Run the Program

1. Clone the repository or download the files.
2. Open a terminal or command prompt.
3. Navigate to the directory where the `Main.java`, `Module.java`, and `Student.java` files are located.
4. Compile the Java files using the following command:
5. Run the program with the command:


## Menu Overview

After running the program, the following options will be presented in the menu:

1. **Check available seats**: Shows the number of students registered and the available seats for new registrations.
2. **Register student**: Registers a new student by entering an ID (must follow the format `w1234567`) and name.
3. **Delete student**: Allows you to delete a student from the system by entering their student ID.
4. **Find student**: Search for a student by their ID and display their personal and academic details.
5. **Store student details**: Save the registered student details into a text file (`StudentManagementSystem.txt`).
6. **Load student details**: Load student data from the text file and populate the system with the stored records.
7. **View student names**: View all registered student names sorted alphabetically.
8. **Sub menu**: 
- **Add name and module marks**: Add or update the student's name and module marks.
- **Generate a summary of the system**: View a summary of the registered students and their performance.
- **Generate complete report**: Generate a detailed report of student marks, total, average, and grade.
9. **Exit the program**: Exits the program.

## How to Use the Program

### 1. Registering a Student
- To register a student, select option 2 from the main menu.
- Enter a student ID that follows the format `w1234567` (8 characters, starting with 'w').
- If the ID is valid and not already registered, the student will be added to the system.

### 2. Deleting a Student
- Select option 3 from the menu.
- Enter the student's ID to delete their record from the system.

### 3. Viewing Student Records
- Select option 7 from the menu to view a list of registered student names, sorted alphabetically.

### 4. Storing and Loading Student Details
- To store student details in a file, select option 5 from the menu.
- To load student details from a file, select option 6 from the menu.

### 5. Generating Reports
- To generate a summary of the system, select option 8b from the sub-menu.
- To generate a complete report, select option 8c from the sub-menu.

## File Format for Storing and Loading Student Details

The student details are stored in a text file `StudentManagementSystem.txt` in the following format:


## Student and Module Classes

- **Student Class**: Represents a student with an ID, name, and their associated marks. It extends the `Module` class to include academic performance details.
- **Module Class**: Handles the module marks and calculates the total, average, and grade for each student.

## License

This project is open-source and available under the MIT License.


