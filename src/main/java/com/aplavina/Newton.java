package com.aplavina;

import java.util.function.DoubleUnaryOperator;

import static com.aplavina.Derivatives.getDerivative;
import static com.aplavina.Derivatives.getSecondDerivative;

public class Newton {
    private Newton() {
    }

    public static double solveNewton(DoubleUnaryOperator function, double initial, double epsilon) {
        double prevX = initial;
        double prevXDerivative = getDerivative(function, prevX);
        while (Math.abs(prevXDerivative) > epsilon) {
            prevX = prevX - prevXDerivative / getSecondDerivative(function, prevX);
            prevXDerivative = getDerivative(function, prevX);
        }
        return prevX;
    }
}
