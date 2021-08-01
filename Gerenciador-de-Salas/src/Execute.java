import java.util.*;

public class Execute {
    
    static List<Guest> guests = new LinkedList<>();
    static List<String> e_mails = new LinkedList<>();
    static Guest overLays = new Guest();
    static GerenciadorDeSalas manager = new GerenciadorDeSalas();
    static String e_mail_manager;
    static Organizer organizer = new Organizer();

    public static void addManagerEmail(){
        System.out.println();
        System.out.println("Email do organizador:");
        Scanner sc = new Scanner(System.in);
        String e_mail_manager = sc.nextLine();
        organizer.e_mail = e_mail_manager;
        e_mails.add(e_mail_manager);
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        for (int m = 0; m != 7;) {
            System.out.println("\nSelecione o que deseja fazer:");
            System.out.println("1: Organizar a reunião");
            System.out.println("2: Inserir salas");
            System.out.println("3: Remover reserva");
            System.out.println("4: Imprimir reservas");
            System.out.println("5: Exibir salas");
            System.out.println("6: Remove sala");
            System.out.println("7: Encerra o programa");
            m = sc.nextInt();
            switch (m) {
                case 1:
                    if(organizer.e_mail==null) addManagerEmail();
                    Cases.createMeeting(organizer, e_mail_manager);
                    break;
                case 2:
                    Cases.insereSala();
                    break;
                case 3:
                    Cases.removeReserve();
                    break;
                case 4:
                    Cases.displayReserves();
                    break;
                case 5:
                    Cases.displayRooms();
                    break;
                case 6:
                    Cases.DeleteRoom();
                    break;
                case 7:
                    System.out.println("Aplicação encerrada!!!");
                    sc.close();
                    break;
                default:
                    System.out.println("Insira um número de 1 a 7");
                    break;
            }
        }
        return;
    }
}