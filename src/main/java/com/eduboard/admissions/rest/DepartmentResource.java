package com.eduboard.admissions.rest;

import com.eduboard.admissions.model.DepartmentDTO;
import com.eduboard.admissions.service.DepartmentService;
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
@RequestMapping(value = "/api/departments", produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentResource {

    private final DepartmentService departmentService;

    public DepartmentResource(final DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable final Long id) {
        return ResponseEntity.ok(departmentService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createDepartment(
            @RequestBody @Valid final DepartmentDTO departmentDTO) {
        return new ResponseEntity<>(departmentService.create(departmentDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDepartment(@PathVariable final Long id,
            @RequestBody @Valid final DepartmentDTO departmentDTO) {
        departmentService.update(id, departmentDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteDepartment(@PathVariable final Long id) {
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
