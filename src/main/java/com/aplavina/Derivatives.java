package com.aplavina;

import java.util.function.DoubleFunction;

public class Derivatives {
    private static final double DERIVATIVE_PRECISION = 0.000001;

    public static double getDerivative(DoubleFunction<Double> function, double x) {
        return (function.apply(x + DERIVATIVE_PRECISION) - function.apply(x)) / DERIVATIVE_PRECISION;
    }

    public static double getSecondDerivative(DoubleFunction<Double> function, double x) {
        return (function.apply(x + DERIVATIVE_PRECISION) - 2 * function.apply(x) + function.apply(x - DERIVATIVE_PRECISION))
                / (DERIVATIVE_PRECISION * DERIVATIVE_PRECISION);
    }
}
