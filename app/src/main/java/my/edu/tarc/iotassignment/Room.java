
package my.edu.tarc.iotassignment;

public class Room {
    private String roomNo;
    private int noOfPax;
    private boolean status;

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getNoOfPax() {
        return noOfPax;
    }

    public void setNoOfPax(int noOfPax) {
        this.noOfPax = noOfPax;
    }

    public Room() {
    }
/*
    var roomList: List<Room>
    //read from firebase with for loop (reading 1 by 1)
    val room = Room(id, status)
    roomList.add(room)
*/
    //array

}
