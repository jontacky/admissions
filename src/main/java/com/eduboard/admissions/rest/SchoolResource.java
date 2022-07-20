package com.eduboard.admissions.rest;

import com.eduboard.admissions.model.SchoolDTO;
import com.eduboard.admissions.service.SchoolService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by: Jonathan Tanye
 * Email: jtanye@gmail.com
 * Project: admissions
 * Date: 19/07/2022
 */

@RestController
@RequestMapping(value = "/api/schools", produces = MediaType.APPLICATION_JSON_VALUE)
public class SchoolResource {

    private final SchoolService schoolService;

    public SchoolResource(final SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping
    public ResponseEntity<List<SchoolDTO>> getAllSchools() {
        return ResponseEntity.ok(schoolService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolDTO> getSchool(@PathVariable final Long id) {
        return ResponseEntity.ok(schoolService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createSchool(@RequestBody @Valid final SchoolDTO schoolDTO) {
        return new ResponseEntity<>(schoolService.create(schoolDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSchool(@PathVariable final Long id,
            @RequestBody @Valid final SchoolDTO schoolDTO) {
        schoolService.update(id, schoolDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteSchool(@PathVariable final Long id) {
        schoolService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
