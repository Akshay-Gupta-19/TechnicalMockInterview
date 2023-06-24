/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PhohePayWallet;

/**
 *
 * @author Akshay Gupta
 */
public class Wallet {
    private double amount;
    void addMoney(double moneyToAdd){
        amount+=moneyToAdd;
    }
    void deductMoney(double moneyToDeduct){
        amount-=moneyToDeduct;
    }
    double getAmount(){
        return amount;
    }
}
