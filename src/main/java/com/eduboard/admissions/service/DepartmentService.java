package com.eduboard.admissions.service;

import com.eduboard.admissions.domain.Department;
import com.eduboard.admissions.domain.School;
import com.eduboard.admissions.model.DepartmentDTO;
import com.eduboard.admissions.repos.DepartmentRepository;
import com.eduboard.admissions.repos.SchoolRepository;
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
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final SchoolRepository schoolRepository;

    public DepartmentService(final DepartmentRepository departmentRepository,
            final SchoolRepository schoolRepository) {
        this.departmentRepository = departmentRepository;
        this.schoolRepository = schoolRepository;
    }

    public List<DepartmentDTO> findAll() {
        return departmentRepository.findAll(Sort.by("id"))
                .stream()
                .map(department -> mapToDTO(department, new DepartmentDTO()))
                .collect(Collectors.toList());
    }

    public DepartmentDTO get(final Long id) {
        return departmentRepository.findById(id)
                .map(department -> mapToDTO(department, new DepartmentDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final DepartmentDTO departmentDTO) {
        final Department department = new Department();
        mapToEntity(departmentDTO, department);
        return departmentRepository.save(department).getId();
    }

    public void update(final Long id, final DepartmentDTO departmentDTO) {
        final Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(departmentDTO, department);
        departmentRepository.save(department);
    }

    public void delete(final Long id) {
        departmentRepository.deleteById(id);
    }

    private DepartmentDTO mapToDTO(final Department department, final DepartmentDTO departmentDTO) {
        departmentDTO.setId(department.getId());
        departmentDTO.setDepartmentName(department.getDepartmentName());
        departmentDTO.setCreatedDate(department.getCreatedDate());
        departmentDTO.setSchoolDepartment(department.getSchoolDepartment() == null ? null : department.getSchoolDepartment().getId());
        return departmentDTO;
    }

    private Department mapToEntity(final DepartmentDTO departmentDTO, final Department department) {
        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setCreatedDate(departmentDTO.getCreatedDate());
        final School schoolDepartment = departmentDTO.getSchoolDepartment() == null ? null : schoolRepository.findById(departmentDTO.getSchoolDepartment())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "schoolDepartment not found"));
        department.setSchoolDepartment(schoolDepartment);
        return department;
    }

    public boolean departmentNameExists(final String departmentName) {
        return departmentRepository.existsByDepartmentNameIgnoreCase(departmentName);
    }

}
