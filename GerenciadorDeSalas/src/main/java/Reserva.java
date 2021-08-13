import java.time.*;

//Classe para salvar a reserva de uma sala

public class Reserva {

    Sala salaReservada;
    LocalDateTime inicio;
    LocalDateTime fim;

    /*
    Construtor da classe Reserva
    @param salaReservada: sala a ser reservada
    @param inicio: inicio da reunião
    @param fim : fim da reunião
    */
    public Reserva(Sala salaReservada, LocalDateTime inicio, LocalDateTime fim) {
        this.salaReservada = salaReservada;
        this.inicio = inicio;
        this.fim = fim;
    }

    //Contrutor padrão
    public Reserva(){};

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