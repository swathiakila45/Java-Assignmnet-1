package com.airtribe.learntrack;

import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.ui.CourseMenu;
import com.airtribe.learntrack.ui.EnrollmentMenu;
import com.airtribe.learntrack.ui.MainMenu;
import com.airtribe.learntrack.ui.StudentMenu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        
        Scanner scanner = new Scanner(System.in);

        // Creating repositories 
        StudentRepository studentRepository = new StudentRepository();
        CourseRepository courseRepository = new CourseRepository();
        EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

        // Creating the services and injecting the repositories 
        StudentService studentService = new StudentService(studentRepository);
        CourseService courseService = new CourseService(courseRepository);
        EnrollmentService enrollmentService = new EnrollmentService(
                enrollmentRepository, studentRepository, courseRepository);

        // Creating menu and injecting the services and the scanner object 
        StudentMenu studentMenu = new StudentMenu(studentService, scanner);
        CourseMenu courseMenu = new CourseMenu(courseService, scanner);
        EnrollmentMenu enrollmentMenu = new EnrollmentMenu(enrollmentService, scanner);

        //Starting the app
        MainMenu mainMenu = new MainMenu(studentMenu, courseMenu, enrollmentMenu, scanner);
        mainMenu.show();

        scanner.close();
    }
}