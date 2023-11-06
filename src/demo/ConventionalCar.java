package demo;

public class ConventionalCar extends Car {

    private Engine engine;

    public ConventionalCar(String make, String color, int year, String model, String type) {
        super(make, color, year, model, type);
        this.engine = new Engine();
    }
}
