package src.com.airtribe.learntrack.repository;

import src.com.airtribe.learntrack.entity.Course;
import src.com.airtribe.learntrack.exception.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository {
    private List<Course> courses = new ArrayList<>();

    public void add(Course course) {
        courses.add(course);
    }

    public Course findById(int id) {
        for (Course c : courses) {
            if (c.getId() == id) {
                return c;
            }
        }
        throw new EntityNotFoundException("Course not found with the CourseId " + id);
    }

    public List<Course> findAll() {
        return courses;
    }

    public void deleteById(int id) {
        Course c = findById(id);
        courses.remove(c);
    }

    public void updateCourse(Course c) {
        Course course = findById(c.getId());
        course.setCourseName(c.getCourseName());
        course.setDescription(c.getDescription());
        course.setDurationInWeeks(c.getDurationInWeeks());
        course.setActive(c.isActive());

    }
}