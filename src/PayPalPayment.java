public class PayPalPayment implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("Pay-pal payment!");
    }
}
