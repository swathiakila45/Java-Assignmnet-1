package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.StudentMenuOption;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.StudentService;

import java.util.List;
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
                case ADD       -> handleAddStudent();
                case LIST      -> handleListStudents();
                case GET_BY_ID -> handleGetStudentById();
                case UPDATE    -> handleUpdateStudent();
                case DELETE    -> handleDeleteStudent();
                case BACK      -> running = false;
                case null      -> System.out.println("❌ Invalid option. Try again.");
            }
        }
    }

    private void handleAddStudent() {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter batch: ");
        String batch = scanner.nextLine();

        try {
            Student student = studentService.addStudent(firstName, lastName, email, batch);
            System.out.println("Student added successfully! ID: " + student.getId());
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void handleListStudents() {
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student s : students) {
            System.out.println("ID: " + s.getId() + " | " + s.getDisplayName() + " | " + s.getEmail() + " | Batch: " + s.getBatch());
        }
    }

    private void handleGetStudentById() {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            Student student = studentService.getStudentById(id);
            System.out.println("Found: " + student.getDisplayName() + " | " + student.getEmail());
        } catch (EntityNotFoundException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void handleUpdateStudent() {
        System.out.print("Enter student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter batch: ");
        String batch = scanner.nextLine();
        System.out.print("Is active? (true/false): ");
        boolean active = scanner.nextBoolean();
        scanner.nextLine();

        try {
            Student student = studentService.updateStudent(id, firstName, lastName, email, batch, active);
            System.out.println("Student updated: " + student.getDisplayName());
        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void handleDeleteStudent() {
        System.out.print("Enter student ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            studentService.deleteStudent(id);
            System.out.println("Student deleted successfully.");
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}