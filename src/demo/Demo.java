package demo;

import java.util.ArrayList;
import java.util.List;

public class Demo {

    public static void main(String[] args) {

        List<Vehicle> vehiclesList = new ArrayList<>();

        Car car = new Car("KIA", "white", 2011, "VENGA", "Coupe");
        Car car2 = new Car("DACIA", "white", 2011, "DOCKER", "VAN");
        Truck truck = new Truck("MAN", "white", 2020, "XYZ");
        ElectricScooter electricScooter = new ElectricScooter();

        drive(car);
        drive(truck);
        drive(electricScooter);

        ElectricCar electricCar = new ElectricCar("TESLA", "white", 2011, "MODEL Y", "Coupe");
        ConventionalCar conventionalCar = new ConventionalCar("FORD", "red", 2010, "MUSTANG", "Coupe");

        vehiclesList.add(car);
        vehiclesList.add(electricCar);
        vehiclesList.add(conventionalCar);

        //  vehiclesList.forEach(System.out::println);

        //        printInfo(car);
        //        printInfo(electricCar);
        //        printInfo(conventionalCar);
    }

    public static void printInfo(Vehicle vehicle) {
        if (vehicle instanceof Car) {
            //            TODO
        } else if (vehicle instanceof Truck) {

        }
        System.out.println(vehicle.toString());
    }

    public static void drive(Driveable driveable) {
        driveable.drive();
    }
}
