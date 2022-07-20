package com.eduboard.admissions.service;

import com.eduboard.admissions.domain.School;
import com.eduboard.admissions.model.SchoolDTO;
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
public class SchoolService {

    private final SchoolRepository schoolRepository;

    public SchoolService(final SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public List<SchoolDTO> findAll() {
        return schoolRepository.findAll(Sort.by("id"))
                .stream()
                .map(school -> mapToDTO(school, new SchoolDTO()))
                .collect(Collectors.toList());
    }

    public SchoolDTO get(final Long id) {
        return schoolRepository.findById(id)
                .map(school -> mapToDTO(school, new SchoolDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final SchoolDTO schoolDTO) {
        final School school = new School();
        mapToEntity(schoolDTO, school);
        return schoolRepository.save(school).getId();
    }

    public void update(final Long id, final SchoolDTO schoolDTO) {
        final School school = schoolRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(schoolDTO, school);
        schoolRepository.save(school);
    }

    public void delete(final Long id) {
        schoolRepository.deleteById(id);
    }

    private SchoolDTO mapToDTO(final School school, final SchoolDTO schoolDTO) {
        schoolDTO.setId(school.getId());
        schoolDTO.setSchoolName(school.getSchoolName());
        schoolDTO.setSchoolLocation(school.getSchoolLocation());
        schoolDTO.setCountry(school.getCountry());
        schoolDTO.setContact(school.getContact());
        schoolDTO.setLocale(school.getLocale());
        return schoolDTO;
    }

    private School mapToEntity(final SchoolDTO schoolDTO, final School school) {
        school.setSchoolName(schoolDTO.getSchoolName());
        school.setSchoolLocation(schoolDTO.getSchoolLocation());
        school.setCountry(schoolDTO.getCountry());
        school.setContact(schoolDTO.getContact());
        school.setLocale(schoolDTO.getLocale());
        return school;
    }

    public boolean schoolNameExists(final String schoolName) {
        return schoolRepository.existsBySchoolNameIgnoreCase(schoolName);
    }

}
