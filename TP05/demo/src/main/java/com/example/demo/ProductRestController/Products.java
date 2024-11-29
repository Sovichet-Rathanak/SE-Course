package com.example.demo.ProductRestController;

public class Products {
    private String product_name;
    private String product_code;
    private String OG_country;
    private double price;
    private double cost;
    private String image;
    private String description;
    
    public Products(String product_name, String product_code, String OG_country, double price, double cost, String image, String description) {
        this.product_name = product_name;
        this.product_code = product_code;
        this.OG_country = OG_country;
        this.price = price;
        this.cost = cost;
        this.image = image;
        this.description = description;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getOG_country() {
        return OG_country;
    }

    public void setOG_country(String oG_country) {
        OG_country = oG_country;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
