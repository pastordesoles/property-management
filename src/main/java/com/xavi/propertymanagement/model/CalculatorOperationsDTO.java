package com.xavi.propertymanagement.model;

import lombok.Getter;

@Getter
public class CalculatorOperationsDTO {
    private Double num1;
    private Double num2;
    private Double num3;
    private Double num4;

    public void setNum1(Double num1) {
        this.num1 = num1;
    }

    public void setNum2(Double num2) {
        this.num2 = num2;
    }

    public void setNum3(Double num3) {
        this.num3 = num3;
    }

    public void setNum4(Double num4) {
        this.num4 = num4;
    }
}
