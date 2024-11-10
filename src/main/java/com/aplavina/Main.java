package com.aplavina;

import lombok.extern.slf4j.Slf4j;

import java.util.function.DoubleBinaryOperator;

@Slf4j
public class Main {
    public static void main(String[] args) {
        DoubleBinaryOperator function = (x1, x2) ->
                (4 * x1 * x1) + (5 * x2 * x2) - (3 * x1 * x2) + (9 * x1) - (2 * x2) + 5;
        double precision = 0.0001;
        Point initial = new Point(2, 3);
        double step = 0.3;
        CoordinateDescentMinimizer coordinateDescentMinimizer =
                new CoordinateDescentMinimizer(function, precision, initial);
        Point coordinateDescentAnswer = coordinateDescentMinimizer.minimize();
        log.info("Coordinate descent result: {} f(Point) = {}", coordinateDescentAnswer,
                function.applyAsDouble(coordinateDescentAnswer.getX1(), coordinateDescentAnswer.getX2()));
        GradientDescentMinimizer gradientDescentMinimizer =
                new GradientDescentMinimizer(function, precision, step, initial);
        Point gradientDescentAnswer = gradientDescentMinimizer.minimize();
        log.info("Gradient descent result: {} f(Point) = {}", gradientDescentAnswer,
                function.applyAsDouble(gradientDescentAnswer.getX1(), gradientDescentAnswer.getX2()));
        double steepestDescentPrecision = 0.01;
        SteepestDescent steepestDescent = new SteepestDescent(initial, steepestDescentPrecision, function);
        Point steepestDescentAnswer = steepestDescent.minimize();
        log.info("Steepest descent result : {} f(Point) = {}", steepestDescentAnswer,
                function.applyAsDouble(steepestDescentAnswer.getX1(), steepestDescentAnswer.getX2()));
    }
}