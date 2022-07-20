package com.eduboard.admissions.service;

import com.eduboard.admissions.domain.Application;
import com.eduboard.admissions.domain.Student;
import com.eduboard.admissions.model.StudentDTO;
import com.eduboard.admissions.repos.ApplicationRepository;
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
public class StudentService {

    private final StudentRepository studentRepository;
    private final ApplicationRepository applicationRepository;

    public StudentService(final StudentRepository studentRepository,
            final ApplicationRepository applicationRepository) {
        this.studentRepository = studentRepository;
        this.applicationRepository = applicationRepository;
    }

    public List<StudentDTO> findAll() {
        return studentRepository.findAll(Sort.by("id"))
                .stream()
                .map(student -> mapToDTO(student, new StudentDTO()))
                .collect(Collectors.toList());
    }

    public StudentDTO get(final Long id) {
        return studentRepository.findById(id)
                .map(student -> mapToDTO(student, new StudentDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final StudentDTO studentDTO) {
        final Student student = new Student();
        mapToEntity(studentDTO, student);
        return studentRepository.save(student).getId();
    }

    public void update(final Long id, final StudentDTO studentDTO) {
        final Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(studentDTO, student);
        studentRepository.save(student);
    }

    public void delete(final Long id) {
        studentRepository.deleteById(id);
    }

    private StudentDTO mapToDTO(final Student student, final StudentDTO studentDTO) {
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setPhoneNumber(student.getPhoneNumber());
        studentDTO.setDateOfBirth(student.getDateOfBirth());
        studentDTO.setOwner(student.getOwner() == null ? null : student.getOwner().getId());
        return studentDTO;
    }

    private Student mapToEntity(final StudentDTO studentDTO, final Student student) {
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setPhoneNumber(studentDTO.getPhoneNumber());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        final Application owner = studentDTO.getOwner() == null ? null : applicationRepository.findById(studentDTO.getOwner())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "owner not found"));
        student.setOwner(owner);
        return student;
    }

    public boolean phoneNumberExists(final String phoneNumber) {
        return studentRepository.existsByPhoneNumberIgnoreCase(phoneNumber);
    }

}
