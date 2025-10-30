package org.example.otp_bmi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BMICalcTest {
    BMICalc calc = new BMICalc();

    @Test
    void testCalculate() {
        assertEquals(20,1, calc.calculate(65, 180));
    }
}
