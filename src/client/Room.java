package client;

import database.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Henrik on 24.02.2015.
 */
public class Room {

    int roomID;
    Database db;
    int seats;
    String sql;
    String roomName;

    public Room() {

    }

    public Room(int roomID, int seats, String roomName ){
        setRoomID(roomID);
        setSeats(seats);
        this.roomName = roomName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setRoomID(int roomID){
        this.roomID = roomID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setSeats(int seats){
        this.seats = seats;
    }

    public int getSeats() {
        return seats;
    }




//Henter ut et rom fra databasen og oppretter det som et objekt

    public void createRoom(Room room){

            db = new Database("all_s_gruppe40_calendar");
            db.connectDb("all_s_gruppe40", "qwerty");
            sql = ("INSERT INTO room values(" + room.roomID + "," + room.seats  +  ", '" + room.roomName + "');");
            db.updateQuery(sql);
            db.closeConnection();


    }


    public Room getRoom(int roomID) {
        try {
            db = new Database("all_s_gruppe40_calendar");
            db.connectDb("all_s_gruppe40", "qwerty");
            sql = "SELECT * FROM room where roomID = " + String.valueOf(roomID) + ";";
            ResultSet rs = db.readQuery(sql);
            while(rs.next()) {
                this.roomID = roomID;
                this.seats = rs.getInt("size");
                this.roomName = rs.getString("roomName");
                }
            db.closeConnection();
            rs.close();


            }
        catch (SQLException e){

            }

        return this;


    }

    // naa er det saann at for aa oppdatere maa alt legges inn - bor kanskje gjores lettere?
    public void updateRoom(int roomID, int seats, String roomName){
        db = new Database("all_s_gruppe40_calendar");
        db.connectDb("all_s_gruppe40", "qwerty");
        sql = "UPDATE room, set size = " + String.valueOf(seats) + ", set roomName = " + roomName + ", where roomID = " + String.valueOf(roomID) + ";";
        db.updateQuery(sql);
        db.closeConnection();

    }

}