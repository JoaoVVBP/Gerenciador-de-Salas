import java.time.*;

public class Participante extends Usuario {

  Participante(){ }

  Participante(String id, String email){
    this.email = email;
    this.id = id;
  }

  @Override
  public void adionaHorario(LocalDateTime horarioInicial, LocalDateTime horarioFinal) {
    dataLista.add(horarioInicial);
    dataLista.add(horarioFinal);
  }

  @Override
  public void exibirHorarios(){
    for (int i = 0; i < dataLista.size(); i++) {
      System.out.println("Index: "+i+" Elemento: "+dataLista.get(i));
    }
  }
}