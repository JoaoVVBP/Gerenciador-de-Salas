import java.util.*;
import java.time.*;
import java.time.format.*;

public class GerenciadorDeSalas {

    List<Reserva> ReservesList = new ArrayList<>();    
    static List<Sala> roomsList = new ArrayList<>();
    Reserva reserved = new Reserva();

    public void adicionaSalaChamada(String name, int maxCapacity, String description) {
        Sala room = new Sala(name,maxCapacity,description);
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

    public List<Sala> roomsList(){
        return roomsList;
    }

    public void adicionaSala(Sala newRoom){
        roomsList.add(newRoom);
    }

    public Reserva reservaSalaChamada(String roomsName, LocalDateTime startDate, LocalDateTime endDate) throws Exception{
        Reserva reserved = new Reserva();
        int i = 0;
        while(i < roomsList.size()) {
            if(roomsList.get(i).roomsName.equals(roomsName)){
                if(verifyOverLays(roomsList.get(i), startDate, endDate)==1){
                    throw new Exception("Não foi possível agendar a sala");
                }
                else {
                    reserved = new Reserva(roomsList().get(i), startDate, endDate);
                    ReservesList.add(reserved);
                }
            }
            i++;
        }
        return reserved;
    }

    public void cancelaReserva(Reserva canceled){
        ReservesList.remove(canceled);
    }

    public Collection<Reserva> reservasParaSala(String roomsName){
        return ReservesList;
    }

    public void imprimeReservasDaSala(String roomsName){
        int i = 0;
        while(i < ReservesList.size()) {
            if(ReservesList.get(i).salaReservada.roomsName.equals(roomsName)){
                DateTimeFormatter dateFormatted = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime endTime = ReservesList.get(i).fim;
                LocalDateTime startTime = ReservesList.get(i).inicio;
                String startFormattedTime = startTime.format(dateFormatted);
                String endFormattedTime = endTime.format(dateFormatted);
                System.out.println("Número da reserva: " + (i + 1));
                System.out.println("Sala: " + ReservesList.get(i).salaReservada.roomsName);
                System.out.println("Início da reunião: " + startFormattedTime);
                System.out.println("Fim da reunião: " + endFormattedTime);
            }
            i++;
        }
    }

    public int verifyOverLays(Sala room, LocalDateTime startDate, LocalDateTime endDate){
        int i = 0;
        while ( i < ReservesList.size()) {
            if(ReservesList.get(i).salaReservada == room){
                if(startDate.isBefore(ReservesList.get(i).inicio) && endDate.isAfter(ReservesList.get(i).fim)) return 1;   
                if(startDate.isAfter(ReservesList.get(i).inicio) && endDate.isBefore(ReservesList.get(i).fim)) return 1;   
                if(startDate.isBefore(ReservesList.get(i).inicio) && endDate.isAfter(ReservesList.get(i).inicio)) return 1; 
                if(startDate.isBefore(ReservesList.get(i).fim) && endDate.isAfter(ReservesList.get(i).fim)) return 1;
            }         
            i++;
        } 
        return 0;
    }

}