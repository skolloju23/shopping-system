package auth;

public class UserFactory {
    static public UserObj getUser(String username, String password) {
        return new UserObj(username, password);
    }

    static public ManageUsers getCreator(User user) {
        return new Create(user);
    }

    static public ManageUsers getAuthenticator(User user) {
        return new Authenticate(user);
    }
}
