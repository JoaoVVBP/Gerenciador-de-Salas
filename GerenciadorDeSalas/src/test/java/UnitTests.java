import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class UnitTests {
    @BeforeAll
    public static MarcadorDeReuniao marcadorDaReuniaoDaDiretoria = new MarcadorDeReuniao();
    public static MarcadorDeReuniao marcadorDaReuniaoDaGalera = new MarcadorDeReuniao();
    public static LocalDate dia15mes4de2004 = LocalDate.of(2004, 4, 15);
    public static LocalDate dia16mes4de2004 = LocalDate.of(2004, 4, 16);
    public static LocalDate dia17mes4de2004 = LocalDate.of(2004, 4, 17);
    public static LocalDate dia18mes4de2004 = LocalDate.of(2004, 4, 18);
    public static LocalDate dia19mes4de2004 = LocalDate.of(2004, 4, 19);
    public static LocalDate dia20mes4de2004 = LocalDate.of(2004, 4, 20);
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

    @Test
    public void teste1() {
        List<String> listaDeParticipantesDaDiretoria = List.of(ale, kon);
        List<String> listaDeParticipantesDaGalera = List.of(ale, kon, joao, jose, maria, ana);

        marcadorDaReuniaoDaDiretoria.marcarReuniaoEntre(dia16mes4de2004, dia19mes4de2004, listaDeParticipantesDaDiretoria);
        marcadorDaReuniaoDaGalera.marcarReuniaoEntre(dia15mes4de2004, dia20mes4de2004, listaDeParticipantesDaGalera);

        System.out.println("----------------------------- Teste 1 -----------------------------");
        marcadorDaReuniaoDaDiretoria.indicaDisponibilidadeDe(ale, LocalDateTime.of(dia18mes4de2004, LocalTime.of(3, 30)), LocalDateTime.of(dia18mes4de2004, LocalTime.of(4, 27)));
        marcadorDaReuniaoDaDiretoria.indicaDisponibilidadeDe(kon, LocalDateTime.of(dia18mes4de2004, LocalTime.of(3, 46)), LocalDateTime.of(dia18mes4de2004, LocalTime.of(5, 30)));
        marcadorDaReuniaoDaDiretoria.mostraSobreposicao();

    }
}
