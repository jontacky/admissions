package com.eduboard.admissions.domain;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import javax.validation.constraints.NotNull;
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
public class Course {

    @Id
    private Long id;

    @Size(max = 255)
    private String courseName;

    @Size(max = 255)
    private String courseCategory;

    private LocalDate createdDate;

    @NotNull
    private Long cutOffGrade;

    @DocumentReference(lazy = true)
    @NotNull
    private Department departmentCourses;

    @DocumentReference(lazy = true, lookup = "{ 'course' : ?#{#self._id} }")
    @ReadOnlyProperty
    private Application course;

    @CreatedDate
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    private OffsetDateTime lastUpdated;

    @Version
    private Integer version;

}
