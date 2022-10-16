package com.rastech.university.repository;

import com.rastech.university.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Ali Shiravand, 10/15/22 7:06 PM
 */
@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

    Optional<Student> findByEmail(String email);

    void deleteStudentByEmail(String email);
}
