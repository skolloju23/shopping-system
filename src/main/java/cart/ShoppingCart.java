package cart;

import auth.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

final public class ShoppingCart {
    static public List<Product> items;
    static public Map<User, List<Product>> userCart = new HashMap<>();

    static public void addToCart(User user, ProductObj product) {
        if (userCart.containsKey(user)) {
            items = userCart.get(user);
            items.add(product);
            userCart.put(user, items);
        } else {
            items.add(product);
            userCart.put(user, items);
        }
    }

    static public void removeFromCart(User user, ProductObj product) {
        if (userCart.containsKey(user)) {
            items = userCart.get(user);
            items.remove(product);
            userCart.put(user, items);
        }
    }
}
