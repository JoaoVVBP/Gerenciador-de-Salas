import java.time.*;

public class Reserva {

    Sala salaReservada;
    LocalDateTime inicio;
    LocalDateTime fim;

    public Reserva(){}

    public Reserva(LocalDateTime inicio, LocalDateTime fim){
        this.inicio = inicio;
        this.fim = fim;
    }

    public Reserva(Sala reservedRoom, LocalDateTime inicio, LocalDateTime fim) {
        this.salaReservada = reservedRoom;
        this.inicio = inicio;
        this.fim = fim;
    }

}