package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.enums.EnrollmentMenuOption;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.EnrollmentService;

import java.util.List;
import java.util.Scanner;

public class EnrollmentMenu {

    private final EnrollmentService enrollmentService;
    private final Scanner scanner;

    public EnrollmentMenu(EnrollmentService enrollmentService, Scanner scanner) {
        this.enrollmentService = enrollmentService;
        this.scanner = scanner;
    }

    public void show() {
        boolean running = true;

        while (running) {
            System.out.println("\n===== Enrollment Menu =====");
            System.out.println("1. Enroll Student in Course");
            System.out.println("2. List All Enrollments");
            System.out.println("3. Get Enrollment by ID");
            System.out.println("4. Get Enrollments by Student ID");
            System.out.println("5. Get Enrollments by Course ID");
            System.out.println("6. Update Enrollment Status");
            System.out.println("7. Delete Enrollment");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            EnrollmentMenuOption option = EnrollmentMenuOption.fromValue(choice);

            switch (option) {
                case ENROLL          -> handleEnrollStudent();
                case LIST_ALL        -> handleListAllEnrollments();
                case GET_BY_ID       -> handleGetEnrollmentById();
                case GET_BY_STUDENT  -> handleGetByStudentId();
                case GET_BY_COURSE   -> handleGetByCourseId();
                case UPDATE_STATUS   -> handleUpdateStatus();
                case DELETE          -> handleDeleteEnrollment();
                case BACK            -> running = false;
                case null            -> System.out.println("❌ Invalid option. Try again.");
            }
        }
    }

    private void handleEnrollStudent() {
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        System.out.print("Enter course ID: ");
        int courseId = scanner.nextInt();
        scanner.nextLine();

        try {
            Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);
            System.out.println("✅ Enrolled successfully! Enrollment ID: " + enrollment.getId());
        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void handleListAllEnrollments() {
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found.");
            return;
        }
        for (Enrollment e : enrollments) {
            System.out.println("ID: " + e.getId() +
                    " | Student ID: " + e.getStudentId() +
                    " | Course ID: " + e.getCourseId() +
                    " | Status: " + e.getStatus() +
                    " | Date: " + e.getEnrollmentDate());
        }
    }

    private void handleGetEnrollmentById() {
        System.out.print("Enter enrollment ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            Enrollment e = enrollmentService.getEnrollmentById(id);
            System.out.println("Found — Student ID: " + e.getStudentId() +
                    " | Course ID: " + e.getCourseId() +
                    " | Status: " + e.getStatus());
        } catch (EntityNotFoundException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void handleGetByStudentId() {
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        try {
            List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudentId(studentId);
            if (enrollments.isEmpty()) {
                System.out.println("No enrollments found for this student.");
                return;
            }
            for (Enrollment e : enrollments) {
                System.out.println("Enrollment ID: " + e.getId() +
                        " | Course ID: " + e.getCourseId() +
                        " | Status: " + e.getStatus());
            }
        } catch (EntityNotFoundException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void handleGetByCourseId() {
        System.out.print("Enter course ID: ");
        int courseId = scanner.nextInt();
        scanner.nextLine();

        try {
            List<Enrollment> enrollments = enrollmentService.getEnrollmentsByCourseId(courseId);
            if (enrollments.isEmpty()) {
                System.out.println("No enrollments found for this course.");
                return;
            }
            for (Enrollment e : enrollments) {
                System.out.println("Enrollment ID: " + e.getId() +
                        " | Student ID: " + e.getStudentId() +
                        " | Status: " + e.getStatus());
            }
        } catch (EntityNotFoundException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void handleUpdateStatus() {
        System.out.print("Enter enrollment ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Select new status:");
        System.out.println("1. ACTIVE");
        System.out.println("2. COMPLETED");
        System.out.println("3. CANCELLED");
        System.out.print("Enter choice: ");
        int statusChoice = scanner.nextInt();
        scanner.nextLine();

        EnrollmentStatus status = switch (statusChoice) {
            case 1 -> EnrollmentStatus.ACTIVE;
            case 2 -> EnrollmentStatus.COMPLETED;
            case 3 -> EnrollmentStatus.CANCELLED;
            default -> null;
        };

        if (status == null) {
            System.out.println("❌ Invalid status choice.");
            return;
        }

        try {
            Enrollment enrollment = enrollmentService.updateEnrollmentStatus(id, status);
            System.out.println("✅ Status updated to: " + enrollment.getStatus());
        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void handleDeleteEnrollment() {
        System.out.print("Enter enrollment ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            enrollmentService.deleteEnrollment(id);
            System.out.println("✅ Enrollment deleted successfully.");
        } catch (EntityNotFoundException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}