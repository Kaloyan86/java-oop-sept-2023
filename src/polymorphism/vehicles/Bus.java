package polymorphism.vehicles;

public class Bus extends AbstractVehicle {

    private static final boolean DEFAULT_IS_DRIVE_EMPTY = true;
    private boolean isEmpty;

    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
        this.isEmpty = DEFAULT_IS_DRIVE_EMPTY;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public void turnOnOfAc(boolean isEmpty) {
        if (isEmpty) {
            super.setFuelConsumption(getFuelConsumption());
        } else {
            super.setFuelConsumption(getFuelConsumption() + 1.4);

        }
    }
}
