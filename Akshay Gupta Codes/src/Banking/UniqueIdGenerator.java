/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Banking;

/**
 *
 * @author Akshay Gupta
 */
public class UniqueIdGenerator {
    static int id=0;
    static synchronized Integer getUniqueId(){
        return ++id;
    }
}
