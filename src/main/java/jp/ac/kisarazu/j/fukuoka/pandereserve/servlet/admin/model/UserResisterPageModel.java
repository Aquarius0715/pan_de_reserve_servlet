package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model;

public class UserResisterPageModel {
    private String username;
    private String password;
    private String confirmPassword;

    public UserResisterPageModel() {
    }

    public UserResisterPageModel(String username, String password, String confirmPassword) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
