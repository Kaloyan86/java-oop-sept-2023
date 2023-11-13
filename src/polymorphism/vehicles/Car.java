package polymorphism.vehicles;

public class Car extends AbstractVehicle {

    private static final double SUMMER_FUEL_CONSUMPTION = 0.9;

    public Car(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption + SUMMER_FUEL_CONSUMPTION, tankCapacity);
    }
}
