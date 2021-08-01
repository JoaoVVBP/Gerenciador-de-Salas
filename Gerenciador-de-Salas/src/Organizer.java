import java.time.*;
import java.util.*;

public class Organizer {

    LocalDate bounds[] = new LocalDate[2];
    String e_mail;
    List<LocalDateTime> listDate = new ArrayList<>();

    Organizer() {}

    Organizer(String e_mail) {
        this.e_mail = e_mail;
    }

    public void addTime(LocalDateTime startTime, LocalDateTime endTime) {
        listDate.add(startTime);
        listDate.add(endTime);
    }

    public void timeLimiter(LocalDate startTime, LocalDate endTime) {
        bounds[0] = startTime;
        bounds[1] = endTime;
    }

}