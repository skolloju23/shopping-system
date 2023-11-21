package cart;

public class ProductFactory {
    public static ProductObj createProduct(String name, int price) {
        return new ProductObj(name, price);
    }

    public static CatalogBuilder createCatalogBuilder() {
        return new CatalogBuilder();
    }
}
