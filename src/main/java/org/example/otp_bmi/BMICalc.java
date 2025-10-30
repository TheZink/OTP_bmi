package org.example.otp_bmi;

public class BMICalc {

    public double calculate(double weight, double height){
        return weight / ((height/100) * (height/100));
    }

}
