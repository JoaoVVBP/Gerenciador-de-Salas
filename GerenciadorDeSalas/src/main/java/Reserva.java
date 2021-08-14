import java.time.*;

public class Reserva {

    Sala salaReservada;
    LocalDateTime inicio;
    LocalDateTime fim;

    public Reserva(Sala salaReservada, LocalDateTime inicio, LocalDateTime fim) {
        this.salaReservada = salaReservada;
        this.inicio = inicio;
        this.fim = fim;
    }

    public Reserva(){}

    public Sala getSala() {
        return salaReservada;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }
}