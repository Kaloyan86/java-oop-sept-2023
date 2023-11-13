package polymorphism.vehicles;

import java.text.DecimalFormat;

public abstract class AbstractVehicle implements Vehicle {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

    private double fuelQuantity;
    private double fuelConsumption;
    private double tankCapacity;

    public AbstractVehicle(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
        this.tankCapacity = tankCapacity;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    @Override
    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public String drive(double distance) {
        double neededFuel = this.fuelConsumption * distance;

        if (this.fuelQuantity >= neededFuel) {
            this.fuelQuantity -= neededFuel;

            return String.format("%s travelled %s km",
                                 this.getClass().getSimpleName(),
                                 DECIMAL_FORMAT.format(distance));
        }

        return String.format("%s needs refueling", this.getClass().getSimpleName());
    }

    @Override
    public void refuel(double liters) {
        if (liters <= 0) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        }

        if (this.fuelQuantity + liters > this.tankCapacity) {
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }

        this.fuelQuantity += liters;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", this.getClass().getSimpleName(), this.fuelQuantity);
    }
}
