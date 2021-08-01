import java.time.*;

public class Gerenciador extends Usuario {
    LocalDate limites[] = new LocalDate[2];

    /*
    @param email: email do gerenciador da sala
    */
    Gerenciador(String email) {
        this.email = email;  
    }

    //Construtor padrão
    Gerenciador() {}

    //Método para adicionar horários
    @Override
    public void adicionaHorario(LocalDateTime horarioInicial, LocalDateTime horarioFinal) {
        dataLista.add(horarioInicial);
        dataLista.add(horarioFinal);
    }

    //Método para inserir horários
    @Override
    public void exibirHorarios() {
        for (int i = 0; i < dataLista.size(); i++) {
            System.out.println("Index: " + i + " Elemento: " + dataLista.get(i));
        }
    }

    //Limite de horários
    public void limitarHorario(LocalDate horarioInicial, LocalDate horarioFinal) {
        limites[0] = horarioInicial;
        limites[1] = horarioFinal;
    }

}