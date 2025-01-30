package jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dto;

import java.sql.Timestamp;

public class UserDTO {
    private int id;
    private String username;
    private String password;
    private String salt;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public UserDTO(int id, String username, String password, String salt, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
