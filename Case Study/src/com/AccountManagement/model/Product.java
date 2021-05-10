package com.AccountManagement.model;

public class Product {
    int id;
    String name;
    int amount;

    public Product() {
    }

    public int getAmount() {
        return amount;
    }

    public Product(int id, String name, int amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + ", amount =" + amount + '\'' +
                '}';
    }

    public String toStringCSV(){
        return id + "," +
                name + "," +
                amount + ",";
    }
    public String toStringCSV2(){
        return id + "," +
                name + "," +
                amount + "\n";
    }
}
