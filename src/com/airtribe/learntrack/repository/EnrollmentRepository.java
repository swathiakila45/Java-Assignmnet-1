package src.com.airtribe.learntrack.repository;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.parser.Entity;

import src.com.airtribe.learntrack.entity.Enrollment;
import src.com.airtribe.learntrack.enums.EnrollmentStatus;
import src.com.airtribe.learntrack.exception.EntityNotFoundException;

public class EnrollmentRepository {

    private List<Enrollment> enrollments = new ArrayList<>();

    public void add(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public Enrollment findById(int id) {
        for (Enrollment e : enrollments) {
            if (e.getId() == id) {
                return e;
            }
        }
        throw new EntityNotFoundException("Enrollment not found with ID: " + id);
    }

    public List<Enrollment> findAll() {
        return enrollments;
    }

    public List<Enrollment> findByStudentId(int studentId) {
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment e : enrollments) {
            if (e.getStudentId() == studentId) {
                result.add(e);
            }
        }
        return result;
    }

    public List<Enrollment> findByCourseId(int courseId) {
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment e : enrollments) {
            if (e.getCourseId() == courseId) {
                result.add(e);
            }
        }
        return result;
    }

    public void deleteById(int id) {
        Enrollment enrollment = findById(id);
        enrollments.remove(enrollment);
    }

    public void updateStatus(int id, EnrollmentStatus status) {
        Enrollment enrollment = findById(id);
        enrollment.setStatus(status);
    }
}