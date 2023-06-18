/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ParkingLot;

import java.util.*;

/**
 *
 * @author guptaakshay 
 * Requirments 
 * 1. User can request spot 
 * 2. User can request checkout 
 * 3. user can make payment 
 * 4. Receptionist can request a parking spot lowest number will go first // need policy 
 * 5. Receptionist at entry can book the spot and create a ticket 
 *6. Receptionist at exit can close a ticket 
 *7.Admin can add new parking spot 
 *8. Admin can add new entry or exit point
 */
public class Parking {

}

enum VechileType {
    Car,
    Bike,
    Truck,
    Cycle,
    Tank
}

interface SpotCollection<ParkingSpot> {

    ParkingSpot fillFreeSpot(VechileType vechileType) throws NoSpotAvailable;

    void freeSpot(ParkingSpot spotToFree);

    boolean isSpotPresent(ParkingSpot spotToCheck);

    void addSpot(ParkingSpot spotToAdd) throws SpotNotFoundException;

    void removeSpot(ParkingSpot spotToDelete) throws SpotNotFoundException;
}

class MallSpotCollection implements SpotCollection<ParkingSpot> {

    Map<VechileType, Queue<ParkingSpot>> spotCollection;

    public MallSpotCollection() {
        spotCollection = new HashMap<>();
        for (VechileType value : VechileType.values()) {
            spotCollection.put(value, new PriorityQueue<>());
        }
    }

    @Override
    public ParkingSpot fillFreeSpot(VechileType vechileType) throws NoSpotAvailable {
        ParkingSpot firstFree = spotCollection.get(vechileType).poll();
        if (firstFree == null) {
            throw new NoSpotAvailable();
        }
        return firstFree;
    }

    @Override
    public void freeSpot(ParkingSpot spotToFree) {
        spotCollection.get(spotToFree.getVechileType()).add(spotToFree);
    }

    @Override
    public boolean isSpotPresent(ParkingSpot spotToCheck) {
        if (!spotCollection.containsKey(spotToCheck.getVechileType())) {
            return false;
        }
        return spotCollection.get(spotToCheck.getVechileType()).contains(spotToCheck);
    }

    @Override
    public void addSpot(ParkingSpot spotToAdd) throws SpotNotFoundException {
        if (isSpotPresent(spotToAdd)) {
            throw new SpotNotFoundException();
        }
        spotCollection.putIfAbsent(spotToAdd.getVechileType(), new PriorityQueue<>());
        spotCollection.get(spotToAdd.getVechileType()).add(spotToAdd);
    }

    @Override
    public void removeSpot(ParkingSpot spotToDelete) throws SpotNotFoundException {
        if (!isSpotPresent(spotToDelete)) {
            throw new SpotNotFoundException();
        }
        spotCollection.get(spotToDelete.getVechileType()).remove(spotToDelete);
    }
}

class SpotNotFoundException extends Exception {

}

class NoSpotAvailable extends Exception {

}

interface ParkingSpot extends Comparable<ParkingSpot> {

    VechileType getVechileType();

    String getFloor();
}

class MallParkingSpot implements ParkingSpot {

    VechileType vechileType;
    String floor;
    Integer row, column;

    public MallParkingSpot(VechileType vechileType, String floor, Integer row, Integer column) {
        this.vechileType = vechileType;
        this.floor = floor;
        this.row = row;
        this.column = column;
    }

    public VechileType getVechileType() {
        return vechileType;
    }

    @Override
    public String getFloor() {
        return floor;
    }

    @Override
    public int compareTo(ParkingSpot otherSpot) {
        if (!(otherSpot instanceof MallParkingSpot)) {
            return 1;
        }
        MallParkingSpot otherMallParkingSpot = (MallParkingSpot) otherSpot;
        if (!otherSpot.getFloor().equals(this.floor)) {
            return this.getFloor().compareTo(otherMallParkingSpot.getFloor());
        }
        int rowCompare = this.row.compareTo(otherMallParkingSpot.row);
        int colCompare = this.column.compareTo(otherMallParkingSpot.column);
        return rowCompare == 0 ? colCompare : rowCompare;
    }

    @Override
    public String toString() {
        return "MallParkingSpot{" + "vechileType=" + vechileType + ", floor=" + floor + ", row=" + row + ", column=" + column + '}';
    }

}

class Vechile {

    VechileType vechileType;
    String vechileNumber;
    Customer customer;

    public Vechile(VechileType vechileType, String vechileNumber, Customer customer) {
        this.vechileType = vechileType;
        this.vechileNumber = vechileNumber;
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Vechile{" + "vechileType=" + vechileType + ", vechileNumber=" + vechileNumber + ", customer=" + customer + '}';
    }

}

abstract class Person {

    String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" + "name=" + name + '}';
    }

}

class Customer extends Person {

    public Customer(String name) {
        super(name);
    }

}

interface GateWorker {

    Ticket findSpotAndGetTicket(Vechile vechile, double rate) throws NoSpotAvailable;

    Price closeTicket(Ticket ticket);

}

abstract class GenralGateKeeper extends Person implements GateWorker {

    PriceCaclulator priceCaclulator = PriceCaclulator.getCaclculator();

    ParkingLot parkingLot;

    public GenralGateKeeper(ParkingLot parkingLot, String name) {
        super(name);
        this.parkingLot = parkingLot;
    }

    @Override
    public Ticket findSpotAndGetTicket(Vechile vechile, double rate) throws NoSpotAvailable {
        ParkingSpot bookedSpot = parkingLot.getSpotCollection().fillFreeSpot(vechile.vechileType);

        Ticket newTicket = new Ticket(vechile, new Date(), bookedSpot, rate, this);
        System.out.println("Assigning new ticket " + newTicket);
        return newTicket;
    }

    @Override
    public Price closeTicket(Ticket ticket) {
        parkingLot.getSpotCollection().freeSpot(ticket.parkingSpot);
        Price price = priceCaclulator.caclulatePrice(ticket, new Date());
        System.out.println("Closing ticket" + ticket + " Fianl Price " + price);
        return price;
    }
}

class MallGateKeeper extends GenralGateKeeper {

    public MallGateKeeper(ParkingLot parkingLot, String name) {
        super(parkingLot, name);
    }

}

class Price {

    double price;

    public Price(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Price{" + "price=" + price + '}';
    }

}

class PriceCaclulator {

    private PriceCaclulator() {
    }
    private final static PriceCaclulator PRICE_CACLULATOR = new PriceCaclulator();

    static PriceCaclulator getCaclculator() {
        return PRICE_CACLULATOR;
    }

    Price caclulatePrice(Ticket ticket, Date endTime) {
        return new Price((endTime.getTime() - ticket.generated.getTime()) * ticket.parkingRate);
    }
}

interface Admin {

    void addParkingSpot(ParkingSpot parkingSpot) throws SpotNotFoundException;

    void removeParkingSpot(ParkingSpot parkingSpot) throws SpotNotFoundException;
}

abstract class GeneralAdmin extends Person implements Admin {

    ParkingLot parkingLot;

    public GeneralAdmin(ParkingLot parkingLot, String name) {
        super(name);
        this.parkingLot = parkingLot;
    }

    @Override
    public void addParkingSpot(ParkingSpot parkingSpot) throws SpotNotFoundException {
        parkingLot.getSpotCollection().addSpot(parkingSpot);
        System.out.println(name + " Added spot " + parkingSpot);
    }

    @Override
    public void removeParkingSpot(ParkingSpot parkingSpot) throws SpotNotFoundException {
        parkingLot.getSpotCollection().removeSpot(parkingSpot);
    }
}

class MallAdmin extends GeneralAdmin {

    public MallAdmin(ParkingLot parkingLot, String name) {
        super(parkingLot, name);
    }

    @Override
    public void addParkingSpot(ParkingSpot parkingSpot) throws SpotNotFoundException {
        super.addParkingSpot(parkingSpot); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}

class Ticket {

    long ticketId;
    Vechile vechile;
    Date generated;
    double parkingRate;
    ParkingSpot parkingSpot;
    static long nextId = 0;
    GateWorker assignedBy;

    public Ticket(Vechile vechile, Date generated, ParkingSpot parkingSpot, double parkingRate, GateWorker assignedBy) {
        this.ticketId = nextId++;
        this.vechile = vechile;
        this.generated = generated;
        this.parkingSpot = parkingSpot;
        this.parkingRate = parkingRate;
        this.assignedBy = assignedBy;
    }

    @Override
    public String toString() {
        return "Ticket{" + "ticketId=" + ticketId + ", vechile=" + vechile + ", generated=" + generated + ", parkingRate=" + parkingRate + ", parkingSpot=" + parkingSpot + ", assignedBy=" + assignedBy + '}';
    }

}

interface ParkingLot {

    public SpotCollection<ParkingSpot> getSpotCollection();
}

class MallParkingLot implements ParkingLot {

    SpotCollection mallSpotCollection = new MallSpotCollection();

    public MallParkingLot() {

    }

    @Override
    public SpotCollection<ParkingSpot> getSpotCollection() {
        return mallSpotCollection;
    }

}
