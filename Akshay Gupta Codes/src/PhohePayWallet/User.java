/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PhohePayWallet;

import java.util.*;

/**
 *
 * @author Akshay Gupta
 */
public class User {
    private String name;
    private Address address;
    private String publicAccountNumber;
    private Integer uniqueUserId;
    private Wallet userWallet;
    private List<Transaction> transactions;
    
    public User(String name, Address address, String publicAccountNumber) {
        this.name = name;
        this.address = address;
        this.publicAccountNumber = publicAccountNumber;
        this.uniqueUserId = IdGenerator.getId();
        this.transactions=new ArrayList<>();
        this.userWallet = new Wallet();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPublicAccountNumber() {
        return publicAccountNumber;
    }

    public int getUniqueUserId() {
        return uniqueUserId;
    }

    public Wallet getWallet(){
        return userWallet;
    }
    
    public void addTransaction(Transaction transactionToAdd){
        transactions.add(transactionToAdd);
    }
    
    public List<Transaction> getTransactions(){
        return transactions;
    }
    
}
