package inheritance;

import java.util.ArrayList;
import java.util.List;

import inheritance.inheritance_demo.Car;
import inheritance.inheritance_demo.ConventionalCar;
import inheritance.inheritance_demo.ElectricCar;
import inheritance.inheritance_demo.Vehicle;

public class Demo {
    /*
                 ----- Inheritance ------

       One of the 4 OOP principles ( Abstraction, Encapsulation, Inheritance, Polymorphism )

       Inheritance in Java is a mechanism in which one object acquires all the properties and behaviors of a parent object.

       Inheritance represents the IS-A relationship which is also known as a parent-child relationship.

       When you inherit from an existing class, you can reuse methods and fields of the parent class.
       Moreover, you can add new methods and fields in your current class also.

       To inherit from a class, use the extends keyword.
       - subclass (child) - the class that inherits from another class
       - superclass (parent) - the class being inherited from

       ----- Inheritance - Benefits -----
       - For Method Overriding (so runtime polymorphism can be achieved).
       - For Code Reusability.

       * Multiple Inheritance is not allowed in Java ( for more info see the diamond problem in Java and how it is overcome )

     */

    public static void main(String[] args) {
        List<Vehicle> vehiclesList = new ArrayList<>();

        Car car = new Car("KIA", "white", 2011, "VENGA", "Coupe");
        ElectricCar electricCar = new ElectricCar("TESLA", "white", 2011, "MODEL Y", "Coupe");
        ConventionalCar conventionalCar = new ConventionalCar("FORD", "red", 2010, "MUSTANG", "Coupe");

        vehiclesList.add(car);
        vehiclesList.add(electricCar);
        vehiclesList.add(conventionalCar);

      //  vehiclesList.forEach(System.out::println);

        printInfo(car);
        printInfo(electricCar);
        printInfo(conventionalCar);
    }
    public static void printInfo(Vehicle vehicle){
        System.out.println(vehicle.toString());
    }
}





















