// package com.example.demo.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.example.demo.entity.Course;
// import com.example.demo.repository.CourseRepository;

// import java.util.List;

// @Service
// public class CourseService {

// @Autowired
// private CourseRepository courseRepository;

// public List<Course> getAllCourses() {
//     return courseRepository.findAll();
// }

// public Course getCourseById(Long id) {
//     return courseRepository.findById(id).orElse(null);
// }

// public Course createCourse(Course course) {
//     return courseRepository.save(course);
// }

// public Course updateCourse(Long id, Course updatedCourse) {
//     Course existingCourse = courseRepository.findById(id).orElse(null);
//     if (existingCourse != null) {
//         existingCourse.setCourseName(updatedCourse.getCourseName());
//         existingCourse.setDescription(updatedCourse.getDescription());
//         existingCourse.setPhoto(updatedCourse.getPhoto());
// <<<<<<< HEAD
//         existingCourse.setPrice(updatedCourse.getPrice());
// =======
// >>>>>>> 8e65c9618247c3790c0884c32a350e98bca5f9dc
//         existingCourse.setTutor(updatedCourse.getTutor());
//         existingCourse.setVideo(updatedCourse.getVideo());
//         return courseRepository.save(existingCourse);
//     }
//     return null;
// }

// public void deleteCourse(Long id) {
//     courseRepository.deleteById(id);
// }
// }
package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // Get all courses
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Get course by ID
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    // Create new course
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    // Update existing course
    public Course updateCourse(Long id, Course updatedCourse) {
        // Find the course to update
        Course existingCourse = courseRepository.findById(id).orElse(null);
        
        // If course exists, update its details
        if (existingCourse != null) {
            existingCourse.setCourseName(updatedCourse.getCourseName());
            existingCourse.setDescription(updatedCourse.getDescription());
            existingCourse.setPhoto(updatedCourse.getPhoto());
            existingCourse.setPrice(updatedCourse.getPrice()); // Resolved conflict here
            existingCourse.setTutor(updatedCourse.getTutor());
            existingCourse.setVideo(updatedCourse.getVideo());
            return courseRepository.save(existingCourse); // Save the updated course
        }
        return null; // Return null if the course doesn't exist
    }

    // Delete a course
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
