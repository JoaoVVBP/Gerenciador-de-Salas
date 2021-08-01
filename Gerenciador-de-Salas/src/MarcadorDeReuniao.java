import java.util.*;
import java.time.*;
import java.time.format.*;

public class MarcadorDeReuniao {
    int bufferDates[] = new int[10];
    Scanner sc = new Scanner(System.in);

    public void marcarReuniaoEntre(LocalDate startDate, LocalDate endDate,Collection<String> guestsList) {
        int i = 0;
        while (i < Execute.guests.size()) {
            System.out.println("\n" + "Quantas disponibilidades de horários " + Execute.guests.get(i).e_mail + " deseja?");
            int n = sc.nextInt();
            int j = 0;
            while (j < n) {
                System.out.println("\n" + Execute.guests.get(i).e_mail + ", informe seu horario de disponibilidade " + (j + 1) + " no formato aaaa mm dd hh mn aaaa mm dd hh mn");
                int k = 0; 
                while (k < 10) {
                    bufferDates[k] = sc.nextInt();
                    k++;
                }
                LocalDateTime startTime = java.time.LocalDateTime.of(bufferDates[0], bufferDates[1], bufferDates[2], bufferDates[3], bufferDates[4]);
                LocalDateTime endTime = java.time.LocalDateTime.of(bufferDates[5], bufferDates[6], bufferDates[7], bufferDates[8], bufferDates[9]);
                indicaDisponibilidade(Execute.guests.get(i).e_mail, startTime, endTime);
                j++;
            }
            i++;
        }
    }

    public void indicaDisponibilidade(String guest, LocalDateTime start, LocalDateTime end) {
        int i = 0;
        while (i < Execute.guests.size()) {
            if (Execute.guests.get(i).e_mail.equals(guest)) Execute.guests.get(i).listDate.add(start);
            if (Execute.guests.get(i).e_mail.equals(guest)) Execute.guests.get(i).listDate.add(end);
            i++;
        }
    }

    public void mostraSobreposicao() {
        int k = 1;
        int i = 0;
        while (i < Execute.guests.size()-1) {
            System.out.println();
            System.out.println("Participante: "+Execute.guests.get(i).e_mail);
            System.out.println("Horários de disponibilidade: ");
            int j = 0;
            while(j < Execute.guests.get(i).listDate.size()){
                LocalDateTime time = Execute.guests.get(i).listDate.get(j);
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                String timeFormatted = time.format(timeFormatter);
                if ((j+1)%2 == 0) {
                    System.out.println(timeFormatted);
                    k++;
                }
                else{
                    System.out.print("Horario "+(k)+": "+timeFormatted+" até às ");
                }
                j++;
            }
            k = 1;
            i++;
        }
        System.out.println();
        System.out.println("################## SOBREPOSIÇÕES ##################");
        int j = 0; 
        while (j < Execute.overLays.listDate.size()){
            LocalDateTime time = Execute.overLays.listDate.get(j);
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String timeFormatted = time.format(timeFormatter);
            if ((j+1)%2 == 0) {
                System.out.println(timeFormatted);
                k++;
            }
            else{
                System.out.print("Horário "+(k)+": "+timeFormatted+" até às ");
            }
            j++;
       }
        k = 1;
    }
}