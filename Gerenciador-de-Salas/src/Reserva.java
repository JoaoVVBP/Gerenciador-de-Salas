import java.time.*;

public class Reserva {

    Sala salaReservada;
    LocalDateTime inicio;
    LocalDateTime fim;

    public Reserva(){ };

    public Reserva(Sala salaReservada, LocalDateTime inicio, LocalDateTime fim) {
        this.salaReservada = salaReservada;
        this.inicio = inicio;
        this.fim = fim;
    }

}