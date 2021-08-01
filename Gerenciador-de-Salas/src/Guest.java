import java.time.*;
import java.util.*;
public class Guest {

    List<LocalDateTime> listDate = new ArrayList<>();
    String e_mail;

    Guest(){}

    public void addTime(LocalDateTime startTime, LocalDateTime endTime) {
        listDate.add(startTime);
        listDate.add(endTime);
    }

}