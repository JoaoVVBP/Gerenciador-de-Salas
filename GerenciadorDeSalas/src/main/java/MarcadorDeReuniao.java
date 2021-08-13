import java.sql.SQLOutput;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class MarcadorDeReuniao {
    //O primeiro indice de disponibilidade eh a data em que a reuniao deve ser marcada.
    //Essa lista tambem armazena os participantes da reuniao.
    static List<Disponibilidade> disponibilidades = new LinkedList<>();

    static Disponibilidade sobreposicoes = new Disponibilidade("Sobreposicoes");

    //O ultimo indice de disponibilidade deve sempre ser o das sobreposicoes ?? Nao lembro por que escrevi isso, vou dormir
    public static Disponibilidade verificarSobreposicao(Disponibilidade d1, Disponibilidade d2) {//Implementar esse dentro de exibirSobreposicao
        Disponibilidade d3 = new Disponibilidade();

        // Caso seja necessário verificar mais de um intervalo:
        for (int i = 0; i < d1.dataLista.size(); i += 2) {
            for (int j = 1; j < d2.dataLista.size(); j += 2) {

                // if 1: intervalo de d1 dentro do intervalo de d2
                if (d1.dataLista.get(i).isAfter(d2.dataLista.get(j - 1)) && d1.dataLista.get(i + 1).isBefore(d2.dataLista.get(j))) {
                    d3.dataLista.add(d1.dataLista.get(i));
                    d3.dataLista.add(d1.dataLista.get(i + 1));
                }
                // if 2: intervalo de d2 dentro do intervalo de d1
                if (d2.dataLista.get(j - 1).isAfter(d1.dataLista.get(i)) && d2.dataLista.get(j).isBefore(d1.dataLista.get(i + 1))) {
                    d3.dataLista.add(d2.dataLista.get(j - 1));
                    d3.dataLista.add(d2.dataLista.get(j));
                }
                // if 3: d1 inicia antes de d2 e termina no meio de d2
                if (d1.dataLista.get(i+1).isAfter(d2.dataLista.get(j-1)) && d1.dataLista.get(i).isBefore(d2.dataLista.get(j-1)) && d1.dataLista.get(i+1).isBefore(d2.dataLista.get(j))) {
                    d3.dataLista.add(d2.dataLista.get(j-1));
                    d3.dataLista.add(d1.dataLista.get(i+1));
                }
                // if 4: d2 inicia antes de d1 e termina no meio de d1
                if (d2.dataLista.get(j).isAfter(d1.dataLista.get(i)) && d1.dataLista.get(i).isAfter(d2.dataLista.get(j - 1)) && d2.dataLista.get(j).isBefore(d1.dataLista.get(i + 1))) {
                    d3.dataLista.add(d1.dataLista.get(i));
                    d3.dataLista.add(d2.dataLista.get(j));
                }
                // Limites de intervalos iguais
                // if 5: ambos valores iguais
                if (d1.dataLista.get(i).isEqual(d2.dataLista.get(j-1)) && d1.dataLista.get(i+1).isEqual(d2.dataLista.get(j))) {
                    d3.dataLista.add(d1.dataLista.get(i));
                    d3.dataLista.add(d1.dataLista.get(i+1));
                }
                // if 6: primeiro valor igual, segundo diferente e d1 antes de d2:
                if (d1.dataLista.get(i).isEqual(d2.dataLista.get(j-1)) && d1.dataLista.get(i+1).isBefore(d2.dataLista.get(j))) {
                    d3.dataLista.add(d1.dataLista.get(i));
                    d3.dataLista.add(d1.dataLista.get(i + 1));
                }
                // if 7: segundo valor igual, primeiro diferente e d1 depois de d2:
                if (d1.dataLista.get(i+1).isEqual(d2.dataLista.get(j)) && d2.dataLista.get(j-1).isBefore(d1.dataLista.get(i))) {
                    d3.dataLista.add(d1.dataLista.get(i));
                    d3.dataLista.add(d2.dataLista.get(j));
                }
                // if 8: primeiro valor igual, segundo diferente e d1 depois de d2:
                if (d1.dataLista.get(i).isEqual(d2.dataLista.get(j-1)) && d1.dataLista.get(i+1).isAfter(d2.dataLista.get(j))) {
                    d3.dataLista.add(d1.dataLista.get(i));
                    d3.dataLista.add(d2.dataLista.get(j));
                }
            }
        }
        return d3;
    }
    //Marca reuniao entre os participantes adicionados
    //A definição dos participantes da reunião será feita utilizando-se do seguinte método
    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal, Collection<String> listaDeParticipantes) {//Problemas aqui
        for (int i = disponibilidades.size()-1; i > 0; i--) {
            disponibilidades.remove(i);
        }

        List<String> participantes = new LinkedList<String>(listaDeParticipantes);

        Disponibilidade reuniao = new Disponibilidade("Reuniao",dataInicial, dataFinal);
        disponibilidades.add(reuniao);//Esta adicionando a reuniao duas vezes, checar no Debugger e nos Testes
        //Por mais que tenha tentado limpar a lista de algumas maneiras, nao tem funcionado

        for (int i = 0; i < participantes.size(); i++) {
            Disponibilidade dp = new Disponibilidade(participantes.get(i));
            disponibilidades.add(dp);
        }

        System.out.println(disponibilidades.size());
    }

    //Cada participante define os seus horários disponíveis. Usado como auxiliar de marcarReuniaoEntre.
    //Talvez seja necessario mover o metodo de disponibilidade do main para aqui
    public void indicaDisponibilidadeDe(String participante, LocalDateTime inicio, LocalDateTime fim) { //Este parece estar funcionando normalmente
        for (int i = 1; i < disponibilidades.size(); i++) {
            if (disponibilidades.get(i).email.equals(participante)){
                disponibilidades.get(i).dataLista.add(inicio);
                disponibilidades.get(i).dataLista.add(fim);
            }
        }
        //Colocar exception aqui
    }

    public void mostraSobreposicao() {
        int index = 1;

        System.out.println("\nSobreposicoes: ");
        for (int j=0; j< sobreposicoes.dataLista.size(); j++) {
            LocalDateTime hora = sobreposicoes.dataLista.get(j);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String horaFormatado = hora.format(formatter);
            if ((j + 1) % 2 != 0) {
                System.out.print("Horario(" + (index) + ") " + horaFormatado + " -> ");
            } else {
                System.out.println(horaFormatado);
                index++;
            }
        }
        disponibilidades.clear();
    }
}