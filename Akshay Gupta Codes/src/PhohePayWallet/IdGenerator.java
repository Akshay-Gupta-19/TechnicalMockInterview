/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PhohePayWallet;

/**
 *
 * @author Akshay Gupta
 */
public class IdGenerator {
    static int id;
    synchronized static int getId(){
        return ++id;
    }
}
