package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model;

public class BreadAppendPageModel {
    private String breadName;
    private int breadPrice;
    private String breadDescription;
    private String breadImage;

    public BreadAppendPageModel() {
    }

    public BreadAppendPageModel(String breadName, int breadPrice, String breadDescription, String breadImage) {
        this.breadName = breadName;
        this.breadPrice = breadPrice;
        this.breadDescription = breadDescription;
        this.breadImage = breadImage;
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
