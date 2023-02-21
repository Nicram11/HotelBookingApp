package Model.RoomTypes;

import Model.Guest;
import Model.Reservation;

public class SingleRoom implements IRoom {

    @Override
    public void chargeGuest(Reservation reservation, int days) {
        reservation.charge = 300*days;
    }

    @Override
    public String toString() {
        return "Single Room";
    }
}