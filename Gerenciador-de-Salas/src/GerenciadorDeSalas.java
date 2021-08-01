import java.util.*;
import java.time.*;
import java.time.format.*;

public class GerenciadorDeSalas {

    List<Reserve> ReservesList = new ArrayList<>();    
    static List<Room> roomsList = new ArrayList<>();
    Reserve reserved = new Reserve();

    public void adicionaSalaChamada(String name, int maxCapacity, String description) {
        Room room = new Room(name,maxCapacity,description);
        adicionaSala(room);
    }

    public static void removeSalaChamada(String nomeDaSala){
        int i=0;
        while(roomsList.size() > i) {
            if(roomsList.get(i).roomsName.equals(nomeDaSala)){
                roomsList.remove(roomsList.get(i));
            }
            i++;
        }
    }

    public List<Room> roomsList(){
        return roomsList;
    }

    public void adicionaSala(Room newRoom){
        roomsList.add(newRoom);
    }

    public Reserve reservaSalaChamada(String roomsName, LocalDateTime startDate, LocalDateTime endDate) throws Exception{
        Reserve reserved = new Reserve();
        int i = 0;
        while(i < roomsList.size()) {
            if(roomsList.get(i).roomsName.equals(roomsName)){
                if(verifyOverLays(roomsList.get(i), startDate, endDate)==1){
                    throw new Exception("Não foi possível agendar a sala");
                }
                else {
                    reserved = new Reserve(roomsList().get(i), startDate, endDate);
                    ReservesList.add(reserved);
                }
            }
            i++;
        }
        return reserved;
    }

    public void cancelaReserva(Reserve canceled){
        ReservesList.remove(canceled);
    }

    public Collection<Reserve> reservasParaSala(String roomsName){
        return ReservesList;
    }

    public void imprimeReservasDaSala(String roomsName){
        int i = 0;
        while(i < ReservesList.size()) {
            if(ReservesList.get(i).reservedRoom.roomsName.equals(roomsName)){
                DateTimeFormatter dateFormatted = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime endTime = ReservesList.get(i).end;
                LocalDateTime startTime = ReservesList.get(i).start;
                String startFormattedTime = startTime.format(dateFormatted);
                String endFormattedTime = endTime.format(dateFormatted);
                System.out.println("Número da reserva: " + i);
                System.out.println("Sala: " + ReservesList.get(i).reservedRoom.roomsName);
                System.out.println("Início da reunião: " + startFormattedTime);
                System.out.println("Fim da reunião: " + endFormattedTime);
            }
            i++;
        }
    }

    public int verifyOverLays(Room room, LocalDateTime startDate, LocalDateTime endDate){
        int i = 0;
        while ( i < ReservesList.size()) {
            if(ReservesList.get(i).reservedRoom == room){
                if(startDate.isBefore(ReservesList.get(i).start) && endDate.isAfter(ReservesList.get(i).end)) return 1;   
                if(startDate.isAfter(ReservesList.get(i).start) && endDate.isBefore(ReservesList.get(i).end)) return 1;   
                if(startDate.isBefore(ReservesList.get(i).start) && endDate.isAfter(ReservesList.get(i).start)) return 1; 
                if(startDate.isBefore(ReservesList.get(i).end) && endDate.isAfter(ReservesList.get(i).end)) return 1;
            }         
            i++;
        } 
        return 0;
    }

}