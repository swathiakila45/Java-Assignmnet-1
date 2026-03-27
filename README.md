# LearnTrack — Student & Course Management System

## Project Overview

LearnTrack is a solo console-based Java application built to manage students, courses, and enrollments for a learning platform.

The project demonstrates core Java and OOP principles through a real-world use case — an admin can add students, create courses, enroll students, and track their progress, all through an interactive console menu.

**Tech Stack:** Core Java, no frameworks, no database — purely in-memory storage to focus on OOP fundamentals.

**Key highlights:**
- Clean 3-layer architecture: Repository → Service → UI
- Input validation and custom exception handling
- Enum-driven menus for type-safe navigation
- Fully encapsulated entities with inheritance hierarchy

---

## Project Structure

```
src/
└── com/
    └── airtribe/
        └── learntrack/
            ├── Main.java
            ├── entity/
            │   ├── Person.java
            │   ├── Student.java
            │   ├── Course.java
            │   └── Enrollment.java
            ├── repository/
            │   ├── StudentRepository.java
            │   ├── CourseRepository.java
            │   └── EnrollmentRepository.java
            ├── service/
            │   ├── StudentService.java
            │   ├── CourseService.java
            │   └── EnrollmentService.java
            ├── ui/
            │   ├── MainMenu.java
            │   ├── StudentMenu.java
            │   ├── CourseMenu.java
            │   └── EnrollmentMenu.java
            ├── exception/
            │   ├── EntityNotFoundException.java
            │   └── InvalidInputException.java
            ├── util/
            │   ├── IdGenerator.java
            │   └── InputValidator.java
            ├── enums/
            │   ├── EnrollmentStatus.java
            │   ├── StudentMenuOption.java
            │   ├── CourseMenuOption.java
            │   ├── EnrollmentMenuOption.java
            │   └── MainMenuOption.java
            └── constants/
                ├── MenuOptions.java
                └── AppConstants.java
```

---

## How to Run

### Prerequisites
- Java 21 or higher
- Any IDE (IntelliJ IDEA, VS Code, Eclipse) or terminal

### Steps

**Using terminal:**
```bash
# 1. Navigate to the project root
cd path/to/learntrack

# 2. Compile all files
javac -d out src/com/airtribe/learntrack/**/*.java src/com/airtribe/learntrack/Main.java

# 3. Run the app
java -cp out com.airtribe.learntrack.Main
```

**Using IntelliJ IDEA:**
1. Open the project folder
2. Right-click `Main.java`
3. Click `Run 'Main.main()'`

---

## Features

### Student Management
- Add a new student (with email validation)
- List all students
- Get student by ID
- Update student details
- Delete a student
- Filter students by batch

### Course Management
- Add a new course
- List all courses
- Get course by ID
- Update course details
- Delete a course

### Enrollment Management
- Enroll a student in a course
- List all enrollments
- Get enrollment by ID
- Get enrollments by student
- Get enrollments by course
- Update enrollment status (ACTIVE / COMPLETED / CANCELLED)
- Delete an enrollment

---

## OOP Concepts Demonstrated

| Concept | Where Used |
|---|---|
| **Encapsulation** | Private fields + public getters/setters in all entity classes |
| **Inheritance** | `Student` and `Trainer` extend `Person` |
| **Polymorphism** | `getDisplayName()` overridden in `Student` |
| **Constructor Overloading** | `Student` has constructors with and without email |
| **Method Overloading** | `getAllStudents()` and `getAllStudents(String batch)` in `StudentService` |
| **Static Methods** | `IdGenerator.getNextStudentId()` etc. |
| **Enums** | `EnrollmentStatus`, `StudentMenuOption`, `CourseMenuOption` etc. |
| **Custom Exceptions** | `EntityNotFoundException`, `InvalidInputException` |
| **Dependency Injection** | Repositories injected into services, services injected into menus |

---

## Architecture

The project follows a 3-layer architecture:

```
UI Layer (Menu)
     ↓ calls
Service Layer (Business Logic)
     ↓ calls
Repository Layer (In-Memory Storage)
```

- **Repository** — stores and retrieves data using in-memory lists
- **Service** — validates input, applies business rules, calls repository
- **Menu** — reads user input, calls service, prints results

---

## Business Rules

- A student cannot be enrolled in the same course twice if already ACTIVE
- A completed enrollment cannot be reactivated
- Email must be unique across all students
- Course names must be unique
- Duration in weeks must be greater than 0
- All required fields must be non-null and non-blank

---

## Sample Usage

```
===== LearnTrack =====
1. Manage Students
2. Manage Courses
3. Manage Enrollments
0. Exit
Enter choice: 1

===== Student Menu =====
1. Add Student
2. List All Students
3. Get Student by ID
4. Update Student
5. Delete Student
0. Back
Enter choice: 1

Enter first name: John
Enter last name: Doe
Enter email: john.doe@gmail.com
Enter batch: B1
Student added successfully! ID: 1
```