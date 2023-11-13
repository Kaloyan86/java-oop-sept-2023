import java.util.List;

public class Basket {

    private List<String> items;
    private String user;
    private double bill;

    void payBasket(Payment payment){
        payment.pay(bill);
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
