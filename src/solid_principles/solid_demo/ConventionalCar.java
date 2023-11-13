package solid_principles.solid_demo;

public class ConventionalCar implements Car, PrintableVehicle {

    private String name;
    private String model;
    private int year;

    @Override
    public void startEngine() {
        System.out.println("Engine started...");
    }

    @Override
    public void drive() {
        System.out.println("You are driving a car!");
    }

    @Override
    public void printDetails() {
        System.out.println(getDetails());
    }

    public static void changeName(String name) {

    }

    public String getDetails() {
        return String.format("name: %s\n" +
                             "model: %s\n" +
                             "year: %d\n", name, model, year);
    }
}
