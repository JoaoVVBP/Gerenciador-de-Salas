import java.time.*;

public class Gerenciador extends Usuario {
    LocalDate limites[] = new LocalDate[2];

    Gerenciador() {}

    Gerenciador(String email, String id) {
        this.email = email;
        this.id = id;   
    }

    @Override
    public void adionaHorario(LocalDateTime horarioInicial, LocalDateTime horarioFinal) {
        dataLista.add(horarioInicial);
        dataLista.add(horarioFinal);
    }

    @Override
    public void exibirHorarios() {
        for (int i = 0; i < dataLista.size(); i++) {
            System.out.println("Index: " + i + " Elemento: " + dataLista.get(i));
        }
    }

    public void limitarHorario(LocalDate horarioInicial, LocalDate horarioFinal) {
        limites[0] = horarioInicial;
        limites[1] = horarioFinal;
    }

}