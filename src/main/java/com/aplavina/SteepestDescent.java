package com.aplavina;

import lombok.extern.slf4j.Slf4j;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

@Slf4j
public class SteepestDescent {
    private static final double INITIAL_FOR_NEWTON = 1.0;

    private Point point;
    private final double precision;
    private final DoubleBinaryOperator function;
    private final Gradient gradient;

    public SteepestDescent(Point point, double precision, DoubleBinaryOperator function) {
        this.point = point;
        this.precision = precision;
        this.function = function;
        this.gradient = new Gradient(function);
    }

    public Point minimize() {
        int countIterations = 1;
        while (gradient.modulo(point) > precision) {
            double x1Gradient = gradient.getX1Derivative(point);
            double x2Gradient = gradient.getX2Derivative(point);
            DoubleUnaryOperator functionToMinimize =
                    h -> function.applyAsDouble(point.getX1() - h * x1Gradient, point.getX2() - h * x2Gradient);
            double h = Newton.solveNewton(functionToMinimize, INITIAL_FOR_NEWTON, precision);
            point = new Point(point.getX1() - h * x1Gradient, point.getX2() - h * x2Gradient);
            countIterations++;
        }
        log.info("Steepest descent iterations = {}", countIterations);
        return point;
    }
}
