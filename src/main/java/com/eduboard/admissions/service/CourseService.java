package com.eduboard.admissions.service;

import com.eduboard.admissions.domain.Course;
import com.eduboard.admissions.domain.Department;
import com.eduboard.admissions.model.CourseDTO;
import com.eduboard.admissions.repos.CourseRepository;
import com.eduboard.admissions.repos.DepartmentRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Created by: Jonathan Tanye
 * Email: jtanye@gmail.com
 * Project: admissions
 * Date: 19/07/2022
 */
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;

    public CourseService(final CourseRepository courseRepository,
            final DepartmentRepository departmentRepository) {
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<CourseDTO> findAll() {
        return courseRepository.findAll(Sort.by("id"))
                .stream()
                .map(course -> mapToDTO(course, new CourseDTO()))
                .collect(Collectors.toList());
    }

    public CourseDTO get(final Long id) {
        return courseRepository.findById(id)
                .map(course -> mapToDTO(course, new CourseDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final CourseDTO courseDTO) {
        final Course course = new Course();
        mapToEntity(courseDTO, course);
        return courseRepository.save(course).getId();
    }

    public void update(final Long id, final CourseDTO courseDTO) {
        final Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(courseDTO, course);
        courseRepository.save(course);
    }

    public void delete(final Long id) {
        courseRepository.deleteById(id);
    }

    private CourseDTO mapToDTO(final Course course, final CourseDTO courseDTO) {
        courseDTO.setId(course.getId());
        courseDTO.setCourseName(course.getCourseName());
        courseDTO.setCourseCategory(course.getCourseCategory());
        courseDTO.setCreatedDate(course.getCreatedDate());
        courseDTO.setCutOffGrade(course.getCutOffGrade());
        courseDTO.setDepartmentCourses(course.getDepartmentCourses() == null ? null : course.getDepartmentCourses().getId());
        return courseDTO;
    }

    private Course mapToEntity(final CourseDTO courseDTO, final Course course) {
        course.setCourseName(courseDTO.getCourseName());
        course.setCourseCategory(courseDTO.getCourseCategory());
        course.setCreatedDate(courseDTO.getCreatedDate());
        course.setCutOffGrade(courseDTO.getCutOffGrade());
        final Department departmentCourses = courseDTO.getDepartmentCourses() == null ? null : departmentRepository.findById(courseDTO.getDepartmentCourses())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "departmentCourses not found"));
        course.setDepartmentCourses(departmentCourses);
        return course;
    }

}
