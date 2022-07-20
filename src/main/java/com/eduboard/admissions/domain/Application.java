package com.eduboard.admissions.domain;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

/**
 * Created by: Jonathan Tanye
 * Email: jtanye@gmail.com
 * Project: admissions
 * Date: 19/07/2022
 */
@Document
@Getter
@Setter
public class Application {

    @Id
    private Long id;

    @Size(max = 255)
    private String academicYear;

    private LocalDate createdDate;

    @Size(max = 255)
    private String status;

    @DocumentReference(lazy = true)
    private Student studentApplication;

    @DocumentReference(lazy = true, lookup = "{ 'owner' : ?#{#self._id} }")
    @ReadOnlyProperty
    private Student owner;

    @DocumentReference(lazy = true)
    private School school;

    @DocumentReference(lazy = true)
    private Course course;

    @CreatedDate
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    private OffsetDateTime lastUpdated;

    @Version
    private Integer version;

}
