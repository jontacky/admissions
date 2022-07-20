package com.eduboard.admissions.repos;

import com.eduboard.admissions.domain.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by: Jonathan Tanye
 * Email: jtanye@gmail.com
 * Project: admissions
 * Date: 19/07/2022
 */
public interface CourseRepository extends MongoRepository<Course, Long> {
}
