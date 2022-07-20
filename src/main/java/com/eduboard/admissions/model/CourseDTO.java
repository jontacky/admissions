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
public class CourseDTO {

    private Long id;

    @Size(max = 255)
    private String courseName;

    @Size(max = 255)
    private String courseCategory;

    private LocalDate createdDate;

    @NotNull
    private Long cutOffGrade;

    @NotNull
    private Long departmentCourses;

}
