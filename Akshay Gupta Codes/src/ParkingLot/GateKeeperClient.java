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
public class GateKeeperClient {
    public static void main(String[] args) {
           ParkingLot parkingLot = new MallParkingLot();
           MallGateKeeper mallGateKeeper=new MallGateKeeper(parkingLot, "Taplu");
        Ticket findSpotAndGetTicket;
        try {
            findSpotAndGetTicket = mallGateKeeper.findSpotAndGetTicket(new Vechile(VechileType.Bike, "Bk3254", new Customer("Bablu")), 10);
        } catch (NoSpotAvailable ex) {
            Logger.getLogger(GateKeeperClient.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
}
