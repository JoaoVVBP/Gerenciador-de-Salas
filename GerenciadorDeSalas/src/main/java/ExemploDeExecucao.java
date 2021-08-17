import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ExemploDeExecucao {

    public static String nomeSala = "Catacumbas";
    public static String localSala = "C1983 - 300m Abaixo da Biblioteca";
    public static int capacidadeSala = 13;
    public static String obsDaSala = "Nao acessar sem equipamento de respiracao, niveis mortais de Metano, Excelente projetor";
    public static Sala s1 = new Sala();

    public static String nomeSala2 = "Salinha de RP";
    public static String localSala2 = "Coracao do Brasil";
    public static int capacidadeSala2 = 12000;
    public static String obsDaSala2 = "Computador, Quadro verde";


    public static Sala s2 = new Sala();


    public static GerenciadorDeSalas gerenciadorDeSala = new GerenciadorDeSalas();

    public static MarcadorDeReuniao marcadorDaReuniaoDaDiretoria = new MarcadorDeReuniao();
    public static MarcadorDeReuniao marcadorDaReuniaoDaGalera = new MarcadorDeReuniao();

    public static LocalDate dia15mes4de2004 =  LocalDate.of(2004, 4, 15);
    public static LocalDate  dia16mes4de2004 =  LocalDate.of(2004, 4, 16);
    public static LocalDate  dia18mes4de2004 =  LocalDate.of(2004, 4, 18);
    public static LocalDate  dia19mes4de2004 =  LocalDate.of(2004, 4, 19);
    public static LocalDate  dia20mes4de2004 =  LocalDate.of(2004, 4, 20);

    public static LocalTime aoMeioDia = LocalTime.of(12, 0);
    public static LocalTime as14horas = LocalTime.of(14, 0);
    public static LocalTime as16horas = LocalTime.of(16, 0);


    public static String ivandroide = "ivandroideparabolico@usp.br";
    public static String ovelha = "ovelhacarneiro@usp.br";
    public static String lauragarcia = "lauragarcia@usp.br";
    public static String lobo = "lobo@usp.br";
    public static String carina = "carina@usp.br";
    public static String steven = "steven@usp.br";

    public static void main(String[] args) {
        List<String> listaDeParticipantesDaDiretoria = List.of(ivandroide, ovelha);
        List<String> listaDeParticipantesDaGalera = List.of(ivandroide, ovelha, lauragarcia, lobo, carina, steven);

        s1.setCapacidade(capacidadeSala);
        s1.setNome(nomeSala);
        s1.setLocal(localSala);
        s1.setObservacoes(obsDaSala);

        s2.setCapacidade(capacidadeSala2);
        s2.setNome(nomeSala2);
        s2.setLocal(localSala2);
        s2.setObservacoes(obsDaSala2);

        System.out.println("Adicionando duas salas diferentes: \n");
        try {
            gerenciadorDeSala.adicionaSala(s1);
            gerenciadorDeSala.adicionaSala(s2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Exibindo a lista de salas: \n");

        for (int i = 0; i < gerenciadorDeSala.listaDeSalas().size(); i++) {
            System.out.println("Nome da Sala: "+gerenciadorDeSala.listaDeSalas().get(i).nomeDaSala);
            System.out.println("Capacidade: "+gerenciadorDeSala.listaDeSalas().get(i).capacidadeMaxima);
            System.out.println("Descricao: "+gerenciadorDeSala.listaDeSalas().get(i).descricao);
            System.out.println("Local: "+gerenciadorDeSala.listaDeSalas().get(i).local);
            System.out.println();
        }

        System.out.println("Adicionando salas duplicadas: \n");
        try {
            gerenciadorDeSala.adicionaSala(s1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            gerenciadorDeSala.adicionaSalaChamada(nomeSala2,capacidadeSala2,obsDaSala2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\nRemovendo uma sala: \n");

        try {
            gerenciadorDeSala.removeSalaChamada(nomeSala);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\nExibindo a lista de salas: \n");

        for (int i = 0; i < gerenciadorDeSala.listaDeSalas().size(); i++) {
            System.out.println("Nome da Sala: "+gerenciadorDeSala.listaDeSalas().get(i).nomeDaSala);
            System.out.println("Capacidade: "+gerenciadorDeSala.listaDeSalas().get(i).capacidadeMaxima);
            System.out.println("Descricao: "+gerenciadorDeSala.listaDeSalas().get(i).descricao);
            System.out.println("Local: "+gerenciadorDeSala.listaDeSalas().get(i).local);
            System.out.println();
        }

        System.out.println("\nRemovendo uma sala inexistente: \n");

        try {
            gerenciadorDeSala.removeSalaChamada("Ubatuba");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\nReservando uma sala e exibindo reservas: ");

        try {
            gerenciadorDeSala.reservaSalaChamada(nomeSala2, LocalDateTime.of(2021, 8, 16, 10, 0, 0), LocalDateTime.of(2021, 8, 16, 12, 0, 0));
            gerenciadorDeSala.imprimeReservasDaSala(nomeSala2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\nReservando uma sala com data incorreta: \n");

        try {
            gerenciadorDeSala.reservaSalaChamada(nomeSala2, LocalDateTime.of(2021, 8, 16, 12, 0, 0), LocalDateTime.of(2021, 8, 16, 10, 0, 0));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\nCancelando uma reserva e exibindo reservas: ");

        try {
            gerenciadorDeSala.cancelaReserva(gerenciadorDeSala.listaDeReservas.get(0));
            gerenciadorDeSala.imprimeReservasDaSala(nomeSala2);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\nCancelando uma reserva inexistente: \n");

        try {
            Reserva reserva = new Reserva();
            gerenciadorDeSala.cancelaReserva(reserva);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\nReservando uma sala inexistente: \n");

        try{
            Reserva reserva = gerenciadorDeSala.reservaSalaChamada("Ubatuba",LocalDateTime.of(2021, 8, 16, 10, 0, 0), LocalDateTime.of(2021, 8, 16, 12, 0, 0));
        }
        catch(Throwable e){
            e.printStackTrace();
        }

        System.out.println("\nMarcando reuniao com um grupo de participantes: ");

        try {
            marcadorDaReuniaoDaGalera.marcarReuniaoEntre(dia15mes4de2004, dia20mes4de2004, listaDeParticipantesDaGalera);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\nReuniao (0): ");
        System.out.println("Inicio da Reuniao: "+marcadorDaReuniaoDaGalera.disponibilidades.get(0).inicioReuniao);
        System.out.println("Fim da Reuniao: "+marcadorDaReuniaoDaGalera.disponibilidades.get(0).fimReuniao);

        marcadorDaReuniaoDaDiretoria = new MarcadorDeReuniao();
        try {
            marcadorDaReuniaoDaDiretoria.marcarReuniaoEntre(dia16mes4de2004, dia19mes4de2004, listaDeParticipantesDaDiretoria);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\nIndicando disponibilidade de dois participantes e mostrando sobreposicoes: \n");
        try {
            marcadorDaReuniaoDaDiretoria.indicaDisponibilidadeDe(ivandroide, LocalDateTime.of(dia18mes4de2004, LocalTime.of(3, 30)), LocalDateTime.of(dia18mes4de2004, LocalTime.of(4, 27)));
            marcadorDaReuniaoDaDiretoria.indicaDisponibilidadeDe(ovelha, LocalDateTime.of(dia18mes4de2004, LocalTime.of(3, 46)), LocalDateTime.of(dia18mes4de2004, LocalTime.of(5, 30)));
            marcadorDaReuniaoDaDiretoria.mostraSobreposicao();
        }
        catch(Throwable e){
            e.printStackTrace();
        }

        marcadorDaReuniaoDaDiretoria = new MarcadorDeReuniao();
        try {
            marcadorDaReuniaoDaDiretoria.marcarReuniaoEntre(dia16mes4de2004, dia19mes4de2004, listaDeParticipantesDaDiretoria);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\nIndicando disponibilidade com fim antes do comeco: ");
        try {
            marcadorDaReuniaoDaDiretoria.indicaDisponibilidadeDe(ovelha, LocalDateTime.of(dia18mes4de2004, LocalTime.of(3, 30)), LocalDateTime.of(dia15mes4de2004, LocalTime.of(4, 27)));
        }
        catch(Throwable e){
            e.printStackTrace();
        }

        System.out.println("\nIndicando disponibilidade de participante inexistente: \n");

        try {
            marcadorDaReuniaoDaDiretoria.indicaDisponibilidadeDe("Lagoinha", LocalDateTime.of(dia18mes4de2004, LocalTime.of(3, 30)), LocalDateTime.of(dia18mes4de2004, LocalTime.of(4, 27)));
        }
        catch(Throwable e){
            e.printStackTrace();
        }

        System.out.println("\nAdicionando disponibilidade depois do fim da reuniao: \n");

        try {
            marcadorDaReuniaoDaDiretoria.indicaDisponibilidadeDe(ivandroide, LocalDateTime.of(dia20mes4de2004, LocalTime.of(3, 30)), LocalDateTime.of(dia20mes4de2004, LocalTime.of(4, 27)));
        }
        catch(Throwable e){
            e.printStackTrace();
        }

        System.out.println("\nImprimindo uma sobreposicao vazia: \n");
        try {
            marcadorDaReuniaoDaGalera.indicaDisponibilidadeDe(ivandroide, LocalDateTime.of(dia18mes4de2004, aoMeioDia), LocalDateTime.of(dia18mes4de2004, as16horas));
            marcadorDaReuniaoDaGalera.indicaDisponibilidadeDe(ovelha, LocalDateTime.of(dia18mes4de2004, aoMeioDia), LocalDateTime.of(dia18mes4de2004, as14horas));
            marcadorDaReuniaoDaGalera.indicaDisponibilidadeDe(lauragarcia, LocalDateTime.of(dia18mes4de2004, as14horas), LocalDateTime.of(dia18mes4de2004, as16horas));
            marcadorDaReuniaoDaGalera.mostraSobreposicao();
        }
        catch(Throwable e){
            e.printStackTrace();

        }
    }
}
