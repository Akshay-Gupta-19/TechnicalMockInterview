/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LiftDesign;
import java.util.*;
/**
 *
 * @author Akshay Gupta
 */
public class Elevator {
    
}
interface ElevatorSystem{
    void makeRequest(Request request);
    Car suitedCar(Request request);
}

interface Car{
    void addRequest(Request request);
}

interface Request{
    
}

class CarRequest implements Request{
    int destinationFloor;
}

class HallRequest implements Request{
    int requetFloor;
    Direction direction;
}

enum Direction{
    UP,
    DOWN
}
enum CarState{
    GOINGUP,
    GOINGDOWN,
    STILL,
    SHUTDOWN,
    EMERGENCY
}


class MicroSoftElevator implements ElevatorSystem{
    Collection<Car> cars;

    @Override
    public void makeRequest(Request request) {
        
    }

    @Override
    public Car suitedCar(Request request) {
        return null;
    }
    
}


class MicrosoftCar implements Car{
    Queue<Request> activeRequests;
    int currentFloor;
    CarState carState;

    @Override
    public void addRequest(Request request) {
     
    }
}
