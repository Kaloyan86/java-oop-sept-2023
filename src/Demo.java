import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Demo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Basket basket = new Basket();

        Payment payment1 = new PayPalPayment();
        Payment payment2 = new CreditCardPayment();

        Map<String, Payment> paymentMap = new HashMap<>();
        paymentMap.put("CreditCard", payment2);
        paymentMap.put("PayPal", payment1);

        String input = scanner.nextLine();
        Payment payment = paymentMap.get(input);

        basket.payBasket(payment);
    }
}
