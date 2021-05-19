package hu.otpmobil.common.security;

public class TechnicalUser {

    private String username;
    private String password;

    public TechnicalUser() {
    }

    public TechnicalUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
