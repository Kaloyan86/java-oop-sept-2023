package demo;

public class Truck extends Vehicle implements Driveable{

    public Truck(String make, String color, int year, String model) {
        super(make, color, year, model);
    }

    @Override
    public void drive() {
        System.out.println("You are driving a truck!");
    }

}
