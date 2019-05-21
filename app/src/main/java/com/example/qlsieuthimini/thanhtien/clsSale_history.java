package com.example.qlsieuthimini.thanhtien;

public class clsSale_history {
    private int ID;
    private String ProductName;
    private String Amount;
    private String Price;
    private String Time;
    private byte[] imgSale;

    public clsSale_history(int ID, String productName, String amount, String price, String time, byte[] imgSale) {
        this.ID = ID;
        ProductName = productName;
        Amount = amount;
        Price = price;
        Time = time;
        this.imgSale = imgSale;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public byte[] getImgSale() {
        return imgSale;
    }

    public void setImgSale(byte[] imgSale) {
        this.imgSale = imgSale;
    }
}
