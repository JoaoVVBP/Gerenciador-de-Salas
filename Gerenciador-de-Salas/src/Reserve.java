import java.time.*;

public class Reserve {

    Room reservedRoom;
    LocalDateTime start;
    LocalDateTime end;

    public Reserve(){ };

    public Reserve(Room reservedRoom, LocalDateTime start, LocalDateTime end) {
        this.reservedRoom = reservedRoom;
        this.start = start;
        this.end = end;
    }

}