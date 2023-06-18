/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ParkingLot;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Akshay Gupta
 */
public class AdminClient {
    public static void main(String[] args) {
        ParkingLot parkingLot = new MallParkingLot();
        Admin paplu = new MallAdmin(parkingLot,"Paplue");
        try {
            paplu.addParkingSpot(new MallParkingSpot(VechileType.Bike, "First", 0,0));
        } catch (SpotNotFoundException ex) {
            Logger.getLogger(AdminClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
