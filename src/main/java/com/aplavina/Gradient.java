package com.aplavina;

import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;

public class Gradient {
    private final BinaryOperator<Double> x1Derivative;
    private final BinaryOperator<Double> x2Derivative;

    public Gradient(DoubleBinaryOperator function) {
        x1Derivative = (x1, x2) -> Derivatives.getDerivative(x -> function.applyAsDouble(x, x2), x1);
        x2Derivative = (x1, x2) -> Derivatives.getDerivative(x -> function.applyAsDouble(x1, x), x2);
    }

    public double getX1Derivative(Point point) {
        return x1Derivative.apply(point.getX1(), point.getX2());
    }

    public double getX2Derivative(Point point) {
        return x2Derivative.apply(point.getX1(), point.getX2());
    }

    public double modulo(Point point) {
        double x1DerivativeValue = x1Derivative.apply(point.getX1(), point.getX2());
        double x2DerivativeValue = x2Derivative.apply(point.getX1(), point.getX2());
        return Math.sqrt(x1DerivativeValue * x1DerivativeValue + x2DerivativeValue * x2DerivativeValue);
    }
}
