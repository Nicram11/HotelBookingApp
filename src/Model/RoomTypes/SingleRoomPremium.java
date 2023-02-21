package Model.RoomTypes;

import Model.Guest;
import Model.Reservation;

public class SingleRoomPremium implements IRoom {

    @Override
    public void chargeGuest(Reservation reservation, int days) {
        reservation.charge = 500*days;
    }

    @Override
    public String toString() {
        return "Single Room+";
    }
}
