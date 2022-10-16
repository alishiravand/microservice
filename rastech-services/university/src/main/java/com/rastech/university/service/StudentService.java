package com.rastech.university.service;

import com.rastech.university.domain.Gender;
import com.rastech.university.domain.Student;
import com.rastech.university.repository.NativeStudentRepository;
import com.rastech.university.repository.StudentRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author Ali Shiravand, 10/15/22 7:07 PM
 */
@Service
public record StudentService(StudentRepository repository, MongoTemplate mongoTemplate) {
    public Student store(Student student) {
        return repository.insert(student);
    }

    public List<Student> findAll() {
        return repository.findAll();
    }

    public Student findByEmail(String email) throws Throwable {
        Optional<Student> result = repository.findByEmail(email);
        result.orElseThrow((Supplier<Throwable>) () -> new Exception("Student not found with this email: " + email));
        return result.get();
    }

    public List<Student> findAllByGender(Gender gender) {
        NativeStudentRepository nativeStudentRepository = new NativeStudentRepository(mongoTemplate);
        return nativeStudentRepository.findAllByGender(gender);
    }
}
