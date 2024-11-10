package com.aplavina;

import java.util.function.DoubleFunction;

import static com.aplavina.Derivatives.getDerivative;
import static com.aplavina.Derivatives.getSecondDerivative;

public class Newton {
    public static double solveNewton(DoubleFunction<Double> function, double initial, double epsilon) {
        double prevX = initial;
        double prevXDerivative = getDerivative(function, prevX);
        while (Math.abs(prevXDerivative) > epsilon) {
            prevX = prevX - prevXDerivative / getSecondDerivative(function, prevX);
            prevXDerivative = getDerivative(function, prevX);
        }
        return prevX;
    }
}
