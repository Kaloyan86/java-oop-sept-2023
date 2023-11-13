package solid_principles.solid;

import java.util.List;

public class Printer {

    private static final String SUM = "Sum: %f";
    private static final String AVERAGE = "Average: %f";

    public void printSum(List<Product> products, CalorieCalculator calorieCalculator) {
        System.out.printf((SUM) + "%n", calorieCalculator.sum(products));
    }

    public void printAverage(List<Product> products, CalorieCalculator calorieCalculator) {
        System.out.printf((AVERAGE) + "%n", calorieCalculator.average(products));
    }
}
