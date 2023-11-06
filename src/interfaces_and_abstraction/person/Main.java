package interfaces_and_abstraction.person;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Buyer> buyerMap = new HashMap<>();

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String name = tokens[0];
            int age = Integer.parseInt(tokens[1]);

            if (tokens.length == 4) {
                Citizen citizen = new Citizen(name, age, tokens[2], tokens[3]);
                buyerMap.put(name, citizen);
            } else {
                Rebel rebel = new Rebel(name, age, tokens[2]);
                buyerMap.put(name, rebel);
            }
        }


        String input = scanner.nextLine();

        while (!"End".equals(input)) {
            String name = input;
            Buyer buyer = buyerMap.get(name);

            if (buyer != null) {
                buyer.buyFood();
            }

            input = scanner.nextLine();
        }
        int boughtFood = buyerMap.values()
                                 .stream()
                                 .mapToInt(Buyer::getFood)
                                 .sum();

        System.out.println(boughtFood);
    }
}
