package com.AccountManagement.dal;

import com.AccountManagement.model.Product;
import com.AccountManagement.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserDB {
    private static final String PATH = "user.csv";
    HashMap<String, User> stringUserHashMap;
    public UserDB(){
        stringUserHashMap = new HashMap<>();
    }

    public void writeFile() throws IOException {
        File file = new File(PATH);
        if (!file.exists()) file.createNewFile();

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        for (Map.Entry<String, User> entry : stringUserHashMap.entrySet()){
            bufferedWriter.write(entry.getValue().toStringCSV());
        }
        bufferedWriter.close();
    }

    public void add(User user){
        stringUserHashMap.put(user.getUsername(),user);
    }

    public void readFile() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH));
        String line;
        while ((line = bufferedReader.readLine()) != null){
            String[] arr = line.split(",");
            ArrayList<Product> products = new ArrayList<>();
            for (int i = 0; i < (arr.length - 6)/3; i++) {
                products.add(new Product(Integer.parseInt(arr[i*3+6]),arr[i*3+7],Integer.parseInt(arr[i*3+8])));
            }

            add(new User(arr[0],arr[1],arr[2],arr[3], arr[4],arr[5],products));
        }
        bufferedReader.close();
    }

    public User findByUsername(String username){
        return stringUserHashMap.get(username);
    }

    public void setPassword(String username, String newPassword){
        findByUsername(username).setPassword(newPassword);
    }

    public void setDob(String username, String newDob){
        findByUsername(username).setDob(newDob);
    }

    public void setGender(String username, String newGender){
        findByUsername(username).setGender(newGender);
    }

    public void setEmail(String username, String newEmail){
        findByUsername(username).setEmail(newEmail);
    }

    public void setAddress(String username, String newAddress){
        findByUsername(username).setAddress(newAddress);
    }

    public void addHistory(String username, Product product){
        findByUsername(username).getHistory().add(product);
    }

    public void printHistory(String username){
        ArrayList<Product> products = findByUsername(username).getHistory();
        for (Product product : products) {
            System.out.println("Name: " + product.getName() + ", amount: " + product.getAmount() + ".");
        }
    }

}
