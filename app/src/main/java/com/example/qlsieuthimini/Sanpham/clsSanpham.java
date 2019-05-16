package com.example.qlsieuthimini.Sanpham;

public class clsSanpham {
    private int ID;
    private String Name;
    private int Amount;
    private byte[] Img;

    public clsSanpham(int ID, String name, int amount, byte[] img) {
        this.ID = ID;
        Name = name;
        Amount = amount;
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

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public byte[] getImg() {
        return Img;
    }

    public void setImg(byte[] img) {
        Img = img;
    }
}
