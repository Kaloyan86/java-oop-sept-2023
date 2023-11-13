package solid_principles.solid_demo;

public class ElectricCar implements Vehicle, PrintableVehicle {

    private String name;
    private String model;
    private int year;

    @Override
    public void printDetails() {
        System.out.println(getDetails());
    }

    @Override
    public void drive() {
        System.out.println("You are driving an electric car!");
    }

    public static void changeName(String name) {

    }

    public String getDetails() {
        return String.format("name: %s\n" +
                             "model: %s\n" +
                             "year: %d\n", name, model, year);
    }
}
