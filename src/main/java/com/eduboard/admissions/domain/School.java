package com.eduboard.admissions.domain;

import java.time.OffsetDateTime;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
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
public class School {

    @Id
    private Long id;

    @Indexed(unique = true)
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

    @DocumentReference(lazy = true, lookup = "{ 'schoolDepartment' : ?#{#self._id} }")
    @ReadOnlyProperty
    private Set<Department> schoolDepartmentDepartments;

    @DocumentReference(lazy = true, lookup = "{ 'school' : ?#{#self._id} }")
    @ReadOnlyProperty
    private Application school;

    @CreatedDate
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    private OffsetDateTime lastUpdated;

    @Version
    private Integer version;

}
