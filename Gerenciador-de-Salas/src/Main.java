import java.time.*;
import java.util.*;

public class Main {
    static List<Participante> participantes = new LinkedList<>();

    public static Participante verificarSobreposicao(Participante p1, Participante p2) {
        Participante p3 = new Participante();

        // Caso seja necessário verificar mais de um intervalo:
        for (int i = 0; i < p1.dataLista.size(); i+=2) {
            for (int j = 1; j < p2.dataLista.size(); j+=2) {
                
                // Mudar o valor de get para adaptar p/ quando há vários intervalos na lista
                if (p1.dataLista.get(i).isAfter(p2.dataLista.get(j-1))
                        && p1.dataLista.get(i+1).isBefore(p2.dataLista.get(j))) {
                     //System.out.println("Intervalo inicial: "+p1.dataLista.get(i)+" Intervalo final: "+p1.dataLista.get(i+1));
                }
                if (p2.dataLista.get(j-1).isAfter(p1.dataLista.get(i))
                        && p2.dataLista.get(j).isBefore(p1.dataLista.get(i+1))) {
                     //System.out.println("Intervalo inicial: "+p2.dataLista.get(j-1)+" Intervalo final: "+p2.dataLista.get(j));
                }

                if (p1.dataLista.get(i).isBefore(p2.dataLista.get(j))
                        && p1.dataLista.get(i).isBefore(p2.dataLista.get(j-1))
                        && p1.dataLista.get(i+1).isBefore(p2.dataLista.get(j))) {
                     //System.out.println("1");
                     //System.out.println("Intervalo inicial: "+p2.dataLista.get(j-1)+" Intervalo final: "+p1.dataLista.get(i+1));

                    p3.dataLista.add(p2.dataLista.get(j-1));
                    p3.dataLista.add(p1.dataLista.get(i+1));
                }

                if (p1.dataLista.get(i).isBefore(p2.dataLista.get(j))
                        && p1.dataLista.get(i).isAfter(p2.dataLista.get(j-1))
                        && p1.dataLista.get(j).isAfter(p2.dataLista.get(j))) {
                     //System.out.println("2");
                     //System.out.println("Intervalo inicial: "+p1.dataLista.get(i)+" Intervalo final: "+p2.dataLista.get(j));
                    p3.dataLista.add(p1.dataLista.get(i));
                    p3.dataLista.add(p2.dataLista.get(j));
                }
            }
        }

        p3.dataLista.add(java.time.LocalDateTime.of(-1, 1, 1, 1, 1, 1, 1)); // Se index o ano de 1 de p for -1 sabemos QUE
        return p3;
    }

    public static void main(String[] args) {
        LocalDate dataAtual;
        LocalDateTime hora1;
        LocalDateTime hora2;
        LocalDateTime hora3;
        LocalDateTime hora4;
        LocalDateTime hora5;
        LocalDateTime hora6;
        LocalDateTime hora7;
        LocalDateTime hora8;

        Scanner s = new Scanner(System.in); // Deixar pra pensar na entrada no final

        //Horarios (Participante 1)
        hora1 = java.time.LocalDateTime.of(2021, 12, 10, 10, 23, 24, 544444);
        hora2 = java.time.LocalDateTime.of(2021, 12, 19, 16, 23, 24, 544444);
        hora3 = java.time.LocalDateTime.of(2021, 12, 20, 14, 23, 24, 544444);
        hora4 = java.time.LocalDateTime.of(2021, 12, 22, 18, 23, 24, 999999);

        //Horarios (Participante 2)
        hora5 = java.time.LocalDateTime.of(2021, 12, 9, 10, 23, 24, 544444);
        hora6 = java.time.LocalDateTime.of(2021, 12, 11, 16, 23, 24, 544444);
        hora7 = java.time.LocalDateTime.of(2021, 12, 12, 14, 23, 24, 544444);
        hora8 = java.time.LocalDateTime.of(2021, 12, 21, 18, 23, 24, 999999);

        // Adicionar participantes

        Participante p1 = new Participante();
        p1.adionaHorario(hora1, hora2);
        p1.adionaHorario(hora3, hora4);
        
        participantes.add(p1);

        Participante p2 = new Participante();
        p2.adionaHorario(hora5, hora6);
        p2.adionaHorario(hora7, hora8);

        participantes.add(p2);

        Participante p3 = new Participante();
        p3 = verificarSobreposicao(p1, p2);
        p3.exibirHorarios();

        s.close();
        /*
         * //Atribuição: dataAtual = LocalDate.now(); hora1 = LocalDateTime.now();
         * 
         * dataAtual = java.time.LocalDate.of(2021,12,12);
         * 
         * hora1 = java.time.LocalDateTime.of(2021, 12, 13, 21, 23, 24, 544444); hora2 =
         * java.time.LocalDateTime.of(2021, 12, 13, 22, 23, 24, 544444); hora3 =
         * java.time.LocalDateTime.of(2021, 12, 13, 23, 23, 24, 544444); hora4 =
         * java.time.LocalDateTime.of(2021, 12, 13, 23, 59, 59, 999999);
         * 
         * System.out.println("Data atual: "+dataAtual);
         * System.out.println("Hora atual: "+hora1);
         * 
         * System.out.println(hora1.isBefore(hora2) && hora2.isBefore(hora4) &&
         * hora3.isBefore(hora4));
         * 
         * 
         * p1.adionaHorario(LocalDateTime.now(), LocalDateTime.now());
         * p1.adionaHorario(LocalDateTime.now(), LocalDateTime.now());
         * p1.adionaHorario(LocalDateTime.now(), LocalDateTime.now());
         * 
         * 
        System.out.println(hora1.isBefore(hora2) && hora2.isBefore(hora4) && hora3.isBefore(hora4));
        System.out.println(hora1.isBefore(hora2) && hora2.isBefore(hora4) && hora3.isBefore(hora4));

         * p1.exibirHorarios();
         */
    }
}