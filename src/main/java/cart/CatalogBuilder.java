package cart;

import java.util.ArrayList;
import java.util.List;

 public class CatalogBuilder {
    public static List<Product> products = new ArrayList<>();

     CatalogBuilder() {
        if(products.size() == 0) {
            products.add(new ProductObj("macbook", 1999));
            products.add(new ProductObj("iphone", 699));
            products.add(new ProductObj("ipad", 799));
            products.add(new ProductObj("airpods", 249));
            products.add(new ProductObj("iwatch", 399));
            products.add(new ProductObj("homePod", 299));
            products.add(new ProductObj("imac", 1299));
            products.add(new ProductObj("alexa", 150));
        }
    }
    public CatalogBuilder addProduct(ProductObj product) {
        products.add(product);
        return this;
    }
    public CatalogBuilder removeProduct(ProductObj product) {
        products.remove(product);
        return this;
    }
}
