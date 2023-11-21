package auth;

class UserObj implements User{
    String username;
    String password;

    public UserObj(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String username() {
        return this.username;
    }

    @Override
    public String password() {
        return this.password;
    }
}
