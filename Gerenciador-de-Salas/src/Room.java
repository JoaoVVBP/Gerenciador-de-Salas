public class Room {
    String roomsName;
    String description;
    int maxCapacity;

    public Room(){}

    public Room(String roomsName, int maxCapacity, String description) {
        this.roomsName = roomsName;
        this.description = description;
        this.maxCapacity = maxCapacity;
    }
    
}
