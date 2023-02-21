

package Model.DataSeeding;
import Model.Reservation;
import Model.Room;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;


public class Json {

    public static void WriteDataToJson()
    {
        JSONArray jsonArray = new JSONArray();
        ArrayList<Room> array = Room.rooms;


        for (int i = 0;i < array.size() ; i++) {
            JSONArray reservationArrayJson = new JSONArray();
            JSONObject roomJson =  new JSONObject();
            Room room = array.get(i);
            ArrayList<Reservation> reservations = room.reservations;

            roomJson.put("id", room.roomId);
            roomJson.put("roomType",  array.get(i).roomType.toString());

            for( Reservation reservation : reservations) {
                JSONObject reservationJson =  new JSONObject();
                reservationJson.put("checkIn", reservation.checkIn.toString());
                reservationJson.put("checkOut", reservation.checkOut.toString());
                reservationJson.put("name", reservation.guest.name);
                reservationJson.put("surrname", reservation.guest.surname);
                reservationJson.put("phoneNumber", reservation.guest.phoneNumeber);
                reservationArrayJson.add(reservationJson);
            }

            roomJson.put("reservations", reservationArrayJson);

            jsonArray.add(roomJson);
        }


        try (FileWriter file = new FileWriter("rooms.json")) {
            file.write(jsonArray.toString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + jsonArray);
        } catch(Exception e){
            System.out.println(e);

        }
    }

    public static void ReadDataFromJson(){

        JSONParser parser = new JSONParser();
        JSONObject roomJson;
        try (Reader reader = new FileReader("rooms.json")) {

            JSONArray jsonArray1= (JSONArray)parser.parse(reader);
            System.out.println("\n" + jsonArray1);
            String roomType;
            for(int i = 0; i<jsonArray1.size(); i++) {

                roomJson = (JSONObject) jsonArray1.get(i);
                roomType = (String)roomJson.get("roomType");
                JSONArray reservationsJson = new JSONArray();
                reservationsJson = (JSONArray)roomJson.get("reservations");
                for( int j = 0; j<reservationsJson.size(); j++){
                    JSONObject reservationJson = (JSONObject) reservationsJson.get(j);
                    new Reservation(i, LocalDate.parse((String)(reservationJson.get("checkIn"))),
                            LocalDate.parse((String)(reservationJson.get("checkOut"))),
                            (String)reservationJson.get("name"),
                            (String)reservationJson.get("surrname"),
                            (String)reservationJson.get("phoneNumber"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();}
        catch (ParseException e) {
            e.printStackTrace();
        }


    }

}
