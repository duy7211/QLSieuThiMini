package com.example.qlsieuthimini.user;

public class clsUser {
    private int ID;
    private String Name;
    private  String Passord;
    private String Hoten;
    private String Que;
    private String NS;

    public clsUser(int ID, String name, String passord, String hoten, String que, String NS) {
        this.ID = ID;
        Name = name;
        Passord = passord;
        Hoten = hoten;
        Que = que;
        this.NS = NS;
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

    public String getPassord() {
        return Passord;
    }

    public void setPassord(String passord) {
        Passord = passord;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String hoten) {
        Hoten = hoten;
    }

    public String getQue() {
        return Que;
    }

    public void setQue(String que) {
        Que = que;
    }

    public String getNS() {
        return NS;
    }

    public void setNS(String NS) {
        this.NS = NS;
    }
}
