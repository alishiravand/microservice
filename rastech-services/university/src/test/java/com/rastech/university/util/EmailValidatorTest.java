package com.rastech.university.util;

import com.rastech.university.utils.EmailValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author Ali Shiravand, 10/16/22 10:39 PM
 */
public class EmailValidatorTest {

    private EmailValidator emailValidator;

    @BeforeEach
    void setUp() {
        emailValidator = new EmailValidator();
    }

    //NB: This test is improved and use instead of test1 and test2 in the below
    @ParameterizedTest
    @CsvSource({
            "shiravand.ali@gmail.com,true",
            "shiravand.aligmail.com,false"

    })
    void itShouldValidateEmailAddress(String email, boolean result) {
        //When
        boolean validated = emailValidator.validate(email);
        //Then
        assertThat(validated).isEqualTo(result);
    }

    @Test
    void test1() {
        //Given
        String email = "shiravand.ali@gmail.com";
        //When
        boolean validated = emailValidator.validate(email);
        //Then
        assertThat(validated).isTrue();
    }

    @Test
    @DisplayName("The email address is incorrect")
    void test2() {
        //Given
        String email = "shiravand.aligmail.com";
        //When
        boolean validated = emailValidator.validate(email);
        //Then
        assertThat(validated).isFalse();
    }
}
