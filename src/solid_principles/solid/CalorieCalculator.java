package solid_principles.solid;

import java.util.List;

public class CalorieCalculator {

    private CalorieCalculator() {
    }

    public static double sum(List<Product> products) {
        return products.stream().mapToDouble(Product::amountOfCalories).sum();
    }

    public static double average(List<Product> products) {
        return sum(products) / products.size();
    }
}
