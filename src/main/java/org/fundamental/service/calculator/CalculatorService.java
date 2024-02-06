package org.fundamental.service.calculator;

import org.fundamental.dto.CalculatorApiResponse;

import java.util.List;

public interface CalculatorService {
    CalculatorApiResponse add(List<Double> operands) throws Exception;

    CalculatorApiResponse subtract(List<Double> operands) throws Exception;

    CalculatorApiResponse multiply(List<Double> operands) throws Exception;

    CalculatorApiResponse divide(List<Double> operands) throws Exception;
}
