import java.time.*;
import java.util.*;

public class Main {
    static List<Participante> participantes = new LinkedList<>();
    static List<String> emails = new LinkedList<>();
    static Participante sobreposicoes = new Participante("Sobreposicoes");
    static GerenciadorDeSalas gerenciador = new GerenciadorDeSalas();
    static Gerenciador gerente = new Gerenciador();

    public static Participante verificarSobreposicao(Usuario p1, Usuario p2) {
        Participante p3 = new Participante();

        // Caso seja necessário verificar mais de um intervalo:
        for (int i = 0; i < p1.dataLista.size(); i += 2) {
            for (int j = 1; j < p2.dataLista.size(); j += 2) {

                // if 1: intervalo de p1 dentro do intervalo de p2
                if (p1.dataLista.get(i).isAfter(p2.dataLista.get(j - 1)) && p1.dataLista.get(i + 1).isBefore(p2.dataLista.get(j))) {
                    p3.dataLista.add(p1.dataLista.get(i));
                    p3.dataLista.add(p1.dataLista.get(i + 1));
                }
                // if 2: intervalo de p2 dentro do intervalo de p1
                if (p2.dataLista.get(j - 1).isAfter(p1.dataLista.get(i)) && p2.dataLista.get(j).isBefore(p1.dataLista.get(i + 1))) {
                    p3.dataLista.add(p2.dataLista.get(j - 1));
                    p3.dataLista.add(p2.dataLista.get(j));
                }
                // if 3: p1 inicia antes de p2 e termina no meio de p2
                if (p1.dataLista.get(i+1).isAfter(p2.dataLista.get(j-1)) && p1.dataLista.get(i).isBefore(p2.dataLista.get(j-1)) && p1.dataLista.get(i+1).isBefore(p2.dataLista.get(j))) {
                    p3.dataLista.add(p2.dataLista.get(j-1));
                    p3.dataLista.add(p1.dataLista.get(i+1));
                }
                // if 4: p2 inicia antes de p1 e termina no meio de p1
                if (p2.dataLista.get(j).isAfter(p1.dataLista.get(i)) && p1.dataLista.get(i).isAfter(p2.dataLista.get(j - 1)) && p2.dataLista.get(j).isBefore(p1.dataLista.get(i + 1))) {
                    p3.dataLista.add(p1.dataLista.get(i));
                    p3.dataLista.add(p2.dataLista.get(j));
                }
                // Limites de intervalos iguais
                // if 5: ambos valores iguais
                if (p1.dataLista.get(i).isEqual(p2.dataLista.get(j-1)) && p1.dataLista.get(i+1).isEqual(p2.dataLista.get(j))) {
                    p3.dataLista.add(p1.dataLista.get(i));
                    p3.dataLista.add(p1.dataLista.get(i+1));
                }
                // if 6: primeiro valor igual, segundo diferente e p1 antes de p2:
                if (p1.dataLista.get(i).isEqual(p2.dataLista.get(j-1)) && p1.dataLista.get(i+1).isBefore(p2.dataLista.get(j))) {
                    p3.dataLista.add(p1.dataLista.get(i));
                    p3.dataLista.add(p1.dataLista.get(i + 1));
                }
                // if 7: segundo valor igual, primeiro diferente e p1 depois de p2:
                if (p1.dataLista.get(i+1).isEqual(p2.dataLista.get(j)) && p2.dataLista.get(j-1).isBefore(p1.dataLista.get(i))) {
                    p3.dataLista.add(p1.dataLista.get(i));
                    p3.dataLista.add(p2.dataLista.get(j));
                }
                // if 8: primeiro valor igual, segundo diferente e p1 depois de p2:
                if (p1.dataLista.get(i).isEqual(p2.dataLista.get(j-1)) && p1.dataLista.get(i+1).isAfter(p2.dataLista.get(j))) {
                    p3.dataLista.add(p1.dataLista.get(i));
                    p3.dataLista.add(p2.dataLista.get(j));
                }
            }
        }
        return p3;
    }

    //Método para exibir todas as salas na lista (usado no switch)
    public static void exibirSalas() {
        if (GerenciadorDeSalas.listaDeSalas.size() == 0) {
            System.out.println("Erro: não existem salas registradas");
            return;
        }
        System.out.println("\nLista de salas disponiveis:");
        for (int i = 0; i < GerenciadorDeSalas.listaDeSalas.size(); i++) {
            System.out.println("Sala " + (i + 1) + ": " + GerenciadorDeSalas.listaDeSalas.get(i).nomeDaSala);
            System.out.println("Capacidade: " + GerenciadorDeSalas.listaDeSalas.get(i).capacidadeMaxima);
            System.out.println("Descrição: " + GerenciadorDeSalas.listaDeSalas.get(i).descricao);
            System.out.println();
        }
    }

    //Metodo chamado quando o gerente quer organizar uma nova reuniao
    public static void organizarReuniao(String email) throws Exception {

        //testa se há alguma sala criada
        if (GerenciadorDeSalas.listaDeSalas.size() == 0) {
            System.out.println("Erro: não existem salas registradas");
            return;
        }

        participantes.clear();
        Scanner s = new Scanner(System.in);
        int datas[] = new int[6];
        MarcadorDeReuniao marcador = new MarcadorDeReuniao();
        Participante buffer = new Participante();

        //Inserção de dados:
        System.out.println("\nOrganizador, informe a quantidade de participantes: ");
        int n = Integer.parseInt(s.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.println("\nOrganizador, informe o email do participante " +(i+1) +": ");
            email = s.next();
            if (!emails.contains(email)) {
                emails.add(email);
            } else {
                System.out.println("\nErro: este email já foi adicionado");
                i--;
            }
        }

        for (int i = 1; i <= n; i++) {// Pula indice 1 pois seria o do gerente
            Participante p = new Participante();
            p.email = emails.get(i);
            participantes.add(p);
        }

        //Entrada de dados: periodo limite em que a reunião pode ser marcada
        System.out.println("\nOrganizador, informe o periodo no qual pretende marcar a reuniao: ");
        System.out.println("Exemplo de entrada: \n2021 07 30 (inicio)\n2021 07 30 (fim)\n");
        for (int i = 0; i < 6; i++) {
            datas[i] = s.nextInt();
        }
        gerente.limites[0] = java.time.LocalDate.of(datas[0], datas[1], datas[2]);
        gerente.limites[1] = java.time.LocalDate.of(datas[3], datas[4], datas[5]);

        //Disponibilidade do organizador
        int datagerente[] = new int[10];
        System.out.println(
                "\nOrganizador " + gerente.email + ", informe a quantidade de horarios em que voce tem disponibilidade: ");
        int tam = s.nextInt();
        for (int j = 0; j < tam; j++) {
            System.out.println("\nOrganizador " + gerente.email + ", informe seu horario disponivel (" + (j + 1) + "): ");
            System.out.println("Exemplo de entrada: \n2021 07 30 12 00 (inicio)\n2021 07 30 15 30 (fim)");
            for (int k = 0; k < 10; k++) datagerente[k] = s.nextInt();
            
            LocalDateTime horarioInicial = java.time.LocalDateTime.of(datagerente[0], datagerente[1], datagerente[2], datagerente[3], datagerente[4]);
            LocalDateTime horarioFinal = java.time.LocalDateTime.of(datagerente[5], datagerente[6], datagerente[7], datagerente[8], datagerente[9]);

            // Verifica se está nos limites definidos pelo organizador
            LocalDate inicioLocalDate = horarioInicial.toLocalDate();
            LocalDate fimLocalDate = horarioFinal.toLocalDate();
            if (inicioLocalDate.isBefore(Main.gerente.limites[0]) || fimLocalDate.isAfter(Main.gerente.limites[1])) {
                System.out.println("\nData fora dos limites definidos pelo organizador. Informe um valor valido.");
                j--;
            } else {
                gerente.adicionaHorario(horarioInicial, horarioFinal);
                buffer.adicionaHorario(horarioInicial, horarioFinal);
            }

        }
        marcador.marcarReuniaoEntre(gerente.limites[0], gerente.limites[1], emails);

        //Executa a verificação de sobreposições entre todos participantes
        sobreposicoes = buffer;
        for (int i = 1; i < participantes.size(); i++) sobreposicoes = verificarSobreposicao(sobreposicoes, participantes.get(i));
        participantes.add(sobreposicoes);

        //Exibir as sobreposições:
        marcador.mostraSobreposicao();

        //Seleção da sala:
        Main.exibirSalas();

        //Verifica se alguma sala comporta o número de participantes
        boolean haSalaValida = false;
        for (int i = 0; i < GerenciadorDeSalas.listaDeSalas.size(); i++) if(GerenciadorDeSalas.listaDeSalas.get(i).capacidadeMaxima >= participantes.size()) haSalaValida = true;
        if(!haSalaValida){
            System.out.println("Erro: nenhuma sala comporta o número de participantes.");
            return;
        }

        // Verifica se a sala comporta o numero de participantes
        System.out.println("Informe o numero da sala selecionada: ");
        int m = s.nextInt()-1;

        //Verifica se o valor inserido é valido:
        while (m < 0 || m > GerenciadorDeSalas.listaDeSalas.size()) {
            System.out.println("Número invalido.");
            System.out.println("Informe o número da sala selecionada: ");
            m = s.nextInt()-1;
        }

        //Verifica se a sala comporta o número de participantes
        while (GerenciadorDeSalas.listaDeSalas.get(m).capacidadeMaxima < Main.participantes.size()) {
            System.out.println("Numero de participantes maior que capacidade maxima da sala.\n");
            System.out.println("Informe o numero da sala selecionada: ");
            m = s.nextInt()-1;
        }

        // Definir horario da reuniao:
        if (sobreposicoes.dataLista.size() == 2) {
            // Marcar reunião no unico horario de sobreposicao
            gerenciador.reservaSalaChamada(GerenciadorDeSalas.listaDeSalas.get(m).nomeDaSala, sobreposicoes.dataLista.get(0), sobreposicoes.dataLista.get(1));
        } else {
            System.out.println("\nInforme o numero do horario de sobreposicao para realizar a reuniao: ");
            int p = s.nextInt();
            p = (p-1)*2;
            gerenciador.reservaSalaChamada(GerenciadorDeSalas.listaDeSalas.get(m).nomeDaSala, sobreposicoes.dataLista.get(p), sobreposicoes.dataLista.get(p + 1));
        }

        // Exibir mensagem de reuniao marcada
        System.out.println("\nReserva efetudada: ");
        gerenciador.imprimeReservasDaSala(GerenciadorDeSalas.listaDeSalas.get(m).nomeDaSala);
    }

    //Método para exibir todas as reservas efetuadas
    public static void imprimirReservas() {
        if (gerenciador.listaDeReservas.size() == 0) {
            System.out.println("\nErro: Nao existem reservas efetuadas");
            return;
        } else System.out.println("\nLista de reservas: ");
        for (int i = 0; i < gerenciador.listaDeReservas.size(); i++) gerenciador.imprimeReservasDaSala(gerenciador.listaDeReservas.get(i).salaReservada.nomeDaSala);
        return;
    }

    //Método para remover uma reserva
    public static void removerReserva() {

        //Testa se há reservas
        if (gerenciador.listaDeReservas.size() == 0) {
            System.out.println("\nErro: Nao existem reservas efetuadas");
            return;
        }
        Scanner s = new Scanner(System.in);

        //Exibe todas reservas
        imprimirReservas();
        System.out.println("\nInsira o número da reserva que deseja excluir: ");
        int n = Integer.parseInt(s.nextLine());

        //Busca a reserva e a exclui
        if (n > gerenciador.listaDeReservas.size() || n < 0) {
            System.out.println("\nValor invalido.");
            removerReserva();
        } else gerenciador.cancelaReserva(gerenciador.listaDeReservas.get(n));

        return;
    }

    //Método para criar uma sala e a inserir na lista
    public static void inserirSala() {
        Scanner s = new Scanner(System.in);

        System.out.println("\nOrganizador, informe a quantidade de salas que deseja adicionar: ");
        int n = Integer.parseInt(s.nextLine());

        // Adicionar salas:
        for (int i=0; i<n; i++) {
            Sala sala = new Sala();

            System.out.println("\nOrganizador, informe o nome da sala " + (i+1) + ": ");
            sala.nomeDaSala = s.nextLine();

            System.out.println("\nOrganizador, informe a capacidade maxima da sala " + (i+1) + ": ");
            sala.capacidadeMaxima = Integer.parseInt(s.nextLine());

            System.out.println("\nOrganizador, informe uma descricao para a sala " + (i+1) + ": ");
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
    //Método para apagar uma sala da lista
    public static void removerSala(){

        Scanner s = new Scanner(System.in);
        if (GerenciadorDeSalas.listaDeSalas.size() == 0) {
            System.out.println("\nErro: não há nenhuma sala adicionada.");
            return;
        }

        exibirSalas();
        System.out.println("\nInsira o número da sala que deseja excluir: ");
        int n = Integer.parseInt(s.nextLine())-1;
        
        //Busca a sala e a exclui
        if (n>GerenciadorDeSalas.listaDeSalas.size() || n<0) {
            System.out.println("\nValor invalido.");
            removerSala();
        } else gerenciador.removeSalaChamada(GerenciadorDeSalas.listaDeSalas.get(n).nomeDaSala);

        return;
    }

    public static void main(String[] args) throws Exception {
        String email;
        Scanner s = new Scanner(System.in);

        //Organizador deve informar o seu email
        System.out.println("\nOrganizador, informe o seu email: ");
        email = s.nextLine();
        gerente.email = email;
        emails.add(email);

        try {
            int m = 0;
            do {
                System.out.println("\nInsira o número da ação desejada:\n (1): Criar nova reunião\n (2): Cancelar reserva\n (3): Adicionar nova sala à lista de salas\n (4): Remover sala\n (5): Exibir reservas\n (6): Exibir salas\n (7): Sair\n");
                m = s.nextInt();
                switch (m) {
                    case 1:
                        organizarReuniao(email);
                        break;
                    case 2:
                        removerReserva();
                        break;
                    case 3:
                        inserirSala();
                        break;
                    case 4:
                        removerSala();
                        break;
                    case 5:
                        imprimirReservas();
                        break;
                    case 6:
                        exibirSalas();
                        break;
                    case 7:
                        System.out.println("Encerrando...");
                        break;
                    default:
                        System.out.println("Entrada invalida.");
                        break;
                }
            } while (m!=7);
            s.close();
        } catch (Exception e) {
            System.out.println("\nFalha durante a operação: ");
            System.err.println("("+e+")\n");
        }
        return;
    }
}