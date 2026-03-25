package src.com.airtribe.learntrack.ui;

import src.com.airtribe.learntrack.enums.StudentMenuOption;
import src.com.airtribe.learntrack.service.StudentService;
import java.util.Scanner;

public class StudentMenu {

    private final StudentService studentService;
    private final Scanner scanner;

    public StudentMenu(StudentService studentService, Scanner scanner) {
        this.studentService = studentService;
        this.scanner = scanner;
    }

    public void show() {
        boolean running = true;
        while (running) {
            System.out.println("\n===== Student Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. List All Students");
            System.out.println("3. Get Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            StudentMenuOption option = StudentMenuOption.fromValue(choice);

            switch (option) {
                case ADD -> handleAddStudent();
                case LIST -> handleListStudents();
                case GET_BY_ID -> handleGetStudentById();
                case UPDATE -> handleUpdateStudent();
                case DELETE -> handleDeleteStudent();
                case BACK -> running = false;
                case null -> System.out.println("Invalid option. Try again.");

            }
        }
    }
}