package com.eduboard.admissions.model;

import java.time.LocalDate;
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
public class ApplicationDTO {

    private Long id;

    @Size(max = 255)
    private String academicYear;

    private LocalDate createdDate;

    @Size(max = 255)
    private String status;

    private Long studentApplication;

    private Long school;

    private Long course;

}
