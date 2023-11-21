package auth;

import java.util.ArrayList;
import java.util.List;

class Create implements ManageUsers{

    User user;
    static List<User> users = new ArrayList<>();
    Create(User user) {
        this.user = user;
    }

    @Override
    public boolean run() {
        return users.add(user);
    }
}
