/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VendingMachine;

import java.util.*;

/**
 *
 * @author Akshay Gupta
 */
public class VendingMachine {

}

class Product {

    private String name;
    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name;
    }

}

interface MachineActions {

    void chooseProduct(int productKey);

    void addMoney(int money);

    void resetMachine();

    void breakMachine();

    void turnBackOn();
}

interface ProductCollection {

    public Product getProductForKey(int key);

    public void addProduct(int number, Product product);
    
    void showProducts();
}

class Machine implements MachineActions,ProductCollection {

    MachineActions needMoney = new NeedMoney(this);
    MachineActions chooseProduct = new ChooseProduct(this);
    MachineActions outOfService = new OutOfService(this);
    private MachineActions currentState = new ChooseProduct(this);
    Product currentProductChoise;
    
    private Map<Integer, Product> products;

    public Machine( ) {
        this.products = new HashMap<>();
    }

    
    @Override
    public Product getProductForKey(int key) {
        return products.get(key);
    }

    @Override
    public void addProduct(int number, Product product) {
        products.put(number, product);
    }
    
    public void setCurrentState(MachineActions currentState) {
        this.currentState = currentState;
    }

    public void setCurrentProductChoise(Product currentProductChoise) {
        this.currentProductChoise = currentProductChoise;
    }

    public Product getCurrentProductChoise() {
        return currentProductChoise;
    }

    public void chooseProduct(int productKey) {
        currentState.chooseProduct(productKey);
    }

    public void addMoney(int money) {
        currentState.addMoney(money);
    }

    public void resetMachine() {
        currentState.resetMachine();
    }

    public void breakMachine() {
        System.out.println("Machine going out of service");
        this.setCurrentState(outOfService);
    }

    public void turnBackOn() {
        System.out.println("Machine coming back up");
        this.setCurrentState(chooseProduct);
    }

    @Override
    public void showProducts() {
        System.out.println(products);
    }

}

class NeedMoney implements MachineActions {

    Machine current;

    public NeedMoney(Machine current) {
        this.current = current;
    }

    @Override
    public void chooseProduct(int productKey) {
        System.out.println("No no product chossen already insert money now");
    }

    @Override
    public void addMoney(int money) {
        Product currentProduct = current.getCurrentProductChoise();
        if (money < currentProduct.getPrice()) {
            System.out.println("Not enough money resetting machine and giving money back add sufficient money or reset");
            current.setCurrentState(current.needMoney);
        } else if (money == currentProduct.getPrice()) {
            System.out.println("Here's your product " + currentProduct + " enjoy!!!");
            current.setCurrentState(current.chooseProduct);
        } else {
            System.out.println("Here's your product " + currentProduct + " enjoy!!!");
            System.out.println(" Take remaining money back " + (money - currentProduct.getPrice()));
            current.setCurrentState(current.chooseProduct);
        }
    }

    @Override
    public void resetMachine() {
        System.out.println("Resetting your choise, choose again");
        current.setCurrentState(current.chooseProduct);
    }

    @Override
    public void breakMachine() {
        System.out.println("Turning machine off");
        current.setCurrentState(current.outOfService);
    }

    @Override
    public void turnBackOn() {
        System.out.println("Turn machine off");
        current.setCurrentState(current.chooseProduct);
    }

}

class ChooseProduct implements MachineActions {

    Machine current;

    public ChooseProduct(Machine current) {
        this.current = current;
    }

    @Override
    public void addMoney(int money) {
        System.out.println("Choose a product first please");
    }

    @Override
    public void chooseProduct(int productKey) {
        Product product = current.getProductForKey(productKey);
        if (product != null) {
            System.out.println("You choose " + product+" Now AddMoney");
            current.setCurrentProductChoise(product);
            current.setCurrentState(current.needMoney);
        } else {
            System.out.println("Wrong key inserted choose again");
        }
    }

    @Override
    public void resetMachine() {
        System.out.println("Resetting your choise, choose again");
        current.setCurrentState(current.chooseProduct);
    }

    @Override
    public void breakMachine() {
        System.out.println("Turning machine off");
        current.setCurrentState(current.outOfService);
    }

    @Override
    public void turnBackOn() {
        System.out.println("Turn machine on");
        current.setCurrentState(current.chooseProduct);
    }

}

class OutOfService implements MachineActions {

    Machine current;

    public OutOfService(Machine machine) {
        this.current = machine;
    }

    @Override
    public void addMoney(int money) {
        System.out.println("Machine out of service please come back later");
    }

    @Override
    public void chooseProduct(int productKey) {
        System.out.println("Machine out of service please come back later");
    }

    @Override
    public void resetMachine() {
        System.out.println("Machine out of service please come back later");
    }

    @Override
    public void breakMachine() {
        System.out.println("Already machine off");
        current.setCurrentState(current.outOfService);
    }

    @Override
    public void turnBackOn() {
        System.out.println("Turn machine on");
        current.setCurrentState(current.chooseProduct);
    }

}
