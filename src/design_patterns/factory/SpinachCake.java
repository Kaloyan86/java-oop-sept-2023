package design_patterns.factory;

public class SpinachCake extends Cake{


    public SpinachCake(double diameter, double price, int pieces) {
        super(diameter, price, pieces);
    }

    @Override
    public void prepare() {
        System.out.println("Preparing Spinach Cake...");
    }

    @Override
    public void bake() {
        System.out.println("Baking Spinach Cake...");
    }

    @Override
    public void box() {
        System.out.println("Boxing Spinach Cake...");
        System.out.println("Spinach Cake is ready");
        System.out.printf("Your bill is %.2f", price * pieces);
    }
}
