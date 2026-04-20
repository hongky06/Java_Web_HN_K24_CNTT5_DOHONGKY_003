package org.example.java_web_hn_k24_cntt5_dohongky.service;

import org.springframework.stereotype.Service;
import org.example.java_web_hn_k24_cntt5_dohongky.model.Course;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private List<Course> courses = new ArrayList<>();

    public List<Course> findAll() { return courses; }

    public void save(Course course) {
        if (course.getId() == null) {
            course.setId(System.currentTimeMillis());
            courses.add(course);
        } else {
            for (int i = 0; i < courses.size(); i++) {
                if (courses.get(i).getId().equals(course.getId())) {
                    courses.set(i, course);
                    break;
                }
            }
        }
    }

    public Course findById(Long id) {
        return courses.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    public void delete(Long id) {
        courses.removeIf(c -> c.getId().equals(id));
    }

    public List<Course> search(String keyword) {
        return courses.stream()
                .filter(c -> c.getCourseName().toLowerCase().contains(keyword.toLowerCase())
                        || c.getInstructor().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}