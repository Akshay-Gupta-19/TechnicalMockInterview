/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VendingMachine;

import java.util.Scanner;

/**
 *
 * @author Akshay Gupta
 */
public class Client {

    public static void main(String[] args) {
        Machine machine = new Machine();
        Admin admin=new Admin(machine);
        User user = new User(machine);
        Scanner sc=new Scanner(System.in);
        String command;
        do{
            command = sc.next();
            switch(command){
                case "Show":
                    System.out.println("""
                                       AddProduct
                                       TurnOff
                                       TurnOn
                                       ChooseProduct
                                       AddMoney
                                       Reset
                                       Exit
                                       """);
                    break;
                case "ShowProducts":
                    machine.showProducts();
                    break;
                case "AddProduct":
                    System.out.println("Enter product id");
                    int number = sc.nextInt();
                    System.out.println("Enter price");
                    int price = sc.nextInt();
                    System.out.println("Enter name");
                    String product = sc.next();
                    admin.addProduct(number, new Product(product, price));
                    break;
                case "TurnOff":
                    System.out.println("Turning machine off bye");
                    admin.turnOffMachine();
                    break;
                case "TurnOn":
                    System.out.println("Turning back on hello!!!");
                    admin.turnOnMachine();
                    break;
                case "ChooseProduct":
                    System.out.println("Enter id");
                    int id=sc.nextInt();
                    user.chooseProduct(id);
                    break;
                case "AddMoney":
                    System.out.println("Enter money ammount");
                    int money=sc.nextInt();
                    user.addMoney(money);
                    break;
                case "Reset":
                    System.out.println("Resetting");
                    user.resetMachine();
                    break;
                case "Exit":
                    System.out.println("Shutting down...");
                    break;
            }
        }while(!command.equals("Exit"));
    }
}

class User {

    Machine machine;

    public User(Machine machine) {
        this.machine = machine;
    }

    void chooseProduct(int productKey) {
        machine.chooseProduct(productKey);
    }

    void addMoney(int money) {
        machine.addMoney(money);
    }

    void resetMachine() {
        machine.resetMachine();
    }

}

class Admin {

    Machine machine;

    public Admin(Machine machine) {
        this.machine = machine;
    }

    void addProduct(int key, Product product) {
        machine.addProduct(key, product);
    }

    void turnOffMachine() {
        machine.breakMachine();
    }

    void turnOnMachine() {
        machine.turnBackOn();
    }
}
