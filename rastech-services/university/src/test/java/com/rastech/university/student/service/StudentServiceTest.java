package com.rastech.university.student.service;

import com.rastech.university.domain.Address;
import com.rastech.university.domain.Gender;
import com.rastech.university.domain.Student;
import com.rastech.university.repository.NativeStudentRepository;
import com.rastech.university.repository.StudentRepository;
import com.rastech.university.service.StudentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * @author Ali Shiravand, 10/16/22 2:18 PM
 */
//@ExtendWith(MockitoExtension.class) //another way to initial Object with Mockito
@DirtiesContext
class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;
    private NativeStudentRepository nativeStudentRepository;
    private AutoCloseable autoCloseable;
    @Mock
    private MongoTemplate mongoTemplate;
    StudentService studentService;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        studentService = new StudentService(studentRepository, mongoTemplate);
        nativeStudentRepository = new NativeStudentRepository(mongoTemplate);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void store() {
        //given
        String email = "ariamehr.k@ash.com";
        Student student = new Student(
                "Kourosh", "Ariamehr", email, Gender.MALE,
                new Address("Iran", "Tehran", "16679812379"),
                List.of("It", "Reading Book"),
                new BigDecimal(10)
        );
        //when
        studentService.store(student);

        //then
        ArgumentCaptor<Student> studentArgumentCaptor =
                ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentArgumentCaptor.capture());
        Student captorValue = studentArgumentCaptor.getValue();
        assertThat(captorValue).isEqualTo(student);
    }

    @Test
    void findAll() {
        //given
        //when
        studentService.findAll();
        //then
        verify(studentRepository).findAll();
    }

    @Test
    void itShouldThrownExceptionWhenEmailNotExist() {
        //given
        String email = "ariamehr.k@ash.com";

        //when
        //then
        assertThatThrownBy(() -> studentService.findByEmail(email))
                .isInstanceOf(Exception.class)
                .hasMessageContaining(
                        "Student not found with this email: " + email);

        verify(studentRepository,never()).save(any());
        //then(studentRepository).should();
        //then(studentRepository).shouldHaveNoInteractions();
        //then(studentRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void itShouldGiveStudentWhenEmailISExist() {
        //given
        String email = "ariamehr.k@ash.com";
        Student student = new Student(
                "Kourosh", "Ariamehr", email, Gender.MALE,
                new Address("Iran", "Tehran", "16679812379"),
                List.of("It", "Reading Book"),
                new BigDecimal(10)
        );
        given(studentRepository.findByEmail(email))
                .willReturn(Optional.of(student));
        //when
        Student resultStudent;
        try {
            resultStudent = studentService.findByEmail(email);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        //then
        assertThat(resultStudent).isNotNull();
    }

    @Test
    void itShouldBeZeroWhenStudentWithTheGenderNotExist() {
        //given
        String email = "ariamehr.k@ash.com";

        //when
        List<Student> students = studentService.findAllByGender(Gender.MALE);
        //then
        assertThat(students.size()).isEqualTo(0);
    }

    @Test
    void itHasItemsWhenStudentsWithTheGenderIsExist() {
        //given
        String email = "ariamehr.k@ash.com";
        Student student = new Student(
                "Kourosh", "Ariamehr", email, Gender.MALE,
                new Address("Iran", "Tehran", "16679812379"),
                List.of("It", "Reading Book"),
                new BigDecimal(10)
        );
        given(nativeStudentRepository.findAllByGender(Gender.MALE))
                .willReturn(List.of(student));
        //when
        List<Student> students = studentService.findAllByGender(Gender.MALE);
        //then
        assertThat(students.size()).isEqualTo(1);
        then(studentRepository).shouldHaveNoInteractions();
    }
}