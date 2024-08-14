import java.io.*;
import java.util.*;


public class Main {

    //Create a variable to count the registered student
    static int studentCount;
    //Maximum count
    static int totalSeat = 100;
    //create a variable for store available seats
    static int availableSeat;

    //Create an array for store students name and ID
    static String[][] studentDetails = new String[100][2];
    //Create an array for store students Module Marks
    static double[][] moduleMarks = new double[100][3];

    //Create a variables for identifier() method use
    static int outerLoopIndex;
    static boolean studentFound = false;


    public static void main(String[] args) {
        //USer choice
        int choice = 0;
        Scanner input = new Scanner(System.in);
        boolean foundError = false;


        do {
            try {
                //Maine menu of the Program
                System.out.println();
                System.out.println("      Menu    ");
                System.out.println("1. Check available seats.");
                System.out.println("2. Register student (with ID).");
                System.out.println("3. Delete student (with student ID).");
                System.out.println("4. Find student (with student ID).");
                System.out.println("5. Store student details into a file.");
                System.out.println("6. Load student details from the file to the system.");
                System.out.println("7. View Student names.");
                System.out.println("8. Sub menu ");
                System.out.println("0. Exit the program");

                System.out.println();


                //get user input for operation
                System.out.println("Enter your choice: ");
                choice = input.nextInt();
                System.out.println();


                //Check user's choice and implement a method
                switch (choice) {
                    case 1:
                        checkAvailableSeats();
                        //Display a message to user
                        System.out.println("You are in student Seat checking option!");
                        //Registered Student
                        System.out.println("Total number of students registered : " + studentCount);
                        //Available seats
                        System.out.println(availableSeat + " Number of seats available for registration.");
                        System.out.println();
                        break;
                    case 2:
                        registerStudent(input);
                        System.out.println();
                        break;
                    case 3:
                        //prompt index
                        deleteStudents(input);
                        System.out.println();
                        break;
                    case 4:
                        findStudents(input);
                        System.out.println();
                        break;
                    case 5:
                        storeStudentDetails();
                        System.out.println();
                        break;
                    case 6:
                        loadStudentDetails();
                        System.out.println();
                        break;
                    case 7:
                        viewStudents();
                        System.out.println();
                        break;
                    case 8:
                        subMenu(input);

                        break;

                    case 0:
                        System.out.println("Exiting!!");
                        break;
                    default:
                        System.out.println("Incorrect choice");
                }


            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number from the menu.");
                input.nextLine();
                foundError = true;
            }
        }
        while (choice != 0 || foundError);

    }


    //Method for Check available seats
    public static void checkAvailableSeats() {

        //When call the checkAvailableSeats() method studentCount always assign 0.
        studentCount = 0;


        for (int i = 0; i < studentDetails.length; i++) {

            if (studentDetails[i][0] != null) {

                studentCount++;
            } else {
                break;
            }
        }
        //Calculate the available seats.
        availableSeat = totalSeat - studentCount;

    }

    //Method For register students
    public static void registerStudent(Scanner input) {
        studentFound = false;
        boolean correctFormat;
        //Display a message to user
        System.out.println("You are in student registration option!" + "\n");

        //Check available seat
        checkAvailableSeats();
        if (availableSeat <= 0) {
            System.out.println("Sorry no seats are available!");
        }

        //Continue registering if any seats are available.
        else {

            //Create a variable for id
            String id;
            input.nextLine();
            System.out.println("Enter your ID number (must be 8 characters e.g. w1234567): ");

            //check the validation of ID
            do {
                id = input.nextLine();
                if (id.length() != 8 || id.charAt(0) != 'w') {
                    System.out.println("Id must be 8 characters e.g. w1234567):");
                    correctFormat = true;
                } else {
                    correctFormat = false;
                }
            }
            while (correctFormat);


            //Check entered ID is already in the system
            identifier(id);
            if (studentFound) {
                System.out.println(id + " is already in the system!");
            }

            //If id is not already registered
            else {

                //Store student ID in array
                studentDetails[studentCount][0] = id;
                System.out.println();

                //Display Registered ID
                System.out.println("Registered ID is " + id);
                //Successful message
                System.out.println("A student registration has been successfully completed! "+"\n");

            }
        }
    }


    //Method for delete student details
    public static void deleteStudents(Scanner input) {
        checkAvailableSeats();

        if (studentCount <= 0) {
            System.out.println("No students to delete!");
        } else {
            studentFound = false;
            // Display a message to the user
            System.out.println("You are in the student delete option!");

            input.nextLine();
            // Prompt for ID
            System.out.println("Enter an ID: ");
            String id = input.nextLine();

            // Call identifier method to identify the student
            identifier(id);

            if (studentFound) {
                // Delete details from student details array
                for (int i = 0; i < studentDetails[outerLoopIndex].length; i++) {
                    studentDetails[outerLoopIndex][i] = null;
                }

                // Delete Module marks
                for (int i = 0; i < moduleMarks[outerLoopIndex].length; i++) {
                    moduleMarks[outerLoopIndex][i] = 0;
                }

                // Shift up data in every array
                for (int i = outerLoopIndex; i < studentCount - 1; i++) {
                    for (int j = 0; j < studentDetails[i].length; j++) {
                        studentDetails[i][j] = studentDetails[i + 1][j];
                    }
                    for (int l = 0; l < moduleMarks[i].length; l++) {
                        moduleMarks[i][l] = moduleMarks[i + 1][l];
                    }
                }

                // Clear the last student position after shifting
                for (int i = 0; i < studentDetails[studentCount - 1].length; i++) {
                    studentDetails[studentCount - 1][i] = null;
                }
                for (int i = 0; i < moduleMarks[studentCount - 1].length; i++) {
                    moduleMarks[studentCount - 1][i] = 0;
                }

                // Decrement student count
                studentCount--;

                // Display successful message
                System.out.println("Student details removed successfully!");
            } else {
                // If id not found in registered details
                System.out.println("Cannot find a student for this ID, check your ID");
            }
        }
    }


    //Method for Search student
    public static void findStudents(Scanner input) {
        studentFound = false;
        System.out.println("You are in Find student option!");

        checkAvailableSeats();

        //first check system is empty or not
        if (studentCount <= 0) {
            System.out.println("No students in the system!");
        }

        else {

            //Prompt ID
            input.nextLine();
            System.out.println("Enter an ID : ");
            String id = input.nextLine();

            //Accessing  student details for given ID.
            identifier(id);

            //If there is a student for given id.
            if (studentFound) {

                //Display Student personal details
                for (int j = 0; j < studentDetails[outerLoopIndex].length; j++) {
                    if (j == 0) {
                        System.out.println("Student ID : " + studentDetails[outerLoopIndex][j]);
                    } else {
                        System.out.println("Student Name : " + studentDetails[outerLoopIndex][j]);
                    }
                }

                //Display module marks
                double m1 = 0;
                double m2 = 0;
                double m3 = 0;

                for (int i = 0; i < moduleMarks[outerLoopIndex].length; i++) {
                    System.out.println("Your Module " + (i + 1) + " mark is " + moduleMarks[outerLoopIndex][i]);
                    if (i == 0) {
                        m1 = moduleMarks[outerLoopIndex][i];
                    } else if (i == 1) {
                        m2 = moduleMarks[outerLoopIndex][i];
                    } else {
                        m3 = moduleMarks[outerLoopIndex][i];
                    }
                }

                Student student = new Student();
                student.setMarks(m1, m2, m3);

                System.out.println("Total marks : " + student.calculateTotal());

                System.out.println("Average : " + student.calculateAverage());
                System.out.println("Module Grade : " + student.calculateModuleGrade());

            } else {
                System.out.println("ID not found!");
            }
        }
    }


    //Create a method to Store Student details
    public static void storeStudentDetails() {
        System.out.println("You are in Store student option" + "\n");

        checkAvailableSeats();

        //first check system is empty or not
        if (studentCount <= 0) {
            System.out.println("No students in the system! ");
        } else {

            int copiedStudents = 0;

            //Create New file and Store data to the file

            try (FileWriter fw = new FileWriter("StudentManagementSystem.txt");
                 BufferedWriter writer = new BufferedWriter(fw)) {


                for (int i = 0; i < studentCount; i++) {
                    Student student = new Student();

                    //Set name and ID
                    for (int j = 0; j < studentDetails[i].length; j++) {
                        if (j == 0) {
                            student.setId(studentDetails[i][j]);

                        }
                        if (j == 1) {
                            student.setName(studentDetails[i][j]);
                        }
                    }
                    //Set marks
                    double mrk1 = 0;
                    double mrk2 = 0;
                    double mrk3 = 0;
                    for (int k = 0; k < moduleMarks[i].length; k++) {
                        if (k == 0) {
                            mrk1 = moduleMarks[i][k];
                        } else if (k == 1) {
                            mrk2 = moduleMarks[i][k];
                        } else {
                            mrk3 = moduleMarks[i][k];
                        }

                    }
                    student.setMarks(mrk1, mrk2, mrk3);


                    //Write data to the file
                    writer.write("Student : " + (i + 1) + "\n");

                    writer.write("ID : " + student.getId() + "\n");

                    writer.write("Name : " + student.getName() + "\n");

                    writer.write("Module Mark 1 : " + student.getMarks()[0] + "\n");

                    writer.write("Module Mark 2 : " + student.getMarks()[1] + "\n");

                    writer.write("Module Mark 3 : " + student.getMarks()[2] + "\n");

                    writer.write("Total : " + student.calculateTotal() + "\n");

                    writer.write("Average: " + student.calculateAverage() + "\n");

                    writer.write("Module Grade: " + student.calculateModuleGrade() + "\n");
                    writer.newLine();
                    copiedStudents++;
                }
                System.out.println("Student  are stored successfully! ");
                System.out.println(copiedStudents + " students are stored");

            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }

        }
    }


    public static void loadStudentDetails() {
        System.out.println("You are in student loading option! ");

        try (FileReader file = new FileReader("StudentManagementSystem.txt");
             BufferedReader reader = new BufferedReader(file)) {

            //Create variables
            String line;
            int studentIndex = -1;
            int loadedStudentCount = 0;

            //Loop will iterate until find empty line
            while ((line = reader.readLine()) != null) {

                //Call check checkAvailableSeats method to count the available seat the system
                checkAvailableSeats();

                // Identify student start lines
                if (line.startsWith("Student : ")) {

                    studentIndex++;
                    studentCount = studentIndex;
                    // Increment index for new student


                    // Check if student count exceeds limit
                    if ((studentCount + 1) > 100) {
                        System.out.println("System can only store " + totalSeat + " students. Skipping remaining entries." + "\n");

                        break;
                        // Exit loop if limit reached
                    }
                } else if (line.contains(": ")) {

                    //Divide two parts from colon
                    String[] parts = line.split(": ");

                    // Extract data after colon, remove leading/trailing spaces
                    String key = parts[0].trim();
                    String value = parts[1].trim();

                    // Assuming arrays are initialized correctly
                    if (studentIndex < 100) {
                        // Store ID in Student detail array
                        if (key.equals("ID")) {
                            studentDetails[studentIndex][0] = value;
                            //increment the loadedStudentCount
                            loadedStudentCount++;

                            // Store name in Student detail array
                        } else if (key.equals("Name")) {
                            studentDetails[studentIndex][1] = value;

                            // Store Module mark1 in Student detail array
                        } else if (key.equals("Module Mark 1")) {
                            moduleMarks[studentIndex][0] = Double.parseDouble(value);

                            // Store Module mark2 in Student detail array
                        } else if (key.equals("Module Mark 2")) {
                            moduleMarks[studentIndex][1] = Double.parseDouble(value);

                            // Store Module mark3 in Student detail array
                        } else if (key.equals("Module Mark 3")) {
                            moduleMarks[studentIndex][2] = Double.parseDouble(value);
                        }

                    }

                }
            }


            if (loadedStudentCount > 0) {
                // Update actual student count
                System.out.println("*" + loadedStudentCount + " students loaded successfully!");
            } else {

                System.out.println("No students in the File!");
            }


        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }


    //Create a method for View the Student details
    public static void viewStudents() {

        System.out.println("You are in view student option!");

        //call checkAvailableSeats method to count registered students.
        checkAvailableSeats();

        //Display message if no students in the system
        if (studentCount <= 0) {
            System.out.println("No students in the system!");
        } else {

            //Temporary array to store student name
            String[] tempName = new String[studentCount];

            //Store all names from Student details to temporary array.
            for (int i = 0; i < studentCount; i++) {
                for (int j = 0; j < studentDetails[i].length; j++) {
                    if (j == 1) {
                        tempName[i] = studentDetails[i][j];
                    }


                }
            }

            //Sorting algorithm
            for (int k = 0; k < (tempName.length - 1); k++) {
                for (int n = k + 1; n < tempName.length; n++) {
                    if (tempName[k].compareTo(tempName[n]) > 0) {
                        String temp = tempName[k];
                        tempName[k] = tempName[n];
                        tempName[n] = temp;
                    }
                }
            }

            //Displaying sorted array
            for (int i = 0; i < tempName.length; i++) {

                System.out.println((i + 1) + ". " + tempName[i]);

            }

        }

    }

    /*Create a method to store module marks and student name in the applicable arrays.
    */
    public static void extraControls(Scanner input) {
        System.out.println("You are in add name and module mark option!");

        studentFound = false;
        checkAvailableSeats();


        if (studentCount <= 0) {
            System.out.println("No IDS in the system! ");

        }
        else {

            //Prompt ID

            System.out.println("Enter your Id: ");
            String tempid = input.nextLine();

            //Check are there any student in system.
            identifier(tempid);


            if (studentFound) {

                //prompt name
                System.out.println("Enter your name :");
                String name = input.nextLine();
                String capitalizedName = name.substring(0, 1).toUpperCase() + name.substring(1);
                studentDetails[outerLoopIndex][1] = capitalizedName;


                //prompt module mark

                for (int i = 0; i < moduleMarks[i].length; i++) {
                    double mark = 0;
                    boolean enteredCorrectValue = false;
                    do {
                        try {
                            System.out.println("Enter you module mark " + (i + 1));
                            mark = input.nextDouble();
                            enteredCorrectValue = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Enter a valid mark!");
                            input.nextLine();
                        }
                    }
                    while (!enteredCorrectValue || mark > 100 ||  mark < 0);


                    moduleMarks[outerLoopIndex][i] = mark;

                }

                System.out.println("Names and module marks added successfully!");

            } else {
                System.out.println("Can't find a student with this ID number, Check your ID or register first.");
            }
        }

    }

    //create a method to Generate a report
    public static void summary() {

        System.out.println("Summary of the System:");


        int totalStudents = studentCount;


        if (studentCount <= 0) {
            System.out.println("No Students are registered yet!");
        } else {
            int module1Pass = 0, module2Pass = 0, module3Pass = 0;

            for (int i = 0; i < studentCount; i++) {
                if (moduleMarks[i][0] > 40) {
                    module1Pass++;
                }

                if (moduleMarks[i][1] > 40) {
                    module2Pass++;
                }
                if (moduleMarks[i][2] > 40) {
                    module3Pass++;
                }
            }

            //Display module records
            System.out.println("Total student registrations: " + totalStudents);
            System.out.println("Students scoring more than 40 marks in Module 1: " + module1Pass);
            System.out.println("Students scoring more than 40 marks in Module 2: " + module2Pass);
            System.out.println("Students scoring more than 40 marks in Module 3: " + module3Pass);
        }


    }
    //Create a method to get a complete report
    public static void report() {

        checkAvailableSeats();

        System.out.println("System Report ");
        //Check System is empty or not
        if (studentCount == 0) {

            System.out.println("System empty!");
        } else {

            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s\n", "Student ID", "Student Name", "Module 1", "Module 2", "Module 3", "Total", "Average", "Grade");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");

            Student[] students = new Student[studentCount];

            for (int i = 0; i < studentCount; i++) {
                Student student = new Student();
                for (int j = 0; j < studentDetails[i].length; j++) {
                    if (studentDetails[i][0] != null) {
                        student.setId(studentDetails[i][0]);
                        if (studentDetails[i][1] != null) {
                            student.setName(studentDetails[i][1]);
                        } else {
                            student.setName("Name not entered");
                            break;
                        }
                    }
                }

                double marks1 = 0;
                double marks2 = 0;
                double marks3 = 0;
                for (int k = 0; k < moduleMarks[i].length; k++) {
                    if (k == 0) {
                        marks1 = moduleMarks[i][k];
                    }
                    if (k == 1) {
                        marks2 = moduleMarks[i][k];
                    }
                    if (k == 2) {
                        marks3 = moduleMarks[i][k];
                    }
                }
                student.setMarks(marks1, marks2, marks3);

                students[i] = student;
            }

            // Bubble Sort based on average marks
            for (int i = 0; i < students.length - 1; i++) {
                for (int j = 0; j < students.length - i - 1; j++) {
                    if (students[j].calculateAverage() < students[j + 1].calculateAverage()) {
                        // Swap students[j] and students[j + 1]
                        Student temp = students[j];
                        students[j] = students[j + 1];
                        students[j + 1] = temp;
                    }
                }
            }

            for (Student student : students) {
                System.out.printf("%-20s %-20s %-20.2f %-20.2f %-20.2f %-20.2f %-20.2f %-20s\n",
                        student.getId(), student.getName(), student.getMarks()[0], student.getMarks()[1], student.getMarks()[2],
                        student.calculateTotal(), student.calculateAverage(), student.calculateModuleGrade());
            }
        }

    }


    /*This method take an argument which is id, it can find the owner of the id and return their index of applicable student, and set studentFound variable  to True.
        */
    public static void identifier(String id) {
        for (int i = 0; i < studentDetails.length; i++) {
            for (int j = 0; j < studentDetails[i].length; j++) {

                if (Objects.equals(studentDetails[i][j], id)) {
                    outerLoopIndex = i;

                    studentFound = true;
                }
            }
        }
    }
    /* This method is used for control submenu */
    public static void subMenu(Scanner input) {

        while (true) {
            System.out.println("Sub menu :-");
            System.out.println("a. Add name and module marks");
            System.out.println("b. Generate a summary of the system");
            System.out.println("c. Generate complete report");
            System.out.println("d. Back to the main menu");
            System.out.println("Enter you choice : ");
            String choice = input.next();
            System.out.println(" ");

            switch (choice) {
                case "a":
                    input.nextLine();
                    extraControls(input);
                    System.out.println(" ");


                    break;

                case "b":
                    summary();
                    System.out.println(" ");

                    break;

                case "c":
                    report();
                    System.out.println(" ");

                    break;

                case "d":
                    return;

                default:
                    System.out.println("Invalid choice!!");

            }


        }
    }
}










