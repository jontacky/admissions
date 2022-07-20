package com.eduboard.admissions.repos;

import com.eduboard.admissions.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by: Jonathan Tanye
 * Email: jtanye@gmail.com
 * Project: admissions
 * Date: 19/07/2022
 */

public interface StudentRepository extends MongoRepository<Student, Long> {

    boolean existsByPhoneNumberIgnoreCase(String phoneNumber);

}
