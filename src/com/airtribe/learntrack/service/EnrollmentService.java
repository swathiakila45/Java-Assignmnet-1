package src.com.airtribe.learntrack.service;

import src.com.airtribe.learntrack.entity.Enrollment;
import src.com.airtribe.learntrack.enums.EnrollmentStatus;
import src.com.airtribe.learntrack.exception.InvalidInputException;
import src.com.airtribe.learntrack.repository.CourseRepository;
import src.com.airtribe.learntrack.repository.EnrollmentRepository;
import src.com.airtribe.learntrack.repository.StudentRepository;
import src.com.airtribe.learntrack.util.IdGenerator;

import java.util.List;

public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Enrollment enrollStudent(int studentId, int courseId) {

        studentRepository.findById(studentId);

        courseRepository.findById(courseId);

        List<Enrollment> existing = enrollmentRepository.findByStudentId(studentId);
        for (Enrollment e : existing) {
            if (e.getCourseId() == courseId && e.getStatus() == EnrollmentStatus.ACTIVE) {
                throw new InvalidInputException("Student is already enrolled in this course.");
            }
        }

        int id = IdGenerator.getNextEnrollmentId();
        Enrollment enrollment = new Enrollment(id, studentId, courseId);
        enrollmentRepository.add(enrollment);
        return enrollment;
    }

    public Enrollment getEnrollmentById(int id) {
        return enrollmentRepository.findById(id);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public List<Enrollment> getEnrollmentsByStudentId(int studentId) {
        studentRepository.findById(studentId);
        return enrollmentRepository.findByStudentId(studentId);
    }

    public List<Enrollment> getEnrollmentsByCourseId(int courseId) {
        courseRepository.findById(courseId);
        return enrollmentRepository.findByCourseId(courseId);
    }

    public Enrollment updateEnrollmentStatus(int id, EnrollmentStatus status) {

        Enrollment enrollment = enrollmentRepository.findById(id);

        if (enrollment.getStatus() == EnrollmentStatus.COMPLETED
                && status == EnrollmentStatus.ACTIVE) {
            throw new InvalidInputException("Cannot reactivate a completed enrollment.");
        }

        enrollmentRepository.updateStatus(id, status);
        enrollment.setStatus(status);
        return enrollment;
    }

    public void deleteEnrollment(int id) {
        enrollmentRepository.deleteById(id);
    }
}