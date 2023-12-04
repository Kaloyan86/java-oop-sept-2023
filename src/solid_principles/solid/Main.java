package solid_principles.solid;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Cloud cloud = new Cloud();

        List<Food> list = List.of(cloud);

        System.out.println(QuantityCalculator.average(list));

    }
}
