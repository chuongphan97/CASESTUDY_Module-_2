package com.AccountManagement.service;

import com.AccountManagement.dal.ProductDB;
import com.AccountManagement.model.Product;

import java.io.IOException;

public class ProductService {
    ProductDB productDB;
    public ProductService(){
        productDB = new ProductDB();
    }

    public void add(Product product) throws IOException {
        productDB.add(product);
        System.out.println("Add product successful.");
        updateFile();
    }

    public Product findById(int id){
        return productDB.findById(id);
    }

    public void setName (int id, String newName) throws IOException {
        productDB.setName(id,newName);
        System.out.println("Set new name successful.");
        updateFile();
    }

    public void setAmount(int id, int newAmount) throws IOException {
        productDB.setAmount(id,newAmount);
        System.out.println("Set new amount successful.");
        updateFile();
    }

    public void updateFile() throws IOException {
        productDB.writeFile();
    }

    public void loadFile() throws IOException {
        productDB.readFile();
    }

    public void printList(){
        System.out.println("Product list: ");
        productDB.printList();
    }
}
