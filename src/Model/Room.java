package Model;

import Model.RoomTypes.IRoom;
import com.github.lgooddatepicker.optionalusertools.DateInterval;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Room {

    public static ArrayList<Room> rooms = new ArrayList<Room>();
    public ArrayList<Reservation> reservations = new ArrayList<Reservation>();
    public IRoom roomType;
    public int roomId = 0;

    public Room(IRoom roomType){
        this.roomType = roomType;
        roomId = rooms.size();
        rooms.add(this);
    }


    public void chargeGuest(Reservation reservation, int days)
    {
        roomType.chargeGuest(reservation, days);
    }

    private Reservation nextCheckIn(LocalDate selectedDate){
        Reservation helper = reservations.get(0);
        Boolean helperCheckInisBeforeSelectedDate = helper.checkIn.isBefore(selectedDate) ? true : false;
        for(Reservation r : reservations)
        {
            if(r.checkIn.isAfter(selectedDate) && (r.checkIn.isBefore(helper.checkIn) || helperCheckInisBeforeSelectedDate))
            {
                helper = r;
            }
        }

        if(helper.checkIn.isBefore(selectedDate))
            return null;

    return helper;
    }

    private Reservation lastCheckIn(LocalDate selectedDate){
        Reservation helper = reservations.get(0);
        Boolean helperCheckInisAfterSelectedDate = !helper.checkIn.isAfter(selectedDate) ? false : true;
        Boolean helperIsDefault = true;
        for(Reservation r : reservations)
        {

            if(!r.checkIn.isAfter(selectedDate) && (!r.checkIn.isBefore(helper.checkIn) || helperCheckInisAfterSelectedDate))
            {
                if(!helper.equals(r))
                    helperIsDefault = false;
                helper = r;
            }
        }
        if(helperIsDefault && helperCheckInisAfterSelectedDate)
            return null;

        return helper;
    }

    public Object[] CheckStatus(LocalDate selectedDate) {

        Object[] ret = new Object[2];
        if(reservations.isEmpty())
            return new Object[]{"Room Free", null};
        Reservation reservation = lastCheckIn(selectedDate);

        if(reservation == null || !(reservation.checkOut.isAfter(selectedDate)))
        {
            ret[0] = "Room Free";
            ret[1] = nextCheckIn(selectedDate);
        }
        else {
            ret[0] = "Room  Occupied";
            ret[1] = reservation;
        }

        return ret;
    }

    public boolean isFree(LocalDate checkIn, LocalDate checkOut){

        for(Reservation reservation : reservations){
            if(! (!checkIn.isBefore(reservation.checkOut) || !checkOut.isAfter(reservation.checkIn) )) return false;
        }
        return true;
    }
    public boolean isFree(LocalDate checkIn, LocalDate checkOut, Reservation excludedReservation){

        for(Reservation reservation : reservations){
            if(!reservation.equals(excludedReservation))
                if(! (checkIn.isAfter(reservation.checkOut) || checkOut.isBefore(reservation.checkIn) )) return false;
        }
        return true;
    }
}
