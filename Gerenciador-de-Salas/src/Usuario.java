import java.time.*;
import java.util.*;

abstract class Usuario {
    String email;
    String id;
  
    List<LocalDateTime> dataLista = new LinkedList<>();

    public void adionaHorario(LocalDateTime horarioInicial, LocalDateTime horarioFinal) {}
    
    public void exibirHorarios(){}

}
