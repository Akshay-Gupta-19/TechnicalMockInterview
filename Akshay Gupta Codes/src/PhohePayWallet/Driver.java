/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PhohePayWallet;
/**
 *
 * @author Akshay Gupta
 */
public class Driver {
    public static void main(String[] args) {
        Address akshayAddress = new Address("Perl Galxy","Sampath","Bangalore");
        User akshayUser = new User("Akshay", akshayAddress, "123");
        
        Address lokeshAddress = new Address("dusri building","pehli gali","Asgard");
        User lokeshUser = new User("Lokesh", lokeshAddress, "456");
        
        Wallet akshayWallet = akshayUser.getWallet();
        akshayWallet.addMoney(1000);
        
        UserController userController = new UserController();
        userController.addUser(akshayUser);
        userController.addUser(lokeshUser);
        userController.transferMoney("123", "456", 100);
        
        System.out.println("Akshay has after trnasaction"+akshayUser.getWallet().getAmount());
        System.out.println("Akshay transactions "+akshayUser.getTransactions());
        
        System.out.println("Lokesh has after trnasaction"+lokeshUser.getWallet().getAmount());
        System.out.println("Lokesh transaction " +lokeshUser.getTransactions());
        
        akshayUser.getTransactions().get(0).money+=200;
        System.out.println(lokeshUser.getTransactions().get(0));
        
    }
}
