package com.aplavina;

import lombok.extern.slf4j.Slf4j;

import java.util.function.DoubleBinaryOperator;

@Slf4j
public class GradientDescentMinimizer implements Minimizer {
    private static final double MIN_STEP = 0.000000000001;

    private final DoubleBinaryOperator function;
    private final double precision;
    private double step;
    private Point point;
    private Point newPoint;
    private final Gradient gradient;
    private double functionValue;
    private double newFunctionValue;

    public GradientDescentMinimizer(DoubleBinaryOperator function, double precision, double step, Point initial) {
        this.function = function;
        this.precision = precision;
        this.step = step;
        this.point = initial;
        this.gradient = new Gradient(function);
    }

    @Override
    public Point minimize() {
        int countIterations = 1;
        iterate();
        while (!isPrecise()) {
            point = newPoint;
            iterate();
            countIterations++;
        }
        log.info("Gradient descent iterations = {}", countIterations);
        return newPoint;
    }

    private void iterate() {
        double x1Gradient = gradient.getX1Derivative(point);
        double x2Gradient = gradient.getX2Derivative(point);
        newPoint = new Point(point.getX1() - step * x1Gradient, point.getX2() - step * x2Gradient);
        functionValue = function.applyAsDouble(point.getX1(), point.getX2());
        newFunctionValue = function.applyAsDouble(newPoint.getX1(), newPoint.getX2());
        changeStep();
    }

    private void changeStep() {
        if (newFunctionValue >= functionValue) {
            step /= 2;
            if (step < MIN_STEP) {
                throw new IllegalStateException("Can not find min of this function");
            }
        }
    }

    private boolean isPrecise() {
        return Math.abs(newFunctionValue - functionValue) <= precision;
    }
}
