package com.rastech.university.repository;

import com.rastech.university.domain.Gender;
import com.rastech.university.domain.Student;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * @author Ali Shiravand, 10/15/22 9:07 PM
 */
public record NativeStudentRepository(MongoTemplate mongoTemplate) {
    public List<Student> findAllByGender(Gender gender) {
        Query query = new Query();
        query.addCriteria(Criteria.where("gender").is(gender));
        return mongoTemplate.find(query, Student.class);
    }
}
