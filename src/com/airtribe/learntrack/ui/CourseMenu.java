package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.enums.CourseMenuOption;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;

import java.util.List;
import java.util.Scanner;

public class CourseMenu {

    private final CourseService courseService;
    private final Scanner scanner;

    public CourseMenu(CourseService courseService, Scanner scanner) {
        this.courseService = courseService;
        this.scanner = scanner;
    }

    public void show() {
        boolean running = true;

        while (running) {
            System.out.println("\n===== Course Menu =====");
            System.out.println("1. Add Course");
            System.out.println("2. List All Courses");
            System.out.println("3. Get Course by ID");
            System.out.println("4. Update Course");
            System.out.println("5. Delete Course");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            CourseMenuOption option = CourseMenuOption.fromValue(choice);

            switch (option) {
                case ADD       -> handleAddCourse();
                case LIST      -> handleListCourses();
                case GET_BY_ID -> handleGetCourseById();
                case UPDATE    -> handleUpdateCourse();
                case DELETE    -> handleDeleteCourse();
                case BACK      -> running = false;
                case null      -> System.out.println("❌ Invalid option. Try again.");
            }
        }
    }

    private void handleAddCourse() {
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter duration in weeks: ");
        int durationInWeeks = scanner.nextInt();
        scanner.nextLine();

        try {
            Course course = courseService.addCourse(courseName, description, durationInWeeks);
            System.out.println("✅ Course added successfully! ID: " + course.getId());
        } catch (InvalidInputException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void handleListCourses() {
        List<Course> courses = courseService.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        for (Course c : courses) {
            System.out.println("ID: " + c.getId() + " | " + c.getCourseName() + " | " + c.getDescription() + " | Weeks: " + c.getDurationInWeeks() + " | Active: " + c.isActive());
        }
    }

    private void handleGetCourseById() {
        System.out.print("Enter course ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            Course course = courseService.getCourseById(id);
            System.out.println("Found: " + course.getCourseName() + " | " + course.getDescription() + " | Weeks: " + course.getDurationInWeeks());
        } catch (EntityNotFoundException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void handleUpdateCourse() {
        System.out.print("Enter course ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter duration in weeks: ");
        int durationInWeeks = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Is active? (true/false): ");
        boolean active = scanner.nextBoolean();
        scanner.nextLine();

        try {
            Course course = courseService.updateCourse(id, courseName, description, durationInWeeks, active);
            System.out.println("✅ Course updated: " + course.getCourseName());
        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void handleDeleteCourse() {
        System.out.print("Enter course ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            courseService.deleteCourse(id);
            System.out.println("✅ Course deleted successfully.");
        } catch (EntityNotFoundException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}