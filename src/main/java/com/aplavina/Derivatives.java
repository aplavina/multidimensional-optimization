package com.aplavina;

import java.util.function.DoubleUnaryOperator;

public class Derivatives {
    private static final double DERIVATIVE_PRECISION = 0.000001;

    private Derivatives() {
    }

    public static double getDerivative(DoubleUnaryOperator function, double x) {
        return (function.applyAsDouble(x + DERIVATIVE_PRECISION) - function.applyAsDouble(x)) / DERIVATIVE_PRECISION;
    }

    public static double getSecondDerivative(DoubleUnaryOperator function, double x) {
        return (function.applyAsDouble(x + DERIVATIVE_PRECISION) - 2 * function.applyAsDouble(x)
                + function.applyAsDouble(x - DERIVATIVE_PRECISION))
                / (DERIVATIVE_PRECISION * DERIVATIVE_PRECISION);
    }
}
