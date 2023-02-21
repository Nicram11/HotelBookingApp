package Model;

import Model.Guest;

import java.time.LocalDate;
import java.util.Date;

import static Model.Room.rooms;

public class Reservation {
    private int id;
    public Guest guest;
    public LocalDate checkIn;
    public LocalDate checkOut;
    public int charge;

    public Reservation( int roomId,LocalDate checkIn, LocalDate checkOut, String firstName, String secondName, String phoneNumber) {
        this.guest = new Guest(firstName, secondName, phoneNumber);
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        rooms.get(roomId).reservations.add(this);

    }



}