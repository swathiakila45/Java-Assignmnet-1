package com.airtribe.learntrack.ui;

import java.util.Scanner;
import com.airtribe.learntrack.enums.MainMenuOption; 

public class MainMenu {

    private final StudentMenu studentMenu;
    private final CourseMenu courseMenu;
    private final EnrollmentMenu enrollmentMenu;
    private final Scanner scanner;

    public MainMenu(StudentMenu studentMenu, CourseMenu courseMenu,
            EnrollmentMenu enrollmentMenu, Scanner scanner) {
        this.studentMenu = studentMenu;
        this.courseMenu = courseMenu;
        this.enrollmentMenu = enrollmentMenu;
        this.scanner = scanner;
    }

    public void show() {
        boolean running = true;

        while (running) {
            System.out.println("\n===== LearnTrack =====");
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Courses");
            System.out.println("3. Manage Enrollments");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

           MainMenuOption option = MainMenuOption.fromValue(choice);

switch (option) {
    case MANAGE_STUDENTS    -> studentMenu.show();
    case MANAGE_COURSES     -> courseMenu.show();
    case MANAGE_ENROLLMENTS -> enrollmentMenu.show();
    case EXIT -> {
        System.out.println("Goodbye.");
        running = false;
    }
    case null -> System.out.println("Invalid option. Try again.");
}
        }
    }
}