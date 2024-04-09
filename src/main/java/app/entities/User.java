package app.entities;

public class User {
    public User(int userId, String username, String password, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    private int userId;
    private String username;

    public void setBalance(int balance) {
        this.balance = balance;
    }

    private String password;
    private String role;
    private int balance;

    public int getBalance() {
        return balance;
    }

    public User(int userId, String username, String password, String role, int balance) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.balance = balance;
    }
}
