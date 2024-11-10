package com.aplavina;

import lombok.extern.slf4j.Slf4j;

import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;

import static com.aplavina.Newton.solveNewton;

@Slf4j
public class CoordinateDescentMinimizer implements Minimizer {
    private final DoubleBinaryOperator function;
    private final double precision;
    private Point M0;
    private Point M1;
    private Point M2;

    public CoordinateDescentMinimizer(DoubleBinaryOperator function, double precision, Point initial) {
        this.function = function;
        this.precision = precision;
        M0 = initial;
    }

    @Override
    public Point minimize() {
        int countIterations = 1;
        iterate();
        while (!isPrecise()) {
            countIterations++;
            M0 = M2;
            iterate();
        }
        log.info("Coordinate descent iterations = {}", countIterations);
        return M2;
    }

    private void iterate() {
        DoubleFunction<Double> fixedX2Function = x1 -> function.applyAsDouble(x1, M0.getX2());
        M1 = new Point(solveNewton(fixedX2Function, M0.getX1(), precision), M0.getX2());
        DoubleFunction<Double> fixedX1Function = x2 -> function.applyAsDouble(M1.getX1(), x2);
        M2 = new Point(M1.getX1(), solveNewton(fixedX1Function, M1.getX2(), precision));
    }

    private boolean isPrecise() {
        return Math.abs(function.applyAsDouble(M2.getX1(), M2.getX2()) - function.applyAsDouble(M0.getX1(), M0.getX2())) <= precision;
    }
}
