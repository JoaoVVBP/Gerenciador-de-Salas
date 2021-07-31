import java.time.*;
import java.util.*;

public class Main {
    static List<Participante> participantes = new LinkedList<>();
    static List<String> emails = new LinkedList<>();
    static Participante sobreposicoes = new Participante("Sobreposicoes");
    static GerenciadorDeSalas gerenciador = new GerenciadorDeSalas();

    public static Participante verificarSobreposicao(Usuario p1, Usuario p2) {
        Participante p3 = new Participante();

        // Caso seja necessário verificar mais de um intervalo:
        for (int i = 0; i < p1.dataLista.size(); i += 2) {
            for (int j = 1; j < p2.dataLista.size(); j += 2) {

                // if 1:
                if (p1.dataLista.get(i).isAfter(p2.dataLista.get(j - 1))
                        && p1.dataLista.get(i + 1).isBefore(p2.dataLista.get(j))) {
                    // System.out.println("Intervalo inicial: "+p1.dataLista.get(i)+" Intervalo
                    // final: "+p1.dataLista.get(i+1));
                    p3.dataLista.add(p1.dataLista.get(i));
                    p3.dataLista.add(p1.dataLista.get(i + 1));
                }
                // if 2:
                if (p2.dataLista.get(j - 1).isAfter(p1.dataLista.get(i))
                        && p2.dataLista.get(j).isBefore(p1.dataLista.get(i + 1))) {
                    System.out.println("Intervalo inicial: " + p2.dataLista.get(j - 1) + " Intervalo final: "
                            + p2.dataLista.get(j));
                    p3.dataLista.add(p2.dataLista.get(j - 1));
                    p3.dataLista.add(p2.dataLista.get(j));
                }
                // if 3:
                if (p1.dataLista.get(i + 1).isAfter(p2.dataLista.get(j - 1))
                        && p1.dataLista.get(i).isBefore(p2.dataLista.get(j - 1))
                        && p1.dataLista.get(i + 1).isBefore(p2.dataLista.get(j))) {
                    p3.dataLista.add(p2.dataLista.get(j - 1));
                    p3.dataLista.add(p1.dataLista.get(i + 1));
                }
                // if 4:
                if (p2.dataLista.get(j).isAfter(p1.dataLista.get(i))
                        && p1.dataLista.get(i).isAfter(p2.dataLista.get(j - 1))
                        && p2.dataLista.get(j).isBefore(p1.dataLista.get(i + 1))) {
                    System.out.println(
                            "Intervalo inicial: " + p1.dataLista.get(i) + " Intervalo final: " + p2.dataLista.get(j));
                    p3.dataLista.add(p1.dataLista.get(i));
                    p3.dataLista.add(p2.dataLista.get(j));
                }
                // Limites de intervalos iguais
                // if 5: ambos valores iguais
                if (p1.dataLista.get(i).isEqual(p2.dataLista.get(j - 1))
                        && p1.dataLista.get(i + 1).isEqual(p2.dataLista.get(j))) {
                    p3.dataLista.add(p1.dataLista.get(i));
                    p3.dataLista.add(p1.dataLista.get(i + 1));
                }
                // if 6: primeiro valor igual, segundo diferente e p1 antes de p2:
                if (p1.dataLista.get(i).isEqual(p2.dataLista.get(j - 1))
                        && p1.dataLista.get(i + 1).isBefore(p2.dataLista.get(j))) {
                    p3.dataLista.add(p1.dataLista.get(i));
                    p3.dataLista.add(p1.dataLista.get(i + 1));
                }
                // if 7: segundo valor igual, primeiro diferente e p1 depois de p2:
                if (p1.dataLista.get(i + 1).isEqual(p2.dataLista.get(j))
                        && p2.dataLista.get(j - 1).isBefore(p1.dataLista.get(i))) {
                    p3.dataLista.add(p1.dataLista.get(i));
                    p3.dataLista.add(p2.dataLista.get(j));
                }
                // if 8: primeiro valor igual, segundo diferente e p1 depois de p2:
                if (p1.dataLista.get(i).isEqual(p2.dataLista.get(j - 1))
                        && p1.dataLista.get(i + 1).isAfter(p2.dataLista.get(j))) {
                    p3.dataLista.add(p1.dataLista.get(i));
                    p3.dataLista.add(p2.dataLista.get(j));
                }
            }
        }
        return p3;
    }

    public static void exibirSalas(){
        if (gerenciador.listaDeSalas().size() == 0) {
            System.out.println("Erro: Nao existem salas registradas");
            return;
        }
        System.out.println("\nLista de salas disponiveis:");
        for (int i = 0; i < GerenciadorDeSalas.listaDeSalas.size(); i++) {
            System.out.println("Sala "+(i+1)+": "+GerenciadorDeSalas.listaDeSalas.get(i).nomeDaSala);
            System.out.println("Capacidade: "+GerenciadorDeSalas.listaDeSalas.get(i).capacidadeMaxima);
            System.out.println("Descrição: "+GerenciadorDeSalas.listaDeSalas.get(i).descricao);
            System.out.println();
        }
    }

    //Metodo chamado quando o gerente quer organizar uma nova reuniao
    public static void organizarReuniao(Gerenciador gerente, String email) throws Exception{
        if (GerenciadorDeSalas.listaDeSalas.size() == 0) {
            System.out.println("Erro: Nao existem salas registradas");
            return;
        }

        Scanner s = new Scanner(System.in);
        int datas[] = new int[6];
        MarcadorDeReuniao marcador = new MarcadorDeReuniao();

        System.out.println("\nOrganizador, informe a quantidade de participantes: ");
        int n = Integer.parseInt(s.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.println("\nOrganizador, informe o email do participante " + (i+1) + ": ");
            email = s.next();
            if(!emails.contains(email)){
                emails.add(email);
            }
            else {
                System.out.println("Erro: este email ja foi adicionado");
                i--;
            }
        }

        for (int i = 1; i <= n; i++) {// Pula indice 1 pois seria o do gerente
            Participante p = new Participante();
            p.email = emails.get(i);
            participantes.add(p);
        }

        System.out.println("\nOrganizador, informe o periodo no qual pretende marcar a reuniao: (Formato ano mes dia)");
        for (int i = 0; i < 6; i++) {
            datas[i] = s.nextInt();
        }

        gerente.limites[0] = java.time.LocalDate.of(datas[0], datas[1], datas[2]);
        gerente.limites[1] = java.time.LocalDate.of(datas[3], datas[4], datas[5]);

        int datagerente[] = new int[10];

        System.out.println("\nOrganizador " + gerente.email + ", informe a quantidade de horarios que voce tem disponibilidade: ");
        int tam = s.nextInt();

        Participante buffer = new Participante();

        for (int j = 0; j < tam; j++) {

            System.out.println("Organizador " + gerente.email + " informe seu horario disponivel ("+ (j+1) +"): ");
            for (int k = 0; k < 10; k++) {
                datagerente[k] = s.nextInt();
            }

            LocalDateTime horarioInicial = java.time.LocalDateTime.of(datagerente[0], datagerente[1], datagerente[2],
                    datagerente[3], datagerente[4]);
            LocalDateTime horarioFinal = java.time.LocalDateTime.of(datagerente[5], datagerente[6], datagerente[7],
                    datagerente[8], datagerente[9]);

            buffer.adicionaHorario(horarioInicial, horarioFinal);
        }

        marcador.marcarReuniaoEntre(gerente.limites[0], gerente.limites[1], emails);

       
        sobreposicoes = buffer;

        //sobreposicoes = participantes.get(0);

        for (int i = 1; i < participantes.size(); i++) {
            sobreposicoes = verificarSobreposicao(sobreposicoes, participantes.get(i));
        }

        // Lembrete: verificar todas as datas inseridas (Uma data final nao pode ser
        // antes de uma data inicial, etc)

        participantes.add(sobreposicoes);

        marcador.mostraSobreposicao();

          //Seleção da sala:
        Main.exibirSalas();
          //Verifica se a sala comporta o numero de participantes
        System.out.println("Informe o numero da sala selecionada: ");
        int m = s.nextInt()-1;
        while(GerenciadorDeSalas.listaDeSalas.get(m).capacidadeMaxima < Main.participantes.size()) {
            System.out.println("Numero de participantes maior que capacidade maxima da sala.");
            System.out.println("Informe o numero da sala selecionada: ");
            m = s.nextInt();    
        }
 
          //Definir horario da reuniao:
          if(sobreposicoes.dataLista.size() == 1){
            //Marcar reunião no unico horario de sobreposicao
            gerenciador.reservaSalaChamada(GerenciadorDeSalas.listaDeSalas.get(m).nomeDaSala, sobreposicoes.dataLista.get(0), sobreposicoes.dataLista.get(1));
          } else {
            System.out.println("\nInforme o numero do horario de sobreposicao para realizar a reuniao: ");
            int p = s.nextInt();
            p = (p-1)*2;
            gerenciador.reservaSalaChamada(GerenciadorDeSalas.listaDeSalas.get(m).nomeDaSala, sobreposicoes.dataLista.get(p), sobreposicoes.dataLista.get(p+1));
        }

         //Exibir mensagem de reuniao marcada
        System.out.println("\nReserva efetudada: ");
        gerenciador.imprimeReservasDaSala(GerenciadorDeSalas.listaDeSalas.get(m).nomeDaSala);
    } 

    public static void imprimirReservas(){
        if (gerenciador.listaDeReservas.size() == 0) {
            System.out.println("\nErro: Nao existem reservas efetuadas");
            return;
        }
        else System.out.println("\nLista de reservas: ");
        for (int i = 0; i < gerenciador.listaDeReservas.size() ; i++) {
            gerenciador.imprimeReservasDaSala(gerenciador.listaDeReservas.get(i).salaReservada.nomeDaSala);
        }
        return;
    }

    public static void removeReserva(){
        if (gerenciador.listaDeReservas.size() == 0) {
            System.out.println("\nErro: Nao existem reservas efetuadas");
            return;
        }
        Scanner s = new Scanner(System.in);
        imprimirReservas();
        System.out.println("\nInsira o nome da sala que deseja excluir: ");
        int n = Integer.parseInt(s.nextLine());
        gerenciador.cancelaReserva(gerenciador.listaDeReservas.get(n));

        return;
    }

    public static void insereSala(){
        Scanner s = new Scanner(System.in);

        System.out.println("\nOrganizador, informe a quantidade de salas que deseja adicionar: ");
        int n = Integer.parseInt(s.nextLine());

        //Adicionar salas:
        for (int i = 0; i < n; i++) {
            Sala sala = new Sala();

            System.out.println("\nOrganizador, informe o nome da sala "+ (i+1) +": ");
            sala.nomeDaSala = s.nextLine();

            System.out.println("\nOrganizador, informe a capacidade maxima da sala "+ (i+1) +": ");
            sala.capacidadeMaxima = Integer.parseInt(s.nextLine());

            System.out.println("\nOrganizador, informe uma descricao para a sala "+ (i+1) +": ");
            sala.descricao = s.nextLine();

            for (int j = 0; j < GerenciadorDeSalas.listaDeSalas.size()-1; j++) {
                if (GerenciadorDeSalas.listaDeSalas.get(j).nomeDaSala.equals(sala.nomeDaSala)) {
                    System.out.println("\nErro: esta sala ja foi adicionada");
                    i--;
                }
            }
            GerenciadorDeSalas.listaDeSalas.add(sala);
        }
    }

    public static void main(String[] args) throws Exception {
        String email;
        Scanner s = new Scanner(System.in); // Deixar pra pensar na entrada no final
        Gerenciador gerente = new Gerenciador();
       
        System.out.println("\nOrganizador, informe o seu email: ");
        email = s.nextLine();
        gerente.email = email;
        emails.add(email);

        int m = 0;
        do {
            System.out.println("\nEscolha uma :\n (1): Criar nova reunião\n (2): Cancelar reserva\n (3): Adicionar nova sala à lista de salas\n (4): Exibir reservas\n (5): Exibir salas\n (6): Sair\n");
            m = s.nextInt();
            switch (m) {
                case 1:
                    organizarReuniao(gerente, email);
                    break;
                case 2:
                    removeReserva();
                    break;
                case 3:
                    insereSala();
                    break;
                case 4:
                    imprimirReservas();
                    break;
                case 5:
                    exibirSalas();
                    break;
                case 6:
                    System.out.println("Encerrando...");
                    break;
                default:
                System.out.println("...");
                    break;
            }
        } while (m !=6);

        s.close();
        return;
    }
}