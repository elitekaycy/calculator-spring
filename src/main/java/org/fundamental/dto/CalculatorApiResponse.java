package org.fundamental.dto;

import java.util.List;

public record CalculatorApiResponse(double result, String current, List<String> history) {
}
