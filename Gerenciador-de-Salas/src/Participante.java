import java.time.*;
import java.util.*;

public class Participante {
  LocalDateTime data;
  List<LocalDateTime> dataLista = new LinkedList<>();
  String email;
  String id;

  Participante(){ }

  Participante(String id, String email){
    this.email = email;
    this.id = id;
  }

  public void adionaHorario(LocalDateTime horarioInicial, LocalDateTime horarioFinal) {
    dataLista.add(horarioInicial);
    dataLista.add(horarioFinal);
  }

  public void exibirHorarios(){
    for (int i = 0; i < dataLista.size(); i++) {
      System.out.println("Index: "+i+" Elemento: "+dataLista.get(i));
    }
  }
}