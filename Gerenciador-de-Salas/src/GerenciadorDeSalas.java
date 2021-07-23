import java.util.*;
import java.time.*;

public class GerenciadorDeSalas {

    //Tudo isso so pros returns nao peidarem
    List<Reserva> reservas1 = new LinkedList<>();    
    List<Sala> list1 = new LinkedList<>();
    Reserva res1 = new Reserva();

    public void adicionaSalaChamada(String nome, int capacidadeMaxima, String descricao) {
        
    }
    public void removeSalaChamada(String nomeDaSala){

    }
    public List<Sala> listaDeSalas(){
        
        return list1;
    }
    public void adicionaSala(Sala novaSala){

    }
    public Reserva reservaSalaChamada(String nomeDaSala, LocalDateTime dataInicial, LocalDateTime dataFinal){
        return res1;
    }
    public void cancelaReserva(Reserva cancelada){

    }
    public Collection<Reserva> reservasParaSala(String nomeSala){
        return reservas1;
    }
    public void imprimeReservasDaSala(String nomeSala){

    }

}