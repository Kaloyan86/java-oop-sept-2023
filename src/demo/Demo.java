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
        //
        //        car2.drive(10);
        //        car2.drive(10, "Pleven");
        //
        //        drive(car);
        //        drive(truck);
        //        drive(electricScooter);

        ElectricCar electricCar = new ElectricCar("TESLA", "white", 2011, "MODEL Y", "Coupe");
        ConventionalCar conventionalCar = new ConventionalCar("FORD", "red", 2010, "MUSTANG", "Coupe");

        vehiclesList.add(car);
        vehiclesList.add(electricCar);
        vehiclesList.add(conventionalCar);

        vehiclesList.forEach(Demo::printInfo);

    }

    public static void printInfo(Vehicle vehicle) {
        System.out.println(vehicle.toString());
    }

    public static void drive(Driveable driveable) {
        driveable.drive();
    }
}



















