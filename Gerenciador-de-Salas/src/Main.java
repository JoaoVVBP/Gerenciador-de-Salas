import java.time.*;
import java.util.*;

public class Main {
    static List<Participante> participantes = new LinkedList<>();

    public static Participante verificarSobreposicao(Participante p1, Participante p2) {
        Participante p = new Participante(); //Usar dataLista deste participante como lista de sobreposições
        
        /* // Caso seja necessário verificar mais de um intervalo:
        for (int i = 0; i < p1.dataLista.size()/2; i++) {
            for (int j = 0; j < p2.dataLista.size()/2; j++) {
            
            }
        }
        */
        //Mudar o valor de get para adaptar p/ quando há vários intervalos na lista
        if (p1.dataLista.get(0).isAfter(p2.dataLista.get(0)) && p1.dataLista.get(1).isBefore(p2.dataLista.get(1))) {
            //System.out.println("Intervalo inicial: "+p1.dataLista.get(0)+" Intervalo final: "+p1.dataLista.get(1));
            return p1;
        }
        if (p2.dataLista.get(0).isAfter(p1.dataLista.get(0)) && p2.dataLista.get(1).isBefore(p1.dataLista.get(1))) {
           // System.out.println("Intervalo inicial: "+p2.dataLista.get(0)+" Intervalo final: "+p2.dataLista.get(1));
            return p2;
        }
        
        if (p1.dataLista.get(0).isBefore(p2.dataLista.get(1)) && p1.dataLista.get(0).isBefore(p2.dataLista.get(0)) && p1.dataLista.get(1).isBefore(p2.dataLista.get(1))){
           // System.out.println("1");
           // System.out.println("Intervalo inicial: "+p2.dataLista.get(0)+" Intervalo final: "+p1.dataLista.get(1));
           
            Participante p3 = new Participante();
            p3.dataLista.add(p2.dataLista.get(0));
            p3.dataLista.add(p1.dataLista.get(1));
            return p3;
        }
        
        if (p1.dataLista.get(0).isBefore(p2.dataLista.get(1)) && p1.dataLista.get(0).isAfter(p2.dataLista.get(0)) && p1.dataLista.get(1).isAfter(p2.dataLista.get(1))){
            //System.out.println("2");
            //System.out.println("Intervalo inicial: "+p1.dataLista.get(0)+" Intervalo final: "+p2.dataLista.get(1));
            Participante p3 = new Participante();
            p3.dataLista.add(p1.dataLista.get(0));
            p3.dataLista.add(p2.dataLista.get(1));
            return p3;
        }

        p.dataLista.add(java.time.LocalDateTime.of(-1, 0, 0, 0, 0, 0, 0)); //Se index o ano de 1 de p for -1 sabemos que  
        return p;
    }

    public static void main(String[] args) {
        LocalDate dataAtual;
        LocalDateTime hora1;
        LocalDateTime hora2;
        LocalDateTime hora3;
        LocalDateTime hora4;
        LocalDateTime hora5;
        LocalDateTime hora6;

        Scanner s = new Scanner(System.in); // Deixar pra pensar na entrada no final
    
        hora1 = java.time.LocalDateTime.of(2021, 12, 10, 10, 23, 24, 544444);
        hora2 = java.time.LocalDateTime.of(2021, 12, 19, 16, 23, 24, 544444);
        hora3 = java.time.LocalDateTime.of(2021, 12, 12, 14, 23, 24, 544444);
        hora4 = java.time.LocalDateTime.of(2021, 12, 20, 18, 23, 24, 999999);
        
        hora5 = java.time.LocalDateTime.of(2021, 12, 13, 20, 23, 24, 544444);
        hora6 = java.time.LocalDateTime.of(2021, 12, 13, 21, 30, 59, 999999);

        //Adicionar participantes

        Participante p1 = new Participante();
        p1.adionaHorario(hora3, hora4);
        participantes.add(p1);

        Participante p2 = new Participante();
        p2.adionaHorario(hora1, hora2);
        participantes.add(p2);

        Participante p3 = new Participante(); //Mais restrito
        p3.adionaHorario(hora3, hora4);
        participantes.add(p3);

        verificarSobreposicao(p1, p2);

        System.out.println(hora1.isBefore(hora2) && hora2.isBefore(hora4) && hora3.isBefore(hora4));
        System.out.println(hora1.isBefore(hora2) && hora2.isBefore(hora4) && hora3.isBefore(hora4));


        /*
        //Atribuição:
        dataAtual = LocalDate.now();
        hora1 = LocalDateTime.now();

        dataAtual = java.time.LocalDate.of(2021,12,12);

        hora1 = java.time.LocalDateTime.of(2021, 12, 13, 21, 23, 24, 544444);
        hora2 = java.time.LocalDateTime.of(2021, 12, 13, 22, 23, 24, 544444);
        hora3 = java.time.LocalDateTime.of(2021, 12, 13, 23, 23, 24, 544444);
        hora4 = java.time.LocalDateTime.of(2021, 12, 13, 23, 59, 59, 999999);

        System.out.println("Data atual: "+dataAtual);
        System.out.println("Hora atual: "+hora1);

        System.out.println(hora1.isBefore(hora2) && hora2.isBefore(hora4) && hora3.isBefore(hora4));

    
        p1.adionaHorario(LocalDateTime.now(), LocalDateTime.now());
        p1.adionaHorario(LocalDateTime.now(), LocalDateTime.now());
        p1.adionaHorario(LocalDateTime.now(), LocalDateTime.now());


        p1.exibirHorarios();
    */
    }
}