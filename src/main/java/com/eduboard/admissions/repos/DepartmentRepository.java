package com.eduboard.admissions.repos;

import com.eduboard.admissions.domain.Department;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by: Jonathan Tanye
 * Email: jtanye@gmail.com
 * Project: admissions
 * Date: 19/07/2022
 */
public interface DepartmentRepository extends MongoRepository<Department, Long> {

    boolean existsByDepartmentNameIgnoreCase(String departmentName);

}
