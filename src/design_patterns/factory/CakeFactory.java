package design_patterns.factory;

public class CakeFactory {

    private CakeFactory() {
    }

    public static Cake createCake(String type, double diameter, double price, int pieces) {
        Cake cake = null;

        switch (type) {
            case "BiscuitCake":
                cake = new BiscuitCake(diameter, price, pieces);
                break;
            case "ChocolateCake":
                cake = new ChocolateCake(diameter, price, pieces);
                break;
            case "SpinachCake":
                cake = new SpinachCake(diameter, price, pieces);
                break;
            case "WhiteCake":
                cake = new WhiteCake(diameter, price, pieces);
                break;
        }
        return cake;
    }
}
