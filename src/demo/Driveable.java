package demo;

public interface Driveable {

    // abstract method
    void drive();

    // default method
    default void turnIgnitionOn() {
        System.out.println("Ignition turned on!");
    }

    default void turnIgnitionOff() {
        System.out.println("Ignition turned off!");
    }

    // static method
    static String getRemainingKm(int km) {
        return "Remaining km: " + km;
    }
}
