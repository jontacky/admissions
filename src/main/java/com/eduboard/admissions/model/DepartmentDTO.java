package com.eduboard.admissions.model;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by: Jonathan Tanye
 * Email: jtanye@gmail.com
 * Project: admissions
 * Date: 19/07/2022
 */

@Getter
@Setter
public class DepartmentDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String departmentName;

    private LocalDate createdDate;

    private Long schoolDepartment;

}
