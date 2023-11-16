package reflection_and_annotation.harvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Class<RichSoilLand> richSoilLandClass = RichSoilLand.class;
        Field[] declaredFields = richSoilLandClass.getDeclaredFields();

        Consumer<Field> printField = field -> System.out.printf("%s %s %s\n",
                                                                Modifier.toString(field.getModifiers()),
                                                                field.getType().getSimpleName(),
                                                                field.getName());

        String line = scanner.nextLine();

        while (!"HARVEST".equals(line)) {

            if ("all".equals(line)) {
                Arrays.stream(declaredFields).forEach(printField);
            } else {
                String finalLine = line;
                Arrays.stream(declaredFields)
                      .filter(field -> Modifier.toString(field.getModifiers()).equals(finalLine))
                      .forEach(printField);
            }

            line = scanner.nextLine();
        }

    }
}
