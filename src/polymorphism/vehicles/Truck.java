package polymorphism.vehicles;

public class Truck extends AbstractVehicle {

    private static final double SUMMER_FUEL_CONSUMPTION = 1.6;

    public Truck(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption + SUMMER_FUEL_CONSUMPTION, tankCapacity);
    }

    @Override
    public void refuel(double liters) {
        super.refuel(liters * 0.95);
    }
}
