import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class GerenciadorDeSalas {

    List<Reserva> listaDeReservas = new LinkedList<>();    
    static List<Sala> listaDeSalas = new LinkedList<>();
    Reserva res1 = new Reserva();

    //recebe o nome da sala, a capacidade máxima da sala e uma descrição, criando uma instancia de sala
    public void adicionaSalaChamada(String nome, int capacidadeMaxima, String descricao) {
        Sala sala = new Sala(nome,capacidadeMaxima,descricao);
        adicionaSala(sala);
    }

    //remove a sala da lista de salas
    public void removeSalaChamada(String nomeDaSala){
        for(int i = 0; i < listaDeSalas.size(); i++) if(listaDeSalas.get(i).nomeDaSala.equals(nomeDaSala)) listaDeSalas.remove(listaDeSalas.get(i));
    }

    //devolve uma instância de List com objetos do tipo Sala
    public List<Sala> listaDeSalas(){ return listaDeSalas; }

    //recebe uma instância de Sala e a adiciona a lista de salas
    public void adicionaSala(Sala novaSala){ listaDeSalas.add(novaSala); }

    //recebe um nome de sala, um LocalDateTime que indica o início da reserva e um outro LocalDateTime para indicar o final da reserva. O método devolve uma instância de Reserva;
    public Reserva reservaSalaChamada(String nomeDaSala, LocalDateTime dataInicial, LocalDateTime dataFinal) throws Exception{
        Reserva res = new Reserva();
        for(int i = 0; i < listaDeSalas.size(); i++) {
            if(listaDeSalas.get(i).nomeDaSala.equals(nomeDaSala)){
                if(!verificarSobreposicao(listaDeSalas.get(i), dataInicial, dataFinal)){
                    res = new Reserva(listaDeSalas().get(i), dataInicial, dataFinal);
                    listaDeReservas.add(res);
                } else throw new Exception("Não foi possível agendar a sala");
            }
        }
        return res;
    }

    //verificar se há sobreposição de reserva de salas
    public boolean verificarSobreposicao(Sala sala, LocalDateTime dataInicial, LocalDateTime dataFinal){
        for (int i = 0; i < listaDeReservas.size(); i++) {
            if(listaDeReservas.get(i).salaReservada == sala){

                //Intervalos diferentes
                if(dataInicial.isAfter(listaDeReservas.get(i).inicio) && dataFinal.isBefore(listaDeReservas.get(i).fim) 
                    || dataInicial.isBefore(listaDeReservas.get(i).inicio) && dataFinal.isAfter(listaDeReservas.get(i).fim)
                        || dataInicial.isBefore(listaDeReservas.get(i).fim) && dataFinal.isAfter(listaDeReservas.get(i).fim)
                            || dataInicial.isBefore(listaDeReservas.get(i).inicio) && dataFinal.isAfter(listaDeReservas.get(i).inicio)){return true;}   
               
                //Intervalos iguais:
                if(dataInicial.isEqual(listaDeReservas.get(i).inicio) && dataFinal.isEqual(listaDeReservas.get(i).inicio) 
                    || dataInicial.isEqual(listaDeReservas.get(i).inicio)  
                        || dataInicial.isEqual(listaDeReservas.get(i).inicio)){return true;} 
            }
        }
        return false;
    }

    public void cancelaReserva(Reserva cancelada){ listaDeReservas.remove(cancelada); }

    //devolve uma lista com todas reservas de nomeSala
    public Collection<Reserva> reservasParaSala(String nomeSala){ 
        List<Reserva> reservasSala= new LinkedList<>();
        for (int i = 0; i < listaDeReservas.size(); i++) 
            if(listaDeReservas.get(i).salaReservada.nomeDaSala == nomeSala) reservasSala.add(listaDeReservas.get(i));
        return reservasSala; 
    }

    public void imprimeReservasDaSala(String nomeSala){
        System.out.println();
        for(int i = 0; i < listaDeReservas.size(); i++) {
            if(listaDeReservas.get(i).salaReservada.nomeDaSala.equals(nomeSala)){
                LocalDateTime horaInicio = listaDeReservas.get(i).inicio;
                LocalDateTime horaFim = listaDeReservas.get(i).fim;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                String horaFormatadaInicio = horaInicio.format(formatter);
                String horaFormatadaFim = horaFim.format(formatter);
                System.out.println("Reserva ("+i+"): "+"\nNome da Sala: "+listaDeReservas.get(i).salaReservada.nomeDaSala+"\nInicio: "+horaFormatadaInicio+"\nFim: "+horaFormatadaFim);
            }
        }
    }
}