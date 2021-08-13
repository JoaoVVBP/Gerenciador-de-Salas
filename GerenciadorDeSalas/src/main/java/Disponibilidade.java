import java.time.*;
import java.util.LinkedList;
import java.util.List;

public class Disponibilidade{//Alterei participante para Disponibilidade, removi a maioria das classes e metodos auxiliares ja que nunca foram usados nos testes.
  String email;
  LocalDateTime inicio;
  LocalDateTime fim;

  LocalDate inicioReuniao;
  LocalDate fimReuniao;

  List<LocalDateTime> dataLista = new LinkedList<>();

  Disponibilidade(){}

  Disponibilidade(String email){
    this.email = email;
  }

  Disponibilidade(String email, LocalDate inicioReuniao, LocalDate fimReuniao){
    this.email = email;
    this.inicioReuniao = inicioReuniao;
    this.fimReuniao = fimReuniao;
  }
  //Construtor padrão de Disponibilidade

  //Método para adicionar horários (Nao cheguei a usar dentro do codigo, mas parece mais pratico)
  public void adicionaHorario(LocalDateTime horarioInicial, LocalDateTime horarioFinal) {
    dataLista.add(horarioInicial);
    dataLista.add(horarioFinal);
  }

  //Método para exibir horários (Nao lembro se eh util)
  public void exibirHorarios(){
    for (int i = 0; i < dataLista.size(); i++) {
      System.out.println(email+" "+i+" Elemento: "+dataLista.get(i));
    }
  }
}