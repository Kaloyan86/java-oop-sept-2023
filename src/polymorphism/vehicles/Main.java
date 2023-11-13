package polymorphism.vehicles;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("\\s+");
        Vehicle vehicle1 = new Car(Double.parseDouble(tokens[1]),
                                   Double.parseDouble(tokens[2]),
                                   Double.parseDouble(tokens[3]));

        tokens = scanner.nextLine().split("\\s+");
        Vehicle vehicle2 = new Truck(Double.parseDouble(tokens[1]),
                                     Double.parseDouble(tokens[2]),
                                     Double.parseDouble(tokens[3]));

        tokens = scanner.nextLine().split("\\s+");
        Vehicle vehicle3 = new Bus(Double.parseDouble(tokens[1]),
                                   Double.parseDouble(tokens[2]),
                                   Double.parseDouble(tokens[3]));

        Map<String, Vehicle> vehicleMap = new LinkedHashMap<>();
        vehicleMap.put("Car", vehicle1);
        vehicleMap.put("Truck", vehicle2);
        vehicleMap.put("Bus", vehicle3);

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            tokens = scanner.nextLine().split("\\s+");

            Vehicle vehicle = vehicleMap.get(tokens[1]);
            double param = Double.parseDouble(tokens[2]);
            try {
                switch (tokens[0]) {
                    case "Drive":
                        vehicle.setEmpty(false);
                        vehicle.turnOnOfAc(vehicle.isEmpty());
                        System.out.println(vehicle.drive(param));
                        break;
                    case "Refuel":
                        vehicle.refuel(param);
                        break;
                    case "DriveEmpty":
                        vehicle.setEmpty(true);
                        vehicle.turnOnOfAc(vehicle.isEmpty());
                        System.out.println(vehicle.drive(param));
                        break;
                }
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }

        }
        vehicleMap.values().forEach(System.out::println);
    }
}
