package demo;

public class ElectricCar extends Car {

    private ElectricMotor electricMotor;

    public ElectricCar(String make, String color, int year, String model, String type) {
        super(make, color, year, model, type);
        this.electricMotor = new ElectricMotor();
    }

    @Override
    public String toString() {
        return super.toString() + String.format("Type: %s\n", getType());
    }
}
