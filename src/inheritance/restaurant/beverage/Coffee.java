package inheritance.restaurant.beverage;

import java.math.BigDecimal;

public class Coffee extends HotBeverage {

    private static final double COFFEE_MILLILITERS = 50;
    private static final BigDecimal COFFEE_PRICE = new BigDecimal("3.5");

    private double caffeine;

    public Coffee(String name, double caffeine) {
        super(name, COFFEE_PRICE, COFFEE_MILLILITERS);

        this.caffeine = caffeine;
    }

    public double getCaffeine() {
        return caffeine;
    }
}
