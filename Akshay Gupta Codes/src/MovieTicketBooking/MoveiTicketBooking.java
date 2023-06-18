/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MovieTicketBooking;

import java.util.*;

/**
 *
 * @author Akshay Gupta
 */
public class MoveiTicketBooking {

}
interface BookingApp{
    void addMovieToTheater(Theater theater,Movie movie);
    void addMovieShow(Theater theater, Movie movie);
    List<Movie> showMovies(Time start,Time end);
    Movie searchMovie(String movie);
    List<MovieShow> getShows(Movie movie);
    List<Seat> showSeatsh(MovieShow show);
    PaymentRequest makePayment(MovieShow show,Seat seats);
    Booking bookSeats(List<Seat> seats,Payment payment);
    void CancleBooking(Booking booking);
}
class Payment{
    int amount;
    String paymentId;
    Person payment;
}
class PaymentRequest{
    int amount;
    String paymentAddress;
}
abstract class Person{
    String name;
    String address;
    
}
interface Theater {

    void addMovieShow(Hall hall,MovieShow show);

    List<MovieShow> getShows(Time from, Time to);

    List<MovieShow> getShows(Movie movie);
    
    void removeShow() throws ShowDoesntExist;

}

record Hall(Theater theater,int number,int capacity){
}

abstract class MovieShow {

    Movie movie;
    Time start, end;
    Collection<Seat> seats;
    Collection<Booking> bookings;
    Hall hall;
    abstract void bookSeat(List<Seat> seats) throws SeatsNotAvailable;

    abstract void CacnelBooking(Booking booking) throws InvalidBooking;

    abstract List<Seat> getAvailableSeats();

    abstract Time getStartTime();

    abstract Time getEndTime();

    abstract Movie getMovie();
}

record Seat(int row, int col) {

}

record Booking(List<Seat> seats) {

}

class SeatsNotAvailable extends Exception {
}

class InvalidBooking extends Exception {
}

class ShowDoesntExist extends Exception {
}
class Time {

    int date, month, year;
    int hour, min, sec;
}

class Movie {

    String name;
    int length;
}
