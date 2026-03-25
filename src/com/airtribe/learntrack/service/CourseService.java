package src.com.airtribe.learntrack.service;

import src.com.airtribe.learntrack.entity.Course;
import src.com.airtribe.learntrack.exception.InvalidInputException;
import src.com.airtribe.learntrack.repository.CourseRepository;
import src.com.airtribe.learntrack.util.IdGenerator;

import java.util.List;

public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course addCourse(String courseName, String description, int durationInWeeks) {

        if (courseName == null || courseName.trim().isEmpty() ||
                description == null || description.trim().isEmpty()) {
            throw new InvalidInputException("All fields are required.");
        }
        if (durationInWeeks <= 0) {
            throw new InvalidInputException("Duration must be greater than 0.");
        }

        List<Course> courses = courseRepository.findAll();
        for (Course c : courses) {
            if (c.getCourseName().equalsIgnoreCase(courseName)) {
                throw new InvalidInputException("Course with this name already exists.");
            }
        }

        int id = IdGenerator.getNextCourseId();
        Course course = new Course(id, courseName.trim(), description.trim(), durationInWeeks);
        courseRepository.add(course);
        return course;
    }

    public Course getCourseById(int id) {
        return courseRepository.findById(id);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course updateCourse(int id, String courseName, String description,
            int durationInWeeks, boolean active) {

        Course existingCourse = courseRepository.findById(id);

        if (courseName == null || courseName.trim().isEmpty() ||
                description == null || description.trim().isEmpty()) {
            throw new InvalidInputException("All fields are required.");
        }
        if (durationInWeeks <= 0) {
            throw new InvalidInputException("Duration must be greater than 0.");
        }

        List<Course> courses = courseRepository.findAll();
        for (Course c : courses) {
            if (c.getCourseName().equalsIgnoreCase(courseName) && c.getId() != id) {
                throw new InvalidInputException("Course with this name already exists.");
            }
        }

        existingCourse.setCourseName(courseName.trim());
        existingCourse.setDescription(description.trim());
        existingCourse.setDurationInWeeks(durationInWeeks);
        existingCourse.setActive(active);

        courseRepository.updateCourse(existingCourse);
        return existingCourse;
    }

    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }
}