package com.AccountManagement.presentation;

import com.AccountManagement.model.Product;
import com.AccountManagement.model.User;
import com.AccountManagement.service.ProductService;
import com.AccountManagement.service.UserService;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static ProductService productService = new ProductService();
    public static UserService userService = new UserService();

    public static void main(String[] args) {
        try {
            productService.loadFile();
            userService.loadFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("User Management System.");
        String choice = "";
        while (!choice.equals("3")){
            System.out.println("\t 1. Login.");
            System.out.println("\t 2. Register.");
            System.out.println("\t 3. Exit.");
            System.out.println("Enter your choice: ");
            choice = scanner.nextLine();
            switch (choice){
                case "1":
                    try {
                        login();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "2":
                    try {
                        register();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
    }

    public static void login() throws IOException {
        System.out.println("Enter username");
        String username = new Scanner(System.in).nextLine();
        System.out.println("Enter password: ");
        String password = new Scanner(System.in).nextLine();
        if (!userService.findByUsername(username).getPassword().equals(password)){
            System.out.println("Wrong password, please check your password again!");
        } else {
            System.out.println("Welcome " + userService.findByUsername(username).getUsername()+"!");
            System.out.println("__________________________________");
            String choice = "";
            while (!choice.equals("8")){
                System.out.println("\t 1. Change password.");
                System.out.println("\t 2. Edit information.");
                System.out.println("\t 3. Display product list.");
                System.out.println("\t 4. Add product.");
                System.out.println("\t 5. Set product amount.");
                System.out.println("\t 6. Buy item.");
                System.out.println("\t 7. Display history.");
                System.out.println("\t 8. Log out.");
                System.out.println("Enter your choice: ");
                choice = new Scanner(System.in).nextLine();
                switch (choice) {
                    case "1" -> {
                        System.out.println("Enter new password: ");
                        String newPassword = new Scanner(System.in).nextLine();
                        while (newPassword.equals(password)) {
                            System.out.println("Please enter new password!!");
                            newPassword = new Scanner(System.in).nextLine();
                        }
                        userService.setPassword(username, newPassword);
                    }
                    case "2" -> {
                        String choice2 = "";
                        while (!choice2.equals("5")) {
                            System.out.println("Edit system: ");
                            System.out.println("\t 1. Edit birthday.");
                            System.out.println("\t 2. Edit gender.");
                            System.out.println("\t 3. Edit email.");
                            System.out.println("\t 4. Edit address.");
                            System.out.println("\t 5. Exit!");
                            System.out.println("Enter your choice: ");
                            choice2 = new Scanner(System.in).nextLine();
                            switch (choice2) {
                                case "1" -> {
                                    System.out.println("Enter new birthday: ");
                                    String newDob = new Scanner(System.in).nextLine();
                                    userService.setDob(username, newDob);
                                }
                                case "2" -> {
                                    System.out.println("Enter new gender: ");
                                    String newGender = new Scanner(System.in).nextLine();
                                    userService.setGender(username, newGender);
                                }
                                case "3" -> {
                                    System.out.println("Enter new email: ");
                                    String newEmail = new Scanner(System.in).nextLine();
                                    userService.setEmail(username, newEmail);
                                }
                                case "4" -> {
                                    System.out.println("Enter new address:");
                                    String newAddress = new Scanner(System.in).nextLine();
                                    userService.setAddress(username, newAddress);
                                }
                            }
                        }
                    }
                    case "3" -> productService.printList();
                    case "4" -> {
                        System.out.println("Enter new product name: ");
                        String newProductName = new Scanner(System.in).nextLine();
                        System.out.println("Enter new product amount: ");
                        int newAmount = new Scanner(System.in).nextInt();
                        productService.add(new Product(0, newProductName, newAmount));
                    }
                    case "5" -> {
                        System.out.println("Enter id: ");
                        int id = new Scanner(System.in).nextInt();
                        System.out.println("Enter new amount");
                        int newAmount2 = new Scanner(System.in).nextInt();
                        productService.setAmount(id, newAmount2);
                    }
                    case "6" -> {
                        System.out.println("Enter id: ");
                        int buyId = new Scanner(System.in).nextInt();
                        System.out.println("Enter amount: ");
                        int buyAmount = new Scanner(System.in).nextInt();
                        userService.addHistory(username, new Product(buyId, productService.findById(buyId).getName(), buyAmount));
                        productService.setAmount(buyId, productService.findById(buyId).getAmount() - buyAmount);
                    }
                    case "7" -> {
                        userService.printHistory(username);
                    }
                }
            }

        }
    }

    public static void register() throws IOException {
        System.out.println("Enter username: ");
        String username = new Scanner(System.in).nextLine();
        while (userService.findByUsername(username) != null){
            System.out.println("Username already exist. Please enter new username: ");
            username = new Scanner(System.in).nextLine();
        }
        System.out.println("Enter password: ");
        String password = new Scanner(System.in).nextLine();
        System.out.println("Enter birthday: ");
        String dob = new Scanner(System.in).nextLine();
        System.out.println("Enter gender: ");
        String gender = new Scanner(System.in).nextLine();
        System.out.println("Enter email: ");
        String email = new Scanner(System.in).nextLine();
        System.out.println("Enter address: ");
        String address = new Scanner(System.in).nextLine();
        userService.add(new User(username,password,dob,gender,email,address));
    }

}
