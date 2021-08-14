import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class GerenciadorDeSalas {

    GerenciadorDeSalas() {}

    List<Reserva> listaDeReservas = new LinkedList<>();
    static List<Sala> listaDeSalas = new LinkedList<>();

    public void adicionaSalaChamada(String nome, int capacidadeMaxima, String descricao) throws Exception {
        Sala sala = new Sala(nome, capacidadeMaxima, descricao);
        for (Sala listaDeSala : listaDeSalas) {
            if (listaDeSala.nomeDaSala.equals(nome)) {
                System.out.println("Erro: Verificar exceptions");
                throw new Exception("\nErro: Nao foi possivel adicionar a sala" + "\nMotivo: Sala ja registrada");
            }
        }
        listaDeSalas.add(sala);

    }

    public void adicionaSala(Sala novaSala) throws Exception {
        for (Sala listaDeSala : listaDeSalas) {
            if (listaDeSala.nomeDaSala.equals(novaSala.nomeDaSala)) {
                System.out.println("Erro: Verificar exceptions");
                throw new Exception("\nErro: Nao foi possivel adicionar a sala" + "\nMotivo: Sala ja registrada");
            }
        }
        listaDeSalas.add(novaSala);
    }

    public void removeSalaChamada(String nomeDaSala) throws Exception{

        for (int i = 0; i < listaDeSalas.size(); i++) {
            if (listaDeSalas.get(i).nomeDaSala.equals(nomeDaSala)){
                listaDeSalas.remove(listaDeSalas.get(i));
                return;
            }
        }
        System.out.println("Erro: Verificar exceptions");
        throw new Exception("\nErro: Nao foi possivel remover a sala" + "\nMotivo: Sala nao encontrada");
    }

    public List<Sala> listaDeSalas() {
        return listaDeSalas;
    }

    public Reserva reservaSalaChamada(String nomeDaSala, LocalDateTime dataInicial, LocalDateTime dataFinal) throws Exception {
        Reserva res = new Reserva();
        if (dataFinal.isBefore(dataInicial)){
            System.out.println("Erro: Verificar exceptions");
            throw new Exception("Erro: Não foi possível reservar a sala" + "\n Motivo: Horario final antecede o inicial");
        }

        for (int i = 0; i < listaDeSalas.size(); i++) {
            if (listaDeSalas.get(i).nomeDaSala.equals(nomeDaSala)) {
                if (!verificarSobreposicao(listaDeSalas.get(i), dataInicial, dataFinal)) {
                    res = new Reserva(listaDeSalas().get(i), dataInicial, dataFinal);
                    listaDeReservas.add(res);
                } else {
                    System.out.println("Erro: Verificar exceptions");
                    throw new Exception("Erro: Não foi possível reservar a sala" + "\n Motivo: A sala ja possui reserva neste horario");
                }
            }
            else{
                System.out.println("Erro: Verificar exceptions");
                throw new Exception("Erro: Não foi possível agendar a sala" + "\n Motivo: Sala nao encontrada");
            }
        }
        return res;
    }

    public boolean verificarSobreposicao(Sala sala, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        for (Reserva listaDeReserva : listaDeReservas) {
            if (listaDeReserva.salaReservada == sala) {

                if (dataInicial.isAfter(listaDeReserva.inicio) && dataFinal.isBefore(listaDeReserva.fim)
                        || dataInicial.isBefore(listaDeReserva.inicio) && dataFinal.isAfter(listaDeReserva.fim)
                        || dataInicial.isBefore(listaDeReserva.fim) && dataFinal.isAfter(listaDeReserva.fim)
                        || dataInicial.isBefore(listaDeReserva.inicio) && dataFinal.isAfter(listaDeReserva.inicio)) {
                    return true;
                }

                if (dataInicial.isEqual(listaDeReserva.inicio) && dataFinal.isEqual(listaDeReserva.inicio)
                        || dataInicial.isEqual(listaDeReserva.inicio)
                        || dataInicial.isEqual(listaDeReserva.inicio)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void cancelaReserva(Reserva cancelada) throws Exception {
        for (int i = 0; i < listaDeReservas.size(); i++) {
            if (listaDeReservas.get(i).salaReservada.nomeDaSala.equals(cancelada.salaReservada.nomeDaSala)){
                listaDeReservas.remove(cancelada);
                return;
            }
        }
        System.out.println("Erro: Verificar exceptions");
        throw new Exception("Erro: Não foi possível cancelar a reserva" + "\n Motivo: Reserva inexistente");
    }

    public Collection<Reserva> reservasParaSala(String nomeSala) {
        List<Reserva> reservasSala = new LinkedList<>();
        for (Reserva listaDeReserva : listaDeReservas)
            if (listaDeReserva.salaReservada.nomeDaSala.equals(nomeSala)) {
                reservasSala.add(listaDeReserva);
            }
        return reservasSala;
    }

    public void imprimeReservasDaSala(String nomeSala) {
        System.out.println();
        if (listaDeReservas.size() == 0) System.out.println("Nao existem reservas efetuadas");
        for (int i = 0; i < listaDeReservas.size(); i++) {
            if (listaDeReservas.get(i).salaReservada.nomeDaSala.equals(nomeSala)) {
                LocalDateTime horaInicio = listaDeReservas.get(i).inicio;
                LocalDateTime horaFim = listaDeReservas.get(i).fim;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                String horaFormatadaInicio = horaInicio.format(formatter);
                String horaFormatadaFim = horaFim.format(formatter);
                System.out.println("Reserva (" + i + "): " + "\nNome da Sala: " + listaDeReservas.get(i).salaReservada.nomeDaSala + "\nInicio: " + horaFormatadaInicio + "\nFim: " + horaFormatadaFim);
            }
        }
    }
}