package jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dto;

import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.struct.SalesStatus;

import java.sql.Timestamp;

public class BakeryItemDTO {
    private int bakeryItemId;
    private String bakeryItemName;
    private int bakeryItemPrice;
    private String bakeryItemDescription;

    private SalesStatus salesStatus;
    private String bakeryItemImage;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public BakeryItemDTO() {
    }

    public BakeryItemDTO(int bakeryItemId, String bakeryItemName, int bakeryItemPrice, String bakeryItemDescription, String bakeryItemImage, Timestamp createdAt, SalesStatus salesStatus, Timestamp updatedAt) {
        this.bakeryItemId = bakeryItemId;
        this.bakeryItemName = bakeryItemName;
        this.bakeryItemPrice = bakeryItemPrice;
        this.bakeryItemDescription = bakeryItemDescription;
        this.salesStatus = salesStatus;
        this.bakeryItemImage = bakeryItemImage;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public SalesStatus getSalesStatus() {
        return salesStatus;
    }

    public void setSalesStatus(SalesStatus salesStatus) {
        this.salesStatus = salesStatus;
    }

    public int getBakeryItemId() {
        return bakeryItemId;
    }

    public void setBakeryItemId(int bakeryItemId) {
        this.bakeryItemId = bakeryItemId;
    }

    public String getBakeryItemName() {
        return bakeryItemName;
    }

    public void setBakeryItemName(String bakeryItemName) {
        this.bakeryItemName = bakeryItemName;
    }

    public int getBakeryItemPrice() {
        return bakeryItemPrice;
    }

    public void setBakeryItemPrice(int bakeryItemPrice) {
        this.bakeryItemPrice = bakeryItemPrice;
    }

    public String getBakeryItemDescription() {
        return bakeryItemDescription;
    }

    public void setBakeryItemDescription(String bakeryItemDescription) {
        this.bakeryItemDescription = bakeryItemDescription;
    }

    public String getBakeryItemImage() {
        return bakeryItemImage;
    }

    public void setBakeryItemImage(String bakeryItemImage) {
        this.bakeryItemImage = bakeryItemImage;
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
