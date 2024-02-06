package org.fundamental.controller;

import org.fundamental.dto.CalculatorApiResponse;
import org.fundamental.service.calculator.CalculatorServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CalculatorController {

    private final CalculatorServiceImpl calculatorService;

    public CalculatorController(CalculatorServiceImpl calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/add")
    public ResponseEntity<CalculatorApiResponse> addCalculation(@RequestParam(name = "operands") List<Double> operands) throws Exception {
        CalculatorApiResponse result = calculatorService.add(operands);
        return ResponseEntity.ok(result);
    };

    @GetMapping("/subtract")
    public ResponseEntity<CalculatorApiResponse> subtractCalculation(@RequestParam(name = "operands") List<Double> operands) throws Exception {
        CalculatorApiResponse result = calculatorService.subtract(operands);
        return ResponseEntity.ok(result);
    };

    @GetMapping("/multiply")
    public ResponseEntity<CalculatorApiResponse> multiplyCalculation(@RequestParam(name = "operands") List<Double> operands) throws Exception {
        CalculatorApiResponse result = calculatorService.multiply(operands);
        return ResponseEntity.ok(result);
    };

    @GetMapping("/divide")
    public ResponseEntity<CalculatorApiResponse> divideCalculation(@RequestParam(name = "operands") List<Double> operands) throws Exception {
        CalculatorApiResponse result = calculatorService.divide(operands);
        return ResponseEntity.ok(result);
    };

    @PostMapping("/reset")
    public ResponseEntity<CalculatorApiResponse> resetCalculation() throws Exception {
        CalculatorApiResponse result = calculatorService.reset();
        return ResponseEntity.ok(result);
    };

    @PostMapping("/clear")
    public ResponseEntity<CalculatorApiResponse> divideCalculation() throws Exception {
        CalculatorApiResponse result = calculatorService.clearHistory();
        return ResponseEntity.ok(result);
    };


}
