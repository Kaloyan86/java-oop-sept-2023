package solid_principles;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import solid_principles.solid_demo.Car;
import solid_principles.solid_demo.ConventionalCar;
import solid_principles.solid_demo.ElectricCar;
import solid_principles.solid_demo.Vehicle;
import solid_principles.solid_demo.PrintableVehicle;


public class Demo {
    /*
        S -> Single responsibility - class should only have one responsibility
        O -> Open-closed - open for extension, but close for modifications
        L -> Liskov substitution - object should be replaceable with instances of
             their subtypes without altering the correctness of the program
        I -> Interface segregation - many specific interfaces are better than one
             general interface
        D -> Dependency inversion - depends on abstraction not concretions
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Vehicle conventionalCar = new ConventionalCar();
        Vehicle electricCar = new ElectricCar();

        Map<String, Vehicle> vehicleMap = new LinkedHashMap<>();
        vehicleMap.put("ConventionalCar", conventionalCar);
        vehicleMap.put("ElectricCar", electricCar);

        String input = scanner.nextLine();

        Vehicle vehicle = vehicleMap.get(input);

        driveVehicle(vehicle);

        PrintableVehicle vehicleDetailsPrinter = (PrintableVehicle) conventionalCar;

        printCarDetails(List.of((PrintableVehicle) electricCar, (ConventionalCar) conventionalCar));
    }

    public static void driveVehicle(Vehicle vehicle) {
        if (vehicle instanceof ConventionalCar) {
            Car car = (Car) vehicle;
            car.startEngine();

            PrintableVehicle printer = (PrintableVehicle) car;
            printer.printDetails();
        }
        vehicle.drive();
    }

    static void printCarDetails(List<PrintableVehicle> printableVehicles) {
        printableVehicles.forEach(PrintableVehicle::printDetails);
    }

    void printList(List<String> linkedList) {

    }
}
