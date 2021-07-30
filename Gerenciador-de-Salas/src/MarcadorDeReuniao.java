import java.util.*;
import java.time.*;

public class MarcadorDeReuniao {
    int bufferdatas[] = new int[10];
    Scanner s = new Scanner(System.in);

    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal,
            Collection<String> listaDeParticipantes) {
        try {

            for (int i = 0; i < Main.participantes.size(); i++) {

                System.out.println("Participante " + Main.participantes.get(i).email
                        + ", informe a quantidade de horarios que voce tem disponibilidade: ");
                int n = s.nextInt();

                for (int j = 0; j < n; j++) {

                    System.out.println("Participante " + Main.participantes.get(i).email
                            + ", informe seu horario de disponibilidade"+j+"(Formato ano mes dia hora minuto, Inicio e Fim): ");
                    for (int k = 0; k < 10; k++) {
                        bufferdatas[k] = s.nextInt();
                    }

                    LocalDateTime horarioInicial = java.time.LocalDateTime.of(bufferdatas[0], bufferdatas[1],
                            bufferdatas[2], bufferdatas[3], bufferdatas[4]);

                    LocalDateTime horarioFinal = java.time.LocalDateTime.of(bufferdatas[5], bufferdatas[6],
                            bufferdatas[7], bufferdatas[8], bufferdatas[9]);

                    Main.participantes.get(i).adicionaHorario(horarioInicial, horarioFinal);
                }
            }
        } catch (java.time.DateTimeException e) {
            System.out.println("Erro no valor inserido, contactar suporte tecnico");
        }
    }

    public void indicaDisponibilidadeDe(String participante, LocalDateTime inicio, LocalDateTime fim) {
        for (int i = 0; i < Main.participantes.size(); i++) {
            if (Main.participantes.get(i).email.equals(participante)) {
                Main.participantes.get(i).dataLista.add(inicio);
                Main.participantes.get(i).dataLista.add(fim);
            }
        }
    }

    public void mostraSobreposicao() {

    }

}