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
public class UserController {
    Map<Integer,User> usersMap;
    Map<String,Integer> publicAccountNumberToUniqueId;
    public UserController() {
        usersMap=new HashMap<>();
        publicAccountNumberToUniqueId = new HashMap<>();
    }
    
    void addUser(User userToAdd){
        usersMap.put(userToAdd.getUniqueUserId(),userToAdd);
        publicAccountNumberToUniqueId.put(userToAdd.getPublicAccountNumber(), userToAdd.getUniqueUserId());
    }
    
    void transferMoney(String sourcePublicAccountNumber,String desPublicAccountNumber,double moneyToTransfer){
        Integer sourceAccountUniqueId = publicAccountNumberToUniqueId.get(sourcePublicAccountNumber);
        User sourceUser = usersMap.get(sourceAccountUniqueId);
        Wallet sourceWallet = sourceUser.getWallet();
        
        Integer desAccountUniqueId = publicAccountNumberToUniqueId.get(desPublicAccountNumber);
        User desUser = usersMap.get(desAccountUniqueId);
        Wallet desWallet = desUser.getWallet();
        
        sourceWallet.deductMoney(moneyToTransfer);
        desWallet.addMoney(moneyToTransfer);
        
        Date currentDate = new Date();
        Transaction transaction = new Transaction(sourcePublicAccountNumber, desPublicAccountNumber, moneyToTransfer, currentDate);
        sourceUser.addTransaction(transaction);
        desUser.addTransaction(transaction);
    }
    
    
}
