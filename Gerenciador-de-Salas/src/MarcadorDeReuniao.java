import java.util.*;
import java.time.*;

public class MarcadorDeReuniao {

    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal, Collection<String> listaDeParticipantes) {
        
    }

    public void indicaDisponibilidadeDe(String participante, LocalDateTime inicio, LocalDateTime fim) {
        for (int i = 0; i < Main.participantes.size(); i++) {
            if(Main.participantes.get(i).id.equals(participante)){
                Main.participantes.get(i).dataLista.add(inicio);
                Main.participantes.get(i).dataLista.add(fim);
            }
        }
    }

    public void mostraSobreposicao() {

    }

}