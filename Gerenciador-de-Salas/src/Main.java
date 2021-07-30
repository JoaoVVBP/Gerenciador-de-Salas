import java.time.*;
import java.util.*;

public class Main {
    static List<Participante> participantes = new LinkedList<>();
    static List<String> emails = new LinkedList<>();

    public static Participante verificarSobreposicao(Usuario p1, Usuario p2) {
        Participante p3 = new Participante();

        // Caso seja necessário verificar mais de um intervalo:
        for (int i = 0; i < p1.dataLista.size(); i += 2) {
            for (int j = 1; j < p2.dataLista.size(); j += 2) {

                // Mudar o valor de get para adaptar p/ quando há vários intervalos na lista
                if (p1.dataLista.get(i).isAfter(p2.dataLista.get(j - 1))
                        && p1.dataLista.get(i + 1).isBefore(p2.dataLista.get(j))) {
                    // System.out.println("Intervalo inicial: "+p1.dataLista.get(i)+" Intervalo
                    // final: "+p1.dataLista.get(i+1));
                    p3.dataLista.add(p1.dataLista.get(i));
                    p3.dataLista.add(p1.dataLista.get(i + 1));
                }
                if (p2.dataLista.get(j - 1).isAfter(p1.dataLista.get(i))
                        && p2.dataLista.get(j).isBefore(p1.dataLista.get(i + 1))) {
                    System.out.println("Intervalo inicial: " + p2.dataLista.get(j - 1) + " Intervalo final: "
                            + p2.dataLista.get(j));
                    p3.dataLista.add(p2.dataLista.get(j - 1));
                    p3.dataLista.add(p2.dataLista.get(j));
                }
                // Pode ter erro na logica
                if (p1.dataLista.get(i + 1).isAfter(p2.dataLista.get(j - 1))
                        && p1.dataLista.get(i).isBefore(p2.dataLista.get(j - 1))
                        && p1.dataLista.get(i + 1).isBefore(p2.dataLista.get(j))) {
                    p3.dataLista.add(p2.dataLista.get(j - 1));
                    p3.dataLista.add(p1.dataLista.get(i + 1));
                }

                if (p2.dataLista.get(j).isAfter(p1.dataLista.get(i))
                        && p1.dataLista.get(i).isAfter(p2.dataLista.get(j - 1))
                        && p2.dataLista.get(j).isBefore(p1.dataLista.get(i + 1))) {
                    System.out.println(
                            "Intervalo inicial: " + p1.dataLista.get(i) + " Intervalo final: " + p2.dataLista.get(j));
                    p3.dataLista.add(p1.dataLista.get(i));
                    p3.dataLista.add(p2.dataLista.get(j));
                }
            }
        }
        return p3;
    }

    public static void main(String[] args) {
        MarcadorDeReuniao marcador = new MarcadorDeReuniao();
        String email;
        Scanner s = new Scanner(System.in); // Deixar pra pensar na entrada no final
        Gerenciador gerente = new Gerenciador();
        int datas[] = new int[6];

        System.out.println("Organizador, informe o seu email: ");
        email = s.nextLine();
        gerente.email = email;
        emails.add(email);

        System.out.println("Organizador, informe a quantidade de participantes: ");
        int n = Integer.parseInt(s.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.println("Organizador, informe o email do participante" + i + ": ");
            email = s.next();
            emails.add(email);
        }

        for (int i = 1; i <= n; i++) {// Pula indice 1 pois seria o do gerente
            Participante p = new Participante();
            p.email = emails.get(i);
            participantes.add(p);
        }

        System.out.println("Organizador, informe o periodo no qual pretende marcar a reuniao: (Formato ano mes dia)");
        for (int i = 0; i < 6; i++) {
            datas[i] = s.nextInt();
        }

        gerente.limites[0] = java.time.LocalDate.of(datas[0], datas[1], datas[2]);
        gerente.limites[1] = java.time.LocalDate.of(datas[3], datas[4], datas[5]);

        /*
         * int datagerente[] = new int[10];
         * 
         * System.out.println( "Organizador " + gerente.email +
         * ", informe a quantidade de horarios que voce tem disponibilidade: "); int tam
         * = s.nextInt();
         * 
         * Participante sobreposicoes = new Participante("Sobreposicoes");// Buffer de
         * sobreposicoes
         * 
         * Participante buffer = new Participante();
         * 
         * for (int j = 0; j < tam; j++) {
         * 
         * System.out.println("Organizador " + gerente.email +
         * " informe seu horario disponivel ("+ j + "): "); for (int k = 0; k < 10; k++)
         * { datagerente[k] = s.nextInt(); }
         * 
         * LocalDateTime horarioInicial = java.time.LocalDateTime.of(datagerente[0],
         * datagerente[1], datagerente[2], datagerente[3], datagerente[4]);
         * LocalDateTime horarioFinal = java.time.LocalDateTime.of(datagerente[5],
         * datagerente[6], datagerente[7], datagerente[8], datagerente[9]);
         * 
         * buffer.adicionaHorario(horarioInicial, horarioFinal); }
         */

        marcador.marcarReuniaoEntre(gerente.limites[0], gerente.limites[1], emails);
        Participante sobreposicoes = new Participante("Sobreposicoes");// Buffer de sobreposicoes

        // sobreposicoes = buffer;

        sobreposicoes = participantes.get(0);
        for (int i = 1; i < participantes.size(); i++) {
            sobreposicoes = verificarSobreposicao(sobreposicoes, participantes.get(i));
        }
        System.out.println(participantes);
        sobreposicoes.exibirHorarios();

        // Lembrete: verificar todas as datas inseridas (Uma data final nao pode ser
        // antes de uma data inicial, etc)

        /*
         * // LocalDate dataAtual; LocalDateTime hora1; LocalDateTime hora2;
         * LocalDateTime hora3; LocalDateTime hora4; LocalDateTime hora5; LocalDateTime
         * hora6; LocalDateTime hora7; LocalDateTime hora8;
         * 
         * // Horarios (Participante 1) hora1 = java.time.LocalDateTime.of(2021, 12, 10,
         * 10, 23, 24, 544444); hora2 = java.time.LocalDateTime.of(2021, 12, 19, 16, 23,
         * 24, 544444); hora3 = java.time.LocalDateTime.of(2021, 12, 20, 14, 23, 24,
         * 544444); hora4 = java.time.LocalDateTime.of(2021, 12, 22, 18, 23, 24,
         * 999999);
         * 
         * // Horarios (Participante 2) hora5 = java.time.LocalDateTime.of(2021, 12, 9,
         * 10, 23, 24, 544444); hora6 = java.time.LocalDateTime.of(2021, 12, 11, 16, 23,
         * 24, 544444); hora7 = java.time.LocalDateTime.of(2021, 12, 12, 14, 23, 24,
         * 544444); hora8 = java.time.LocalDateTime.of(2021, 12, 21, 18, 23, 24,
         * 999999);
         * 
         * // Adicionar participantes p1.adicionaHorario(hora1, hora2);
         * p1.adicionaHorario(hora3, hora4);
         * 
         * participantes.add(p1);
         * 
         * p2.adicionaHorario(hora5, hora6); p2.adicionaHorario(hora7, hora8);
         * 
         * participantes.add(p2);
         * 
         * 
         * sobreposicoes.exibirHorarios();
         */

        s.close();

    }
}

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
 * p1.adicionaHorario(LocalDateTime.now(), LocalDateTime.now());
 * p1.adicionaHorario(LocalDateTime.now(), LocalDateTime.now());
 * p1.adicionaHorario(LocalDateTime.now(), LocalDateTime.now());
 * 
 * 
 * System.out.println(hora1.isBefore(hora2) && hora2.isBefore(hora4) &&
 * hora3.isBefore(hora4)); System.out.println(hora1.isBefore(hora2) &&
 * hora2.isBefore(hora4) && hora3.isBefore(hora4));
 * 
 * p1.exibirHorarios();
 */