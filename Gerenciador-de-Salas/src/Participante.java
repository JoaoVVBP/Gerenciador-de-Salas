import java.time.*;

public class Participante extends Usuario {

  /*
    Construtor da classe Participante
    @param email: email do do participante
    */
  Participante(String email){
    this.email = email;
  }

  //Construtor padrão de participante
  Participante(){}

  //Método para adicionar horários
  @Override
  public void adicionaHorario(LocalDateTime horarioInicial, LocalDateTime horarioFinal) {
    dataLista.add(horarioInicial);
    dataLista.add(horarioFinal);
  }

  //Método para inserir horários
  @Override
  public void exibirHorarios(){
    for (int i = 0; i < dataLista.size(); i++) {
      System.out.println("Index: "+i+" Elemento: "+dataLista.get(i));
    }
  }
}