package org.fundamental.service.calculator;

import org.fundamental.dto.CalculatorApiResponse;
import org.fundamental.service.memory.MemoryServiceImpl;
import org.fundamental.service.operation.OperationServiceImpl;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    private final MemoryServiceImpl memoryService;
    private final OperationServiceImpl operationService;

    public CalculatorServiceImpl(MemoryServiceImpl memory, OperationServiceImpl operationService) {
        this.memoryService = memory;
        this.operationService = operationService;
    }

    @Override
    public CalculatorApiResponse add(List<Double> operands) throws Exception {
        this.isValidDoubleArguments(operands);
        double calculatorMemoryValue = operands.stream().reduce(0.0, Double::sum);
        return createCalculatorResponse(calculatorMemoryValue, operands, "+");
    };

    @Override
    public CalculatorApiResponse subtract(List<Double> operands) throws Exception {
        this.isValidDoubleArguments(operands);
        double calculatorMemoryValue = operands.stream().reduce((accumulator, element) -> accumulator - element).orElse(0.0);
        return this.createCalculatorResponse(calculatorMemoryValue, operands, "-");
    }

    @Override
    public CalculatorApiResponse multiply(List<Double> operands) throws Exception {
        this.isValidDoubleArguments(operands);
        double calculatorMemoryValue = operands.stream().reduce((accumulator, element) -> accumulator * element).orElse(1.0);
        return this.createCalculatorResponse(calculatorMemoryValue, operands, "*");
    }

    @Override
    public CalculatorApiResponse divide(List<Double> operands) throws Exception {
        this.isValidDoubleArguments(operands);
        this.isZeroOperandArgument(operands);

        double calculatorMemoryValue = operands.stream().reduce((accumulator, element) -> accumulator / element).orElseThrow(() -> new ArithmeticException("Division by zero is not allowed."));
        return this.createCalculatorResponse(calculatorMemoryValue, operands, "/");
    }

    public CalculatorApiResponse reset() throws IOException {
        String current = this.memoryService.getMemoryCurrent();
        this.memoryService.addToHistory(current);
        this.memoryService.setMemoryCurrent("");
        this.memoryService.setMemory(0.0);

        return new CalculatorApiResponse(
                this.memoryService.getMemory(),
                this.memoryService.getMemoryCurrent(),
                this.memoryService.getHistory());
    }

    public CalculatorApiResponse clearHistory() throws IOException {
        this.memoryService.clearMemory();
        this.memoryService.setMemoryCurrent("");

        return new CalculatorApiResponse(
                this.memoryService.getMemory(),
                this.memoryService.getMemoryCurrent(),
                this.memoryService.getHistory());
    }

    public void isValidDoubleArguments(List<Double> operands) throws Exception {
        if(!operands.stream().allMatch(Objects::nonNull))
            throw new IllegalArgumentException("List contains a non - Double value ");
    }

    public void isZeroOperandArgument(List<Double> operands) throws Exception {
        if (operands.stream().anyMatch(operand -> operand == 0.0)) {
            throw new IllegalArgumentException("Cannot divide by zero. Please provide a non-zero divisor.");
        }
    }

    private String streamCalculatorResponse(List<Double> operands, String operation) {
        return operands.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" " + operation));
    }

    private double getMemoryNotZero() {
        if (this.memoryService.getMemoryCurrent().isEmpty()) {
            return 1.0;
        }

        return this.memoryService.getMemory();
    }

    private CalculatorApiResponse createCalculatorResponse(double calculatorMemoryValue, List<Double> operands, String operation ) throws IOException {
        memoryService.setMemoryCurrent(String.valueOf(calculatorMemoryValue) + operation + streamCalculatorResponse(operands, operation));
        memoryService.setMemory(operationService.performOperation(memoryService.getMemory() ,calculatorMemoryValue, operation));
        return new CalculatorApiResponse(
                memoryService.getMemory(),
                memoryService.getMemoryCurrent(),
                memoryService.getHistory()
        );
    }
}
