package com.AccountManagement.presentation;

import com.AccountManagement.model.Product;
import com.AccountManagement.model.User;
import com.AccountManagement.service.ProductService;
import com.AccountManagement.service.UserService;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        System.out.println("Exit system!");
    }

    public static void login() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username");
        String username = new Scanner(System.in).nextLine();
        System.out.println("Enter password: ");
        String password = new Scanner(System.in).nextLine();
        if (!userService.userDB.stringUserHashMap.containsKey(username)) {
            System.out.println("This username don't exist, please check your username again!");
        } else if (!userService.findByUsername(username).getPassword().equals(password)){
            System.out.println("Wrong password, please check your password again!");
        } else {
            if (username.equals("admin")){
                System.out.println("------------ADMIN SYSTEM------------");
                String choice = "";
                while (!choice.equals("5")){
                    System.out.println("\t 1. Change password.");
                    System.out.println("\t 2. Display product list.");
                    System.out.println("\t 3. Add product.");
                    System.out.println("\t 4. Set product amount.");
                    System.out.println("\t 5. Log out.");
                    System.out.println("Enter your choice: ");
                    choice = new Scanner(System.in).nextLine();
                    switch (choice){
                        case "1" -> {
                            System.out.println("Enter new password: ");
                            String newPassword = new Scanner(System.in).nextLine();
                            while (!checkPassword(newPassword)){
                                System.out.println("Invalid password, please enter password again!");
                                newPassword = new Scanner(System.in).nextLine();
                            }
                            while (newPassword.equals(password)) {
                                System.out.println("Please enter new password!!");
                                newPassword = new Scanner(System.in).nextLine();
                                while (!checkPassword(newPassword)){
                                    System.out.println("Invalid password, please enter password again!");
                                    newPassword = new Scanner(System.in).nextLine();
                                }
                            }
                            userService.setPassword(username, newPassword);
                        }
                        case "2" -> {
                            productService.printList();
                        }
                        case "3" -> {
                            System.out.println("Enter new product name: ");
                            String newProductName = new Scanner(System.in).nextLine();
                            System.out.println("Enter new product amount: ");
                            int newAmount;
                            do {
                                System.out.println("Please enter amount: ");
                                while (!scanner.hasNextInt()) {
                                    System.out.println("Invalid number! Please enter number: ");
                                    scanner.next();
                                }
                                newAmount = scanner.nextInt();
                            } while (newAmount <= 0);
                            productService.add(new Product(0, newProductName, newAmount));
                        }
                        case "4" -> {
                            int id;
                            do {
                                System.out.println("Please enter id: ");
                                while (!scanner.hasNextInt()) {
                                    System.out.println("Invalid number! Please enter your number: ");
                                    scanner.next();
                                }
                                id = scanner.nextInt();
                            } while (id <= 0);

                            int newAmount2;
                            do {
                                System.out.println("Please enter amount: ");
                                while (!scanner.hasNextInt()) {
                                    System.out.println("Invalid number! Please enter number: ");
                                    scanner.next();
                                }
                                newAmount2 = scanner.nextInt();
                            } while (newAmount2 <= 0);
                            productService.setAmount(id, newAmount2);
                        }
                    }
                }
            } else {
                System.out.println("Welcome " + userService.findByUsername(username).getUsername()+"!");
                System.out.println("__________________________________");
                String choice = "";
                while (!choice.equals("6")){
                    System.out.println("\t 1. Change password.");
                    System.out.println("\t 2. Edit information.");
                    System.out.println("\t 3. Display product list.");
                    System.out.println("\t 4. Buy item.");
                    System.out.println("\t 5. Display history.");
                    System.out.println("\t 6. Log out.");
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
                                        while (!checkDoB(newDob)){
                                            System.out.println("Invalid birthday, please enter birthday again!");
                                            newDob = new Scanner(System.in).nextLine();
                                        }
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
                                        while (!checkEmail(newEmail)){
                                            System.out.println("Invalid email, please enter email again!");
                                            newEmail = new Scanner(System.in).nextLine();
                                        }
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
                            int buyId;
                            do {
                                System.out.println("Enter id of item you want buy: ");
                                while (!scanner.hasNextInt()) {
                                    System.out.println("Invalid number! Please enter your number: ");
                                    scanner.next();
                                }
                                buyId = scanner.nextInt();
                            } while (buyId <= 0);

                            int buyAmount;
                            do {
                                System.out.println("Enter amount of product: ");
                                while (!scanner.hasNextInt()) {
                                    System.out.println("Invalid number! Please enter your number: ");
                                    scanner.next();
                                }
                                buyAmount = scanner.nextInt();
                            } while (buyAmount <= 0);
                            if  (buyAmount > productService.findById(buyId).getAmount()) {
                                System.out.println("Out of stock!");
                            } else {
                                userService.addHistory(username, new Product(buyId, productService.findById(buyId).getName(), buyAmount));
                                productService.setAmount(buyId, productService.findById(buyId).getAmount() - buyAmount);
                                System.out.println("Transaction completed.");
                            }
                        }
                        case "5" -> {
                            userService.printHistory(username);
                        }
                    }
                }
            }



        }
    }

    public static void register() throws IOException {
        System.out.println("Enter username: ");
        String username = new Scanner(System.in).nextLine();
        while (!checkUserName(username)){
            System.out.println("Invalid username, please enter username again!");
            username = new Scanner(System.in).nextLine();
        }
        while (userService.findByUsername(username) != null){
            System.out.println("Username already exist. Please enter new username: ");
            username = new Scanner(System.in).nextLine();
            while (!checkUserName(username)){
                System.out.println("Invalid username, please enter username again!");
                username = new Scanner(System.in).nextLine();
            }
        }
        System.out.println("Enter password: ");
        String password = new Scanner(System.in).nextLine();
        while (!checkPassword(password)){
            System.out.println("Invalid password, please enter password again!");
            password = new Scanner(System.in).nextLine();
        }
        System.out.println("Enter birthday: ");
        String dob = new Scanner(System.in).nextLine();
        while (!checkDoB(dob)){
            System.out.println("Invalid birthday, please enter birthday again!");
            dob = new Scanner(System.in).nextLine();
        }
        System.out.println("Enter gender: ");
        String gender = new Scanner(System.in).nextLine();
        System.out.println("Enter email: ");
        String email = new Scanner(System.in).nextLine();
        while (!checkEmail(email)){
            System.out.println("Invalid email, please enter your email again!!");
            email = new Scanner(System.in).nextLine();
        }
        System.out.println("Enter address: ");
        String address = new Scanner(System.in).nextLine();
        userService.add(new User(username,password,dob,gender,email,address));
    }

    public static boolean checkEmail(String email){
        String regex = "^[a-zA-Z]+[a-zA-Z0-9]*@\\w+mail.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean checkDoB(String dob){
        String regex = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dob);
        return matcher.matches();
    }

    public static boolean checkUserName(String username){
        String regex = "^[a-zA-Z0-9._]{4,16}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    public static boolean checkPassword(String password){
        String regex = "^[a-zA-Z0-9._]{4,16}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
