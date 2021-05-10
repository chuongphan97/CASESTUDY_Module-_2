package com.AccountManagement.model;

import java.util.ArrayList;

public class User {
    String username;
    String password;
    String dob;
    String gender;
    String email;
    String address;
    ArrayList<Product> history;
    public User(){
        history = new ArrayList<>();
    }

    public User(String username, String password, String dob, String gender, String email, String address){
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.history = new ArrayList<>();
    }

    public User(String username, String password, String dob, String gender, String email, String address, ArrayList<Product> history) {
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.history = history;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Product> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<Product> history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String toStringCSV(){
        return username + "," +
                password + "," +
                dob + "," +
                gender + "," +
                email + "," +
                address + "," +
                historyToStringCSV() + "\n";
    }

    public String historyToStringCSV(){
        String result = "";
        for (Product product : history) {
            result += product.toStringCSV();
        }
        return result;
    }
}
