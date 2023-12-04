package design_patterns.factory;

import java.util.Scanner;

public class CakeFactoryDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // String type, double diameter, double price, int pieces
        String type = scanner.nextLine();
        double diameter = Double.parseDouble(scanner.nextLine());
        double price = Double.parseDouble(scanner.nextLine());
        int pieces = Integer.parseInt(scanner.nextLine());

        Cake cake = PastryShop.orderCake(type, diameter, price, pieces);
    }
}
