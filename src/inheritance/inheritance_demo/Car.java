package inheritance.inheritance_demo;

public class Car extends Vehicle {

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
}
