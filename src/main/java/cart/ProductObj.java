package cart;

public class ProductObj implements Product {
    String name;
    int price;

    public ProductObj(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public int price() {
        return this.price;
    }
}
