package com.eduboard.admissions.model;

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
public class FieldError {

    private String field;
    private String errorCode;

}
