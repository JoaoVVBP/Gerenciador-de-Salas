import java.util.*;
import java.time.*;

public class TestesMarcadorDeReuniao {

    public static MarcadorDeReuniao marcadorDaReuniaoDaDiretoria = new MarcadorDeReuniao();
    public static MarcadorDeReuniao marcadorDaReuniaoDaGalera = new MarcadorDeReuniao();
    public static LocalDate  dia15mes4de2004 =  LocalDate.of(2004, 4, 15);
    public static LocalDate  dia16mes4de2004 =  LocalDate.of(2004, 4, 16);
    public static LocalDate  dia17mes4de2004 =  LocalDate.of(2004, 4, 17);
    public static LocalDate  dia18mes4de2004 =  LocalDate.of(2004, 4, 18);
    public static LocalDate  dia19mes4de2004 =  LocalDate.of(2004, 4, 19);
    public static LocalDate  dia20mes4de2004 =  LocalDate.of(2004, 4, 20);
    public static LocalTime as8horas = LocalTime.of(8, 0);
    public static LocalTime as10horas = LocalTime.of(10, 0);
    public static LocalTime aoMeioDia = LocalTime.of(12, 0);
    public static LocalTime as14horas = LocalTime.of(14, 0);
    public static LocalTime as16horas = LocalTime.of(16, 0);
    public static LocalTime as18horas = LocalTime.of(18, 0);
    public static LocalTime as20horas = LocalTime.of(20, 0);
    public static LocalTime aMeiaNoite = LocalTime.of(0, 0);
    public static LocalTime as2horasDaManha = LocalTime.of(2, 0);
    public static String ale = "AlexandreFreireDaSilva@ime.usp.br";
    public static String kon = "FabioKon@ime.usp.br";
    public static String joao = "joao@correio.br";
    public static String jose = "jose@correio.br";
    public static String maria = "maria@correio.br";
    public static String ana = "ana@correio.br";

    public static void main(String[] args) {
        List<String> listaDeParticipantesDaDiretoria = List.of(ale, kon);
        List<String> listaDeParticipantesDaGalera = List.of(ale, kon, joao, jose, maria, ana);
        List<String> listaDeParticipantesDaGalera2 = List.of(ale, kon, maria);
        marcadorDaReuniaoDaDiretoria.marcarReuniaoEntre(dia16mes4de2004, dia19mes4de2004, listaDeParticipantesDaDiretoria);
        marcadorDaReuniaoDaGalera.marcarReuniaoEntre(dia15mes4de2004, dia20mes4de2004, listaDeParticipantesDaGalera);
        case1();
        marcadorDaReuniaoDaDiretoria = new MarcadorDeReuniao();
        marcadorDaReuniaoDaDiretoria.marcarReuniaoEntre(dia16mes4de2004, dia19mes4de2004, listaDeParticipantesDaDiretoria);
        case2();
        marcadorDaReuniaoDaDiretoria = new MarcadorDeReuniao();
        marcadorDaReuniaoDaDiretoria.marcarReuniaoEntre(dia16mes4de2004, dia19mes4de2004, listaDeParticipantesDaDiretoria);
        case3();
        marcadorDaReuniaoDaDiretoria = new MarcadorDeReuniao();
        marcadorDaReuniaoDaDiretoria.marcarReuniaoEntre(dia16mes4de2004, dia19mes4de2004, listaDeParticipantesDaDiretoria);
        case4();
        marcadorDaReuniaoDaDiretoria = new MarcadorDeReuniao();
        marcadorDaReuniaoDaDiretoria.marcarReuniaoEntre(dia16mes4de2004, dia19mes4de2004, listaDeParticipantesDaDiretoria);
        case5();
        marcadorDaReuniaoDaDiretoria = new MarcadorDeReuniao();
        marcadorDaReuniaoDaDiretoria.marcarReuniaoEntre(dia16mes4de2004, dia19mes4de2004, listaDeParticipantesDaDiretoria);
        case6();
        marcadorDaReuniaoDaGalera = new MarcadorDeReuniao();
        marcadorDaReuniaoDaGalera.marcarReuniaoEntre(dia15mes4de2004, dia20mes4de2004, listaDeParticipantesDaGalera2);
        case7();
    }

    public static void case1() { //Teste de disponibilidade

        try {
            System.out.println("----------------------------- Teste 1 -----------------------------");
            marcadorDaReuniaoDaDiretoria.indicaDisponibilidadeDe(ale, LocalDateTime.of(dia18mes4de2004, LocalTime.of(3, 30)), LocalDateTime.of(dia18mes4de2004, LocalTime.of(4, 27)));
            marcadorDaReuniaoDaDiretoria.indicaDisponibilidadeDe(kon, LocalDateTime.of(dia18mes4de2004, LocalTime.of(3, 46)), LocalDateTime.of(dia18mes4de2004, LocalTime.of(5, 30)));
            marcadorDaReuniaoDaDiretoria.mostraSobreposicao();
        }
        catch(Throwable e){
            e.printStackTrace();
        }
    }

    public static void case2() { //testAdicionaDisponibilidadeComComecoAntesDoInicio

        System.out.println("----------------------------- Teste 2 -----------------------------"); //

        try {
            marcadorDaReuniaoDaDiretoria.indicaDisponibilidadeDe(ale, LocalDateTime.of(dia15mes4de2004, LocalTime.of(3, 30)), LocalDateTime.of(dia18mes4de2004, LocalTime.of(4, 27)));
        }
        catch(Throwable e){
            e.printStackTrace();

        }
    }

    public static void case3() { //testAdicionaDisponibilidadeComFimAntesDoComeco


        System.out.println("----------------------------- Teste 3 -----------------------------");

        try {
            marcadorDaReuniaoDaDiretoria.indicaDisponibilidadeDe(ale, LocalDateTime.of(dia18mes4de2004, LocalTime.of(3, 30)), LocalDateTime.of(dia15mes4de2004, LocalTime.of(4, 27)));
        }
        catch(Throwable e){
            e.printStackTrace();
        }
    }

    public static void case4(){ //testAdicionaDisponibilidadeDeParticipanteQueNaoExiste

        System.out.println("----------------------------- Teste 4 -----------------------------");

        try {

            marcadorDaReuniaoDaDiretoria.indicaDisponibilidadeDe("desconhecido", LocalDateTime.of(dia18mes4de2004, LocalTime.of(3, 30)), LocalDateTime.of(dia18mes4de2004, LocalTime.of(4, 27)));
        }
        catch(Throwable e){
            e.printStackTrace();
        }
    }
    public static void case5(){ //testAdicionaDisponibilidadeDepoisDoFim
        System.out.println("----------------------------- Teste 5 -----------------------------");
        try {
            marcadorDaReuniaoDaDiretoria.indicaDisponibilidadeDe(ale, LocalDateTime.of(dia20mes4de2004, LocalTime.of(3, 30)), LocalDateTime.of(dia20mes4de2004, LocalTime.of(4, 27)));

        }
        catch(Throwable e){
            e.printStackTrace();
        }
    }

    public static void case6(){ //testSobreposicaoComplexa

        try{
            System.out.println("----------------------------- Teste 6 -----------------------------");
            marcadorDaReuniaoDaGalera.indicaDisponibilidadeDe(ale, LocalDateTime.of(dia15mes4de2004, as14horas), LocalDateTime.of(dia15mes4de2004, as18horas));
            marcadorDaReuniaoDaGalera.indicaDisponibilidadeDe(ale, LocalDateTime.of(dia16mes4de2004, as14horas), LocalDateTime.of(dia16mes4de2004, as18horas));
            marcadorDaReuniaoDaGalera.indicaDisponibilidadeDe(ale, LocalDateTime.of(dia17mes4de2004, as14horas), LocalDateTime.of(dia17mes4de2004, as18horas));
            marcadorDaReuniaoDaGalera.indicaDisponibilidadeDe(ale, LocalDateTime.of(dia18mes4de2004, as14horas), LocalDateTime.of(dia18mes4de2004, as18horas));
            marcadorDaReuniaoDaGalera.indicaDisponibilidadeDe(ale, LocalDateTime.of(dia19mes4de2004, as14horas), LocalDateTime.of(dia19mes4de2004, as18horas));
            marcadorDaReuniaoDaGalera.indicaDisponibilidadeDe(ale, LocalDateTime.of(dia20mes4de2004, as14horas), LocalDateTime.of(dia20mes4de2004, as18horas));
            marcadorDaReuniaoDaGalera.indicaDisponibilidadeDe(kon, LocalDateTime.of(dia15mes4de2004, as8horas), LocalDateTime.of(dia18mes4de2004, as20horas));
            marcadorDaReuniaoDaGalera.indicaDisponibilidadeDe(joao, LocalDateTime.of(dia15mes4de2004, as2horasDaManha), LocalDateTime.of(dia16mes4de2004, as20horas));
            marcadorDaReuniaoDaGalera.indicaDisponibilidadeDe(joao, LocalDateTime.of(dia18mes4de2004, aMeiaNoite), LocalDateTime.of(dia19mes4de2004, as2horasDaManha));
            marcadorDaReuniaoDaGalera.indicaDisponibilidadeDe(joao, LocalDateTime.of(dia20mes4de2004, as10horas), LocalDateTime.of(dia20mes4de2004, as14horas));
            marcadorDaReuniaoDaGalera.indicaDisponibilidadeDe(jose, LocalDateTime.of(dia15mes4de2004, aoMeioDia), LocalDateTime.of(dia15mes4de2004, as16horas));
            marcadorDaReuniaoDaGalera.indicaDisponibilidadeDe(jose, LocalDateTime.of(dia16mes4de2004, aoMeioDia), LocalDateTime.of(dia16mes4de2004, as16horas));
            marcadorDaReuniaoDaGalera.indicaDisponibilidadeDe(jose, LocalDateTime.of(dia17mes4de2004, aoMeioDia), LocalDateTime.of(dia17mes4de2004, as16horas));
            marcadorDaReuniaoDaGalera.indicaDisponibilidadeDe(jose, LocalDateTime.of(dia18mes4de2004, aoMeioDia), LocalDateTime.of(dia18mes4de2004, as16horas));
            marcadorDaReuniaoDaGalera.indicaDisponibilidadeDe(maria, LocalDateTime.of(dia17mes4de2004, as8horas), LocalDateTime.of(dia17mes4de2004, as18horas));
            marcadorDaReuniaoDaGalera.indicaDisponibilidadeDe(maria, LocalDateTime.of(dia18mes4de2004, as8horas), LocalDateTime.of(dia18mes4de2004, as18horas));
            marcadorDaReuniaoDaGalera.indicaDisponibilidadeDe(maria, LocalDateTime.of(dia19mes4de2004, as8horas), LocalDateTime.of(dia19mes4de2004, as18horas));
            marcadorDaReuniaoDaGalera.indicaDisponibilidadeDe(maria, LocalDateTime.of(dia20mes4de2004, as10horas), LocalDateTime.of(dia20mes4de2004, as14horas));
            marcadorDaReuniaoDaGalera.indicaDisponibilidadeDe(ana, LocalDateTime.of(dia15mes4de2004, as8horas), LocalDateTime.of(dia20mes4de2004, as2horasDaManha));
            marcadorDaReuniaoDaGalera.mostraSobreposicao();
        }
        catch(Throwable e){
            e.printStackTrace();
        }
    }

    public static void case7() { // testSobreposiçãoVazia

        System.out.println("----------------------------- Teste 7 -----------------------------");

        try {
            marcadorDaReuniaoDaGalera.indicaDisponibilidadeDe(ale, LocalDateTime.of(dia18mes4de2004, aoMeioDia), LocalDateTime.of(dia18mes4de2004, as16horas));
            marcadorDaReuniaoDaGalera.indicaDisponibilidadeDe(kon, LocalDateTime.of(dia18mes4de2004, aoMeioDia), LocalDateTime.of(dia18mes4de2004, as14horas));
            marcadorDaReuniaoDaGalera.indicaDisponibilidadeDe(maria, LocalDateTime.of(dia18mes4de2004, as14horas), LocalDateTime.of(dia18mes4de2004, as16horas));
            marcadorDaReuniaoDaGalera.mostraSobreposicao();
        }
        catch(Throwable e){
            e.printStackTrace();

        }
    }
}
			