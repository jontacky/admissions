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
public class Student {

    @Id
    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String email;

    @Indexed(unique = true)
    @Size(max = 255)
    private String phoneNumber;

    @Size(max = 255)
    private String dateOfBirth;

    @DocumentReference(lazy = true, lookup = "{ 'studentApplication' : ?#{#self._id} }")
    @ReadOnlyProperty
    private Set<Application> studentApplicationApplications;

    @DocumentReference(lazy = true)
    private Application owner;

    @CreatedDate
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    private OffsetDateTime lastUpdated;

    @Version
    private Integer version;

}
