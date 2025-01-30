package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model;

import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.struct.SalesStatus;

public class BreadListPageModel {
    private int id;
    private String breadName;
    private int breadPrice;
    private String breadDescription;
    private SalesStatus salesStatus;
    private String breadImage;

    public BreadListPageModel() {
    }

    public BreadListPageModel(int id, String breadName, int breadPrice, String breadDescription, SalesStatus salesStatus, String breadImage) {
        this.id = id;
        this.breadName = breadName;
        this.breadPrice = breadPrice;
        this.breadDescription = breadDescription;
        this.salesStatus = salesStatus;
        this.breadImage = breadImage;
    }

    public SalesStatus getSalesStatus() {
        return salesStatus;
    }

    public void setSalesStatus(SalesStatus salesStatus) {
        this.salesStatus = salesStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBreadName() {
        return breadName;
    }

    public void setBreadName(String breadName) {
        this.breadName = breadName;
    }

    public int getBreadPrice() {
        return breadPrice;
    }

    public void setBreadPrice(int breadPrice) {
        this.breadPrice = breadPrice;
    }

    public String getBreadDescription() {
        return breadDescription;
    }

    public void setBreadDescription(String breadDescription) {
        this.breadDescription = breadDescription;
    }

    public String getBreadImage() {
        return breadImage;
    }

    public void setBreadImage(String breadImage) {
        this.breadImage = breadImage;
    }
}
