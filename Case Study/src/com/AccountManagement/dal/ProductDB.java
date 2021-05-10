package com.AccountManagement.dal;

import com.AccountManagement.model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class ProductDB {
    ArrayList<Product>  products;
    public static int count = 1;
    public static final String PATH = "list_product.csv";
    public ProductDB(){
        products = new ArrayList<>();
    }

    public void writeFile() throws IOException {
        File file = new File(PATH);
        if (!file.exists()) file.createNewFile();

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        for (Product product : products) {
            bufferedWriter.write(product.toStringCSV2());
        }
        bufferedWriter.close();
    }

    public void readFile() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH));
        String line;
        while ((line = bufferedReader.readLine()) != null){
            String[] arr = line.split(",");
            add(new Product(0,arr[1],Integer.parseInt(arr[2])));
        }
    }

    public void add(Product product){
        product.setId(count++);
        products.add(product);
    }

    public Product findById(int id){
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id){
                return products.get(i);
            }
        }
        return null;
    }

    public void setName(int id, String newName){
        findById(id).setName(newName);
    }

    public void setAmount(int id, int newAmount){
        findById(id).setAmount(newAmount);
    }

    public void printList(){
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }


}
