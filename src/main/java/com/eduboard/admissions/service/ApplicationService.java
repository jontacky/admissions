package com.eduboard.admissions.service;

import com.eduboard.admissions.domain.Application;
import com.eduboard.admissions.domain.Course;
import com.eduboard.admissions.domain.School;
import com.eduboard.admissions.domain.Student;
import com.eduboard.admissions.model.ApplicationDTO;
import com.eduboard.admissions.repos.ApplicationRepository;
import com.eduboard.admissions.repos.CourseRepository;
import com.eduboard.admissions.repos.SchoolRepository;
import com.eduboard.admissions.repos.StudentRepository;
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
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final StudentRepository studentRepository;
    private final SchoolRepository schoolRepository;
    private final CourseRepository courseRepository;

    public ApplicationService(final ApplicationRepository applicationRepository,
            final StudentRepository studentRepository, final SchoolRepository schoolRepository,
            final CourseRepository courseRepository) {
        this.applicationRepository = applicationRepository;
        this.studentRepository = studentRepository;
        this.schoolRepository = schoolRepository;
        this.courseRepository = courseRepository;
    }

    public List<ApplicationDTO> findAll() {
        return applicationRepository.findAll(Sort.by("id"))
                .stream()
                .map(application -> mapToDTO(application, new ApplicationDTO()))
                .collect(Collectors.toList());
    }

    public ApplicationDTO get(final Long id) {
        return applicationRepository.findById(id)
                .map(application -> mapToDTO(application, new ApplicationDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final ApplicationDTO applicationDTO) {
        final Application application = new Application();
        mapToEntity(applicationDTO, application);
        return applicationRepository.save(application).getId();
    }

    public void update(final Long id, final ApplicationDTO applicationDTO) {
        final Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(applicationDTO, application);
        applicationRepository.save(application);
    }

    public void delete(final Long id) {
        applicationRepository.deleteById(id);
    }

    private ApplicationDTO mapToDTO(final Application application,
            final ApplicationDTO applicationDTO) {
        applicationDTO.setId(application.getId());
        applicationDTO.setAcademicYear(application.getAcademicYear());
        applicationDTO.setCreatedDate(application.getCreatedDate());
        applicationDTO.setStatus(application.getStatus());
        applicationDTO.setStudentApplication(application.getStudentApplication() == null ? null : application.getStudentApplication().getId());
        applicationDTO.setSchool(application.getSchool() == null ? null : application.getSchool().getId());
        applicationDTO.setCourse(application.getCourse() == null ? null : application.getCourse().getId());
        return applicationDTO;
    }

    private Application mapToEntity(final ApplicationDTO applicationDTO,
            final Application application) {
        application.setAcademicYear(applicationDTO.getAcademicYear());
        application.setCreatedDate(applicationDTO.getCreatedDate());
        application.setStatus(applicationDTO.getStatus());
        final Student studentApplication = applicationDTO.getStudentApplication() == null ? null : studentRepository.findById(applicationDTO.getStudentApplication())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "studentApplication not found"));
        application.setStudentApplication(studentApplication);
        final School school = applicationDTO.getSchool() == null ? null : schoolRepository.findById(applicationDTO.getSchool())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "school not found"));
        application.setSchool(school);
        final Course course = applicationDTO.getCourse() == null ? null : courseRepository.findById(applicationDTO.getCourse())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "course not found"));
        application.setCourse(course);
        return application;
    }

}
