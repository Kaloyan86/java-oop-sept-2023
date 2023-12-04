package solid_principles.solid.products;

import solid_principles.solid.Food;

public class Chocolate implements Food {

    public static final double CALORIES_PER_100_GRAMS = 575.0;

    private double grams;

    public Chocolate(double grams) {
        this.grams = grams;
    }

    public double getGrams() {
        return grams;
    }

    @Override
    public double amountOfCalories() {
        return CALORIES_PER_100_GRAMS / 100 * getGrams();
    }

    @Override
    public double amountOfFood() {
        return getGrams() / 1000;
    }
}
