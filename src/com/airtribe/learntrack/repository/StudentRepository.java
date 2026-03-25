package src.com.airtribe.learntrack.repository;

import java.util.ArrayList;
import java.util.List;
import src.com.airtribe.learntrack.entity.Student;
import src.com.airtribe.learntrack.exception.EntityNotFoundException;

public class StudentRepository {

    private List<Student> students = new ArrayList<>();

    public void add(Student student) {
        students.add(student);
    }

    public Student findById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        throw new EntityNotFoundException("Student not found with ID: " + id);
    }

    public List<Student> findAll() {
        return students;
    }

    public Student deleteById(int id) {
        Student student = findById(id);
        students.remove(student);
        return student;
    }

    public void updateStudent(Student updateStudent) {
        Student existing = findById(updateStudent.getId());
        existing.setFirstName(updateStudent.getFirstName());
        existing.setLastName(updateStudent.getLastName());
        existing.setEmail(updateStudent.getEmail());
        existing.setBatch(updateStudent.getBatch());
        existing.setActive(updateStudent.isActive());
    }
}