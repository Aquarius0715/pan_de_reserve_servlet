package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model;

public class LoginPageModel {
    private String username;
    private String password;

    public LoginPageModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginPageModel() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
