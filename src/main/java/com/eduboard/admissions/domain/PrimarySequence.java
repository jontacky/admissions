package com.eduboard.admissions.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by: Jonathan Tanye
 * Email: jtanye@gmail.com
 * Project: admissions
 * Date: 19/07/2022
 */

@Document
@Getter
@Setter
public class PrimarySequence {

    @Id
    private String id;

    private long seq;

}
