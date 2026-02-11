package com.xavi.propertymanagement.controller;

import com.xavi.propertymanagement.model.CalculatorOperationsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/calculator")
public class CalculatorOperationsController {

    @GetMapping("/add")
    public Double add(@RequestParam("num1") Double a, @RequestParam("num2") Double b) {
        return a + b;
    }

    @GetMapping("/substract/{a}/{b}")
    public Double subtract(@PathVariable("a") Double a, @PathVariable("b") Double b, @RequestParam String message) {
        Double result = null;

        if (a > b) {
            result = a - b;
        } else if (a < b) {
            result = b - a;
        }
        return result;
    }

    @PostMapping("/multiply")
    public ResponseEntity<Double> multiply(@RequestBody CalculatorOperationsDTO calculatorOperationsDTO) {
        Double result = calculatorOperationsDTO.getNum1()
                * calculatorOperationsDTO.getNum2()
                * calculatorOperationsDTO.getNum3()
                * calculatorOperationsDTO.getNum4();

        return new ResponseEntity<Double>(result, HttpStatus.CREATED);
    }
}
