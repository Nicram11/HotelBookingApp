package Model.DataSeeding;
import Model.Reservation;
import Model.Room;
import Model.RoomTypes.*;

import java.time.LocalDate;


public class Seeder {

    public static void seed()
    {
        seedRooms();
        seedReservations();
    }
    private static void seedRooms()
    {
        //public Reservation( int roomId,Date checkIn, Date checkOut, String firstName, String secondName, String phoneNumber)
        new Room(new SingleRoom());
        new Room(new SingleRoom());
        new Room(new DoubleRoom());
        new Room(new SingleRoomPremium());
        new Room(new DoubleRoom());
        new Room(new DoubleRoomPremium());
    }

    private static void seedReservations()
    {
       /* new Reservation(0, LocalDate.of(2022, 12,26), LocalDate.of(2022, 12,29),
                "Stefan", "Czeresniak", "635-556-416");
        new Reservation(1, LocalDate.of(2022, 12,24), LocalDate.of(2022, 12,25),
                "Stefan", "trzec", "561-556-157");
        new Reservation(1, LocalDate.of(2022, 12,29), LocalDate.of(2022, 12,30),
                "Stefan", "trzec", "561-556-157");
        new Reservation(2, LocalDate.of(2022, 12,22), LocalDate.of(2022, 12,27),
                "Stefan", "drugi", "163-764-333");
        new Reservation(3, LocalDate.of(2022, 12,25), LocalDate.of(2022, 12,26),
                "Stefan", "Analfa", "994-122-433");
        new Reservation(4, LocalDate.of(2022, 12,26), LocalDate.of(2022, 12,29),
                "Stefan", "Kowal", "666-300-111");
        new Reservation(5, LocalDate.of(2022, 12,26), LocalDate.of(2022, 12,30),
                "Stefan", "Miecz", "122-322-332");
        new Reservation(2, LocalDate.of(2022, 12,10), LocalDate.of(2022, 12,20),
                "Stefan", "Miecz", "122-322-332");
        new Reservation(2, LocalDate.of(2022, 12,28), LocalDate.of(2022, 12,31),
                "Piotr", "Szymkowski", "122-322-332");
*/
        Json.ReadDataFromJson();

    }
}
