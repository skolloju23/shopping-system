package auth;

class Authenticate implements ManageUsers{
    User user;

    Authenticate(User user) {
        this.user = user;
    }

    @Override
    public boolean run() {
        for(User u : Create.users) {
            if(u.username().equals(user.username()) && u.password().equals(user.password())) {
                return true;
            }
        }
        return false;
    }
}
