package com.AccountManagement.service;

import com.AccountManagement.dal.UserDB;
import com.AccountManagement.model.Product;
import com.AccountManagement.model.User;

import java.io.FileNotFoundException;
import java.io.IOException;

public class UserService {
    public UserDB userDB;
    public UserService(){
        userDB = new UserDB();
    }

    public void updateFile() throws IOException {
        userDB.writeFile();
    }

    public void loadFile() throws IOException {
        userDB.readFile();
    }

    public void add(User user) throws IOException {
        userDB.add(user);
        System.out.println("Register new user successful.");
        updateFile();
    }

    public void setPassword(String username, String newPassword) throws IOException {
        userDB.setPassword(username,newPassword);
        System.out.println("Change password successful.");
        updateFile();
    }

    public User findByUsername(String username){
        return userDB.findByUsername(username);
    }

    public void setDob(String username, String newDob) throws IOException {
        userDB.setDob(username,newDob);
        System.out.println("Change birthday successful.");
        updateFile();
    }

    public void setGender(String username, String newGender) throws IOException {
        userDB.setGender(username,newGender);
        System.out.println("Change gender successful.");
        updateFile();
    }

    public void setEmail(String username, String newEmail) throws IOException {
        userDB.setEmail(username,newEmail);
        System.out.println("Change email successful.");
        updateFile();
    }

    public void setAddress(String username, String newAddress) throws IOException {
        userDB.setAddress(username,newAddress);
        System.out.println("Change address successful.");
        updateFile();
    }

    public void addHistory(String username, Product product) throws IOException {
        userDB.addHistory(username,product);
        updateFile();
    }

    public void printHistory(String username){
        userDB.printHistory(username);
        System.out.println("________________________________");
    }
}
