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
public class Transaction {
    String sourceAccountNumber;
    String desAccountNumber;
    double money;
    Date date;

    public Transaction(String sourceAccountNumber, String desAccountNumber, double money, Date date) {
        this.sourceAccountNumber = sourceAccountNumber;
        this.desAccountNumber = desAccountNumber;
        this.money = money;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" + "sourceAccountNumber=" + sourceAccountNumber + ", desAccountNumber=" + desAccountNumber + ", money=" + money + ", date=" + date + '}';
    }
    
}
