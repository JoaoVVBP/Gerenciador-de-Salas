import java.time.*;
import java.util.LinkedList;
import java.util.List;

public class Disponibilidade{
  String email;
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
}