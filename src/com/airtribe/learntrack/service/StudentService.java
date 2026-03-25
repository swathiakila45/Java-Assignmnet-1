package src.com.airtribe.learntrack.service;

import java.util.List;

import src.com.airtribe.learntrack.entity.Student;
import src.com.airtribe.learntrack.repository.StudentRepository;
import src.com.airtribe.learntrack.exception.EntityNotFoundException;
import src.com.airtribe.learntrack.exception.InvalidInputException;
import src.com.airtribe.learntrack.util.IdGenerator;
import src.com.airtribe.learntrack.util.InputValidator;

public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(String firstName, String lastName, String email, String batch) {
        if (firstName == null || firstName.trim().isEmpty() ||
                lastName == null || lastName.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                batch == null || batch.trim().isEmpty()) {
            throw new InvalidInputException("All the fields are required");
        }
        InputValidator.validateEmail(email);

        List<Student> students = studentRepository.findAll();
        for (Student s : students) {
            if (s.getEmail().equalsIgnoreCase(email)) {
                throw new InvalidInputException("The email is already present");
            }
        }

        int id = IdGenerator.getNextStudentId();
        Student student = new Student(id, firstName.trim(), lastName.trim(),
                email.trim(), batch.trim());

        studentRepository.add(student);
        return student;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id);
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(int id, String firstName, String lastName, String email, String batch,
            boolean active) {
        Student existingStudent = studentRepository.findById(id);

        if (firstName == null || firstName.trim().isEmpty() ||
                lastName == null || lastName.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                batch == null || batch.trim().isEmpty()) {
            throw new InvalidInputException("All the fields are required");
        }

        List<Student> students = studentRepository.findAll();
        for (Student s : students) {
            if (s.getEmail().equalsIgnoreCase(email) && s.getId() != id) {
                throw new InvalidInputException("Email is already registered.");
            }
        }

        existingStudent.setFirstName(firstName.trim());
        existingStudent.setLastName(lastName.trim());
        existingStudent.setEmail(email.trim());
        existingStudent.setBatch(batch.trim());
        existingStudent.setActive(active);

        studentRepository.updateStudent(existingStudent);
        return existingStudent;

    }
}