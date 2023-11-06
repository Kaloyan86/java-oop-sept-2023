package demo;

public class Car extends Vehicle implements Driveable {

    private String type; // coupe, sedan, estate car, van...

    public Car(String make, String color, int year, String model, String type) {
        super(make, color, year, model);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void drive() {
        System.out.println("You are driving a car!");
    }

    public String getCarRemainingKm(int km) {
        return "Car " + Driveable.getRemainingKm(km);
    }
}
