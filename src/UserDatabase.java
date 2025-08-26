class UserDatabase {
    private List<User> users;

    public UserDatabase() {
        users = new ArrayList();
        //Add initial users
        users.add(new User("admin", "password"));
        users.add(new User("kyle", "password"));
    }

    public boolean authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
      public void addUser(String username, String password) {
        users.add(new User(username, password));
    }
}