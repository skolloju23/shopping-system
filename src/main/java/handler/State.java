package handler;

import auth.User;
import auth.UserFactory;
import cart.*;

import java.util.ArrayList;
import java.util.List;

public enum State implements CartState {

    StartState {
        State next;
        @Override
        public void process(Handle handle) {
            switch (printStartMenu(handle)) {
                case 1 -> next = Login;
                case 2 -> next = Register;
                default -> next = StartState;
            }
        }
        @Override
        public State next() {
            return next;
        }
        int printStartMenu(Handle handle) {
            return handle.inputAndOutput.getInput("is it your first time? Register!!!, you already have an account? GREAT!!! just login\n" +
                    "1. to Login\n" +
                    "2. to Register");

        }
    },
    Login {
        State next;
        @Override
        public void process(Handle handle) {
            Handle.user = printLoginMenu(handle);
            if(UserFactory.getAuthenticator(Handle.user).run()){
                handle.inputAndOutput.showOutput("Login Successful");
                next = ViewCatlog;
            }else{
                handle.inputAndOutput.showOutput("Login Failed");
                next = StartState;
            }
        }
        @Override
        public State next() {
            return next;
        }

    },
    Register {
        State next;
        @Override
        public void process(Handle handle) {
            /*handle.inputAndOutput.getStringInput("Enter your First Name");
            handle.inputAndOutput.getStringInput("Enter your Last Name");
            handle.inputAndOutput.getStringInput("Enter your Email");
            handle.inputAndOutput.getStringInput("Enter your Phone Number");
            handle.inputAndOutput.getStringInput("Enter your Address");*/
            User user = printLoginMenu(handle);
            while(UserFactory.getAuthenticator(UserFactory.getUser(user.username(), user.password())).run()){
                handle.inputAndOutput.getStringInput("username already exists, enter a different username");
                user = printLoginMenu(handle);
            }
            UserFactory.getCreator(user).run();
            Handle.user = user;
            next = ViewCatlog;
        }

        @Override
        public State next() {
            return next;
        }
    },
    ViewCatlog {

        State next;
        @Override
        public void process(Handle handle) {
            while (true) {
                String input = handle.inputAndOutput.getStringInput(printCatalog());
                if (input.equals("cart")) {
                    next = ViewCart;
                    break;
                }else if(input.equals("logout")) {
                    next = StartState;
                    break;
                } else{
                    List<Product> products = ShoppingCart.userCart.get(Handle.user);
                    if(products==null){
                        products = new ArrayList<>();
                    }
                    List<Product> catalog = ProductFactory.createCatalogBuilder().products;
                    for(Product product: catalog){
                        if(product.name().equals(input)){
                            products.add(product);
                        }
                    }
                    ShoppingCart.userCart.put(Handle.user, products);
                }
            }
        }

        @Override
        public State next() {
            return next;
        }

        String printCatalog(){
            String catalog = "enter the item name to add to cart\n"+
                    "enter cart to view cart:\n"+
                    "enter logout to logout\n";
            List<Product> products = ProductFactory.createCatalogBuilder().products;
            for(Product product: products){
                catalog+="item name: "+product.name()+" item price: "+product.price()+"\n";
            }
            return catalog;
        }
    },
    Checkout {
        State next;
        @Override
        public void process(Handle handle) {
            List<Product> products = ShoppingCart.userCart.get(Handle.user);
            if(products==null){
                handle.inputAndOutput.showOutput("cart is empty");
                next = ViewCatlog;
            }else{
                handle.inputAndOutput.showOutput("total amount to pay: "+ShoppingCart.userCart.get(Handle.user).stream().mapToDouble(Product::price).sum());
                String input = handle.inputAndOutput.getStringInput("enter 'pay' to pay; 'cart' to add/remove more items to cart; 'logout' to logout");
                if(input.equals("pay")) {
                    next = Payment;
                }else if(input.equals("cart")) {
                    next = ViewCart;
                }else if(input.equals("logout")) {
                    next = Logout;
                }
            }

        }

        @Override
        public State next() {
            return next;
        }
    },
    Payment {
        State next;
        @Override
        public void process(Handle handle) {
            String input;
            input = handle.inputAndOutput.getStringInput("enter your credit or debit card number; 'cancel' to cancel payment");
            input = handle.inputAndOutput.getStringInput("enter your card expiry date or 'cancel' to if you're cancelling the payment");
            input = handle.inputAndOutput.getStringInput("enter your card cvv or 'cancel' to if you're cancelling the payment");
            if(input.equals("cancel")){
                next = ViewCart;
            }else{
                input = handle.inputAndOutput.getStringInput("payment successful; enter 'logout' to logout");
                ShoppingCart.userCart.get(Handle.user).clear();
                if(input.equals("logout")){
                    next = Logout;
                }
                next = ViewCatlog;
            }
        }

        @Override
        public State next() {
            return next;
        }
    },
    ViewCart {
        State next;
        @Override
        public void process(Handle handle) {

            String input = handle.inputAndOutput.getStringInput(printCart());
            if(input.equals("checkout")) {
                next = Checkout;
            }else if(input.equals("add")) {
                next = ViewCatlog;
            }else if(input.equals("remove")) {
                next = RemoveProduct;
            }else if(input.equals("logout")) {
                next = Logout;
            }else{
                handle.inputAndOutput.showOutput("invalid input");
                next = ViewCart;
            }
        }

        @Override
        public State next() {
            return next;
        }

        String printCart(){
            String str= "enter 'checkout' to checkout; 'add' to add more items to cart; 'remove to remove items from cart'; 'logout' to logout\n";
            List<Product> products=ShoppingCart.userCart.get(Handle.user);
            if(products!=null){
                for(Product product: products){
                    str+="item name: "+product.name()+" item price: "+product.price()+"\n";
                }
            }
            return str;
        }
    },
    RemoveProduct {
        State next;
        @Override
        public void process(Handle handle) {
            String input = handle.inputAndOutput.getStringInput("enter the item name to remove from cart");
            List<Product> products = ShoppingCart.userCart.get(Handle.user);
            for(Product product: products){
                if(product.name().equals(input)){
                    products.remove(product);
                }
            }
            ShoppingCart.userCart.put(Handle.user, products);
            next = ViewCart;
        }

        @Override
        public State next() {
            return next;
        }
    },
    Logout {
        State next;
        @Override
        public void process(Handle handle) {
            next = StartState;
        }

        @Override
        public State next() {
            return next;
        }
    },
    ExitState {
        @Override
        public void process(Handle handle) {
            System.out.println("ExitState");
        }

        @Override
        public State next() {
            System.out.println("Next");
            return null;
        }
    };

    User printLoginMenu(Handle handle) {
        String username = handle.inputAndOutput.getStringInput("Enter your username");
        String password = handle.inputAndOutput.getStringInput("Enter your password");
        return UserFactory.getUser(username, password);
    }


}
