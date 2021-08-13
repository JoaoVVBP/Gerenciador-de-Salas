import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class MarcadorDeReuniao {
    int datasTemp[] = new int[10];
    Scanner s = new Scanner(System.in);

    //Marca reuniao entre os participantes adicionados
    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal, Collection<String> listaDeParticipantes) {
        listaDeParticipantes.clear();
        try {
            for (int i=0; i<Main.participantes.size(); i++) {
                System.out.println("\nParticipante " + Main.participantes.get(i).email+ ", informe a quantidade de horarios em que voce tem disponibilidade: ");
                int n = s.nextInt();

                for (int j=0; j<n; j++) {
                    System.out.println("\nParticipante " + Main.participantes.get(i).email+ ", informe seu horario de disponibilidade (" + (j + 1) + ")");
                    System.out.println("Exemplo de entrada: \n2021 07 30 12 00 (inicio)\n2021 07 30 15 30 (fim)\n");
                    for (int k = 0; k < 10; k++) {
                        datasTemp[k] = s.nextInt();
                    }
                    LocalDateTime horarioInicial = java.time.LocalDateTime.of(datasTemp[0], datasTemp[1], datasTemp[2], datasTemp[3], datasTemp[4]);
                    LocalDateTime horarioFinal = java.time.LocalDateTime.of(datasTemp[5], datasTemp[6],datasTemp[7], datasTemp[8], datasTemp[9]);

                    // Verifica se está nos limites definidos pelo organizador
                    LocalDate inicioLocalDate = horarioInicial.toLocalDate();
                    LocalDate fimLocalDate = horarioFinal.toLocalDate();
                    if (inicioLocalDate.isBefore(Main.gerente.limites[0]) || fimLocalDate.isAfter(Main.gerente.limites[1])) {
                        System.out.println("\nData fora dos limites definidos pelo organizador. Informe um valor valido.");
                        j--;
                    } else {
                        indicaDisponibilidadeDe(Main.participantes.get(i).email, horarioInicial, horarioFinal);
                    }
                }
            }
        } catch (java.time.DateTimeException e) {
            System.out.println("\nErro: formato de data invalido");
            Main.participantes.clear();
            return;
        }
    }

    //Cada participante define os seus horários disponíveis. Usado como auxiliar de marcarReuniaoEntre.
    public void indicaDisponibilidadeDe(String participante, LocalDateTime inicio, LocalDateTime fim) {
        for (int i=0; i<Main.participantes.size(); i++) {
            if (Main.participantes.get(i).email.equals(participante)) {
                Main.participantes.get(i).dataLista.add(inicio);
                Main.participantes.get(i).dataLista.add(fim);
            }
        }
    }

    public void mostraSobreposicao() {
        int t = 1;
        System.out.println("\nOrganizador: " + Main.gerente.email + "\nDisponibilidade: ");
        for (int j = 0; j < Main.gerente.dataLista.size(); j++) {
            LocalDateTime hora = Main.gerente.dataLista.get(j);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String horaFormatado = hora.format(formatter);
            if ((j+1)%2!=0) System.out.print("Horario(" +t+") " + horaFormatado + " -> ");
            else {
                System.out.println(horaFormatado);
                t++;
            }
        }

        int index = 1;
        // Converter as datas para um formato mais legivel
        for (int i=0; i<Main.participantes.size()-1; i++) {
            System.out.println("\nUsuario: " + Main.participantes.get(i).email + "\nDisponibilidade: ");
            for (int j = 0; j < Main.participantes.get(i).dataLista.size(); j++) {
                LocalDateTime hora = Main.participantes.get(i).dataLista.get(j);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                String horaFormatado = hora.format(formatter);
                if ((j+1)%2!=0) System.out.print("Horario(" +index+") " + horaFormatado + " -> ");
                else {
                    System.out.println(horaFormatado);
                    index++;
                }
            }
            index = 1;
        }
        System.out.println("\nSobreposições: ");
        for (int j=0; j<Main.sobreposicoes.dataLista.size(); j++) {
            LocalDateTime hora = Main.sobreposicoes.dataLista.get(j);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String horaFormatado = hora.format(formatter);
            if ((j + 1) % 2 != 0) {
                System.out.print("Horario(" + (index) + ") " + horaFormatado + " -> ");
            } else {
                System.out.println(horaFormatado);
                index++;
            }
        }
        index = 1;
    }
}