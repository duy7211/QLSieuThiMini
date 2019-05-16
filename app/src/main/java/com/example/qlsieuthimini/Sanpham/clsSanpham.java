package com.example.qlsieuthimini.Sanpham;

public class clsSanpham {
    private int ID;
    private String Name;
    private String Amount;
    private String Describe;
    private byte[] Img;

    public clsSanpham(int ID, String name, String amount, String describe, byte[] img) {
        this.ID = ID;
        Name = name;
        Amount = amount;
        Describe = describe;
        Img = img;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public byte[] getImg() {
        return Img;
    }

    public void setImg(byte[] img) {
        Img = img;
    }
}
