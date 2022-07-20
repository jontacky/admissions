package com.eduboard.admissions.model;

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
public class SchoolDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String schoolName;

    @Size(max = 255)
    private String schoolLocation;

    @Size(max = 255)
    private String country;

    @Size(max = 255)
    private String contact;

    @Size(max = 255)
    private String locale;

}
