package com.rastech.university.sample;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SampleTests {

    private final Calculator calculator = new Calculator();

    @Test
    void shouldBeAdd() {
        //given
        int number1 = 10;
        int number2 = 17;
        //when
        int result = calculator.add(number1, number2);
        //then
        int expected = 27;
        assertThat(result).isEqualTo(expected);
    }

    static class Calculator {

        public int add(int number1, int number2) {
            return number1 + number2;
        }
    }

}
