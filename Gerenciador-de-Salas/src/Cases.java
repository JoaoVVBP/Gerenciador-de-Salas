import java.time.*;
import java.util.*;

public class Cases {
    public static void displayRooms() {
        if (Execute.manager.roomsList().size() == 0) {
            System.out.println("Ainda não há salas, insira salas, para que seja possível exibí-las.");
            return;
        }
        System.out.println();
        System.out.println("################## LISTA DE SALAS ##################");
        int i = 0; 
        while (i < GerenciadorDeSalas.roomsList.size()) {
            System.out.println("Número da sala: " + (i + 1));
            System.out.println("Sala: " + GerenciadorDeSalas.roomsList.get(i).roomsName);
            System.out.println("Número máximo de pessoas: " + GerenciadorDeSalas.roomsList.get(i).maxCapacity);
            System.out.println("Descrição da sala: " + GerenciadorDeSalas.roomsList.get(i).description + " ");
            System.out.println();
            i++;
        }
    }

    public static void DeleteRoom() {
        if (GerenciadorDeSalas.roomsList.size() == 0) {
            System.out.println("Ainda não há salas, insira salas, para que seja possível exibí-las.");
            return;
        }       
        displayRooms();
        System.out.println("Qual o nome da sala que deseja remover?");
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();
        GerenciadorDeSalas.removeSalaChamada(choice);
    }
    public static void createMeeting(Organizer manager, String e_mail) throws Exception {
        if (GerenciadorDeSalas.roomsList.size() == 0) {
            System.out.println("Ainda não há salas, insira salas, para que seja possível exibí-las.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        int dates[] = new int[6];
        MarcadorDeReuniao marcador = new MarcadorDeReuniao();

        System.out.println("\nHaverá quantos participantes?");
        int length = Integer.parseInt(sc.nextLine());
        int i = 0;
        while (i < length) {
            System.out.println("\nQual o e-mail do participante " + (i + 1) + "?");
            e_mail = sc.next();
            if (Execute.e_mails.contains(e_mail)) {
                System.out.println("Esse e-mail já existe!");
                i--;
            } 
            else {
                Execute.e_mails.add(e_mail);
            }
            i++;
        }
        i = 1;
        while (i <= length) {
            Guest guest = new Guest();
            guest.e_mail = Execute.e_mails.get(i);
            Execute.guests.add(guest);
            i++;
        }
        System.out.println("\nEm qual período pretende marcar a reunião? (aaaa mm dd aaaa mm dd)");
        i=0;
        while (i < 6) {
            dates[i] = sc.nextInt();
            i++;
        }

        manager.bounds[0] = java.time.LocalDate.of(dates[0], dates[1], dates[2]);
        manager.bounds[1] = java.time.LocalDate.of(dates[3], dates[4], dates[5]);

        int managerDate[] = new int[10];

        System.out.println("\n" + manager.e_mail + ", quantas disponibilidades de horários você deseja?");
        int sizeDisponibility = sc.nextInt();
        Guest buffer = new Guest();
        int j = 0;
        while (j < sizeDisponibility) {
            System.out.println(manager.e_mail + " digite seu horário disponível " + (j + 1) + " no formato aaaa mm dd hh mm aaaa mm dd hh mm: ");
            int k = 0;
            while (k < 10) {
                managerDate[k] = sc.nextInt();
                k++;
            }
            LocalDateTime startTime = java.time.LocalDateTime.of(managerDate[0], managerDate[1], managerDate[2], managerDate[3], managerDate[4]);
            LocalDateTime endTime = java.time.LocalDateTime.of(managerDate[5], managerDate[6], managerDate[7], managerDate[8], managerDate[9]);
            buffer.addTime(startTime, endTime);
            j++;
        }

        marcador.marcarReuniaoEntre(manager.bounds[0], manager.bounds[1], Execute.e_mails);

        Execute.overLays = buffer;
        i = 1;
        while (i < Execute.guests.size()) {
            Execute.overLays = VerifyOverlays.verifyOverLays(Execute.overLays, Execute.guests.get(i));
            i++;
        }

        Execute.guests.add(Execute.overLays);

        marcador.mostraSobreposicao();

        displayRooms();
        System.out.println("Número de sala que deseja selecionar: ");
        int roomsNumber = sc.nextInt() - 1;
        while (GerenciadorDeSalas.roomsList.get(roomsNumber).maxCapacity < Execute.guests.size()) {
            System.out.println("Número de participantes excedeu o limite da sala.");
            System.out.println("Número de sala que deseja selecionar: ");
            roomsNumber = sc.nextInt();
        }

        if (Execute.overLays.listDate.size() != 1) {
            System.out.println("\nDigite o número do horário de sobreposição que você deseja marcar a reunião: ");
            int guest99 = sc.nextInt();
            guest99 = (guest99 - 1) * 2;
            Execute.manager.reservaSalaChamada(GerenciadorDeSalas.roomsList.get(roomsNumber).roomsName, Execute.overLays.listDate.get(guest99),
            Execute.overLays.listDate.get(guest99 + 1));
        } 
        else {
            Execute.manager.reservaSalaChamada(GerenciadorDeSalas.roomsList.get(roomsNumber).roomsName, Execute.overLays.listDate.get(0),
            Execute.overLays.listDate.get(1));
        }
        System.out.println("\nReunião marcada com sucesso: ");
        Execute.manager.imprimeReservasDaSala(GerenciadorDeSalas.roomsList.get(roomsNumber).roomsName);
    }

    public static void displayReserves() {
        if (Execute.manager.ReservesList.size() == 0) {
            System.out.println("\nNão há nenhuma reserva existente.");
            return;
        } 
        else {
            System.out.println("################## LISTA DE RESERVAS ##################");
        }
        int i = 0;
        while (i < Execute.manager.ReservesList.size()) {
            Execute.manager.imprimeReservasDaSala(Execute.manager.ReservesList.get(i).salaReservada.roomsName);
            i++;
        }
        return;
    }

    public static void removeReserve() {
        if (Execute.manager.ReservesList.size() == 0) {
            System.out.println("\nNão há nenhuma reserva existente.");
            return;
        }
        Scanner sc = new Scanner(System.in);
        displayReserves();
        System.out.println("\nDigite o número da reserva que deseja remover: ");
        int n = Integer.parseInt(sc.nextLine());
        Execute.manager.cancelaReserva(Execute.manager.ReservesList.get(n));

        return;
    }

    public static void insereSala() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nDigite a quantidade de salas: ");
        int quantityRooms = Integer.parseInt(sc.nextLine());
        int i = 0;
        while (i < quantityRooms) {
            Sala sala = new Sala();

            System.out.println("\nDigite o nome da sala " + (i + 1) + ": ");
            sala.roomsName = sc.nextLine();

            System.out.println("\nDigite a capacidade máxima da sala " + (i + 1) + ": ");
            sala.maxCapacity = Integer.parseInt(sc.nextLine());

            System.out.println("\nDigite a descrição da sala " + (i + 1) + ": ");
            sala.description = sc.nextLine();
            int j = 0;
            while (j < GerenciadorDeSalas.roomsList.size() - 1) {
                if (GerenciadorDeSalas.roomsList.get(j).roomsName.equals(sala.roomsName)) {
                    System.out.println("\nEssa sala já existe, tente outro nome.");
                    i--;
                }
                j++;
            }
            GerenciadorDeSalas.roomsList.add(sala);
            i++;
        }
    }
}
