package Model.RoomTypes;

import Model.Guest;
import Model.Reservation;

public interface IRoom {
    public void chargeGuest(Reservation guest, int days);
    public String toString();
}
