package app;

public class User {
    private String name;
    private String role;
    private int password;

    public User(String name, String role, int password) {
        this.name = name;
        this.role = role;
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public String getRole() {
        return this.role;
    }

    public int getPassword() {
        return this.password;
    }
}
