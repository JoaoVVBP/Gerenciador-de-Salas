import java.time.*;
import java.util.*;

abstract class Usuario {
    String email;
  
    List<LocalDateTime> dataLista = new LinkedList<>();

    public void adicionaHorario(LocalDateTime horarioInicial, LocalDateTime horarioFinal) {}
    
    public void exibirHorarios(){}

}
