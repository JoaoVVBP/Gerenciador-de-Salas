import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class TestesGerenciadorDeSalas {

    public static GerenciadorDeSalas gerenciador = new GerenciadorDeSalas();
    public static String nomeDaB3 = "B-3";
    public static String localDaB3 = "IME - Bloco B - Terreo";
    public static int capacidadeDaB3 = 60;
    public static Sala b3 = new Sala();
    public static String nomeDoAuditorio = "Auditorio Antonio Giglioli";
    public static String localDoAuditorio = "IME - Bloco A - Primeiro Andar";
    public static int capacidadeDoAuditorio = 95;
    public static String obsDoAuditorio = "Possui projetor LCD, rede com e sem fio e lousa eletronica";
    public static Sala auditorio = new Sala();
    public static Sala quentura = new Sala();
    public static Sala frescura = new Sala();
    public static Sala cloneMalignoDaB3 = new Sala();
    public static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void main(String[] args) {
        b3.setNome(nomeDaB3);
        b3.setLocal(localDaB3);
        b3.setCapacidade(capacidadeDaB3);
        auditorio.setNome(nomeDoAuditorio);
        auditorio.setLocal(localDoAuditorio);
        auditorio.setCapacidade(capacidadeDoAuditorio);
        auditorio.setObservacoes(obsDoAuditorio);
        quentura.setNome("b-143");
        frescura.setNome("b-115");
        cloneMalignoDaB3.setNome("B-3");
        case1();
        case2();
        case3();
        case4();
        gerenciador = new GerenciadorDeSalas();
        case5();
        case6();
        case7();
        case8();
        case8();
        case9();
        case10();
        case11();
    }

    public static void case1() { //testeAdicionaSalaChamada

        try {
            System.out.println("----------------------------- Teste 1 -----------------------------");
            gerenciador.adicionaSalaChamada(nomeDoAuditorio, capacidadeDoAuditorio, obsDoAuditorio);
            List<Sala> salas = gerenciador.listaDeSalas();
            for (Sala sala : salas) {
                System.out.println(sala.getNome() + " (" + sala.getObservacoes() + ")");
            }
        }
        catch(Throwable e){
            e.printStackTrace();
        }
    }

    public static void case2() { //testeAdicionaSala

        try {
            System.out.println("----------------------------- Teste 2 -----------------------------");
            gerenciador.adicionaSala(b3);
            List<Sala> salas = gerenciador.listaDeSalas();
            for (Sala sala : salas) {
                System.out.println(sala.getNome() + " (" + sala.getObservacoes() + ")");
            }
        }
        catch(Throwable e){
            e.printStackTrace();
        }
    }


    public static void case3() { //testAdcionaSalaComMesmoNome


        try {
            System.out.println("----------------------------- Teste 3 -----------------------------");
            gerenciador.adicionaSala(cloneMalignoDaB3);
            List<Sala> salas = gerenciador.listaDeSalas();
            for (Sala sala : salas) {
                System.out.println(sala.getNome() + " (" + sala.getObservacoes() + ")");
            }
        }
        catch(Throwable e){
            e.printStackTrace();
        }
    }

    public static void case4(){ //testAdcionaSalaChamadaComMesmoNome

        try {

            System.out.println("----------------------------- Teste 4 -----------------------------");
            gerenciador.adicionaSalaChamada(nomeDoAuditorio, capacidadeDoAuditorio, obsDoAuditorio);
            List<Sala> salas = gerenciador.listaDeSalas();
            for (Sala sala : salas) {
                System.out.println(sala.getNome() + " (" + sala.getObservacoes() + ")");
            }
        }
        catch(Throwable e){
            e.printStackTrace();
        }
    }
    public static void case5(){ //testAdicionaVariasSalas
        System.out.println("----------------------------- Teste 5 -----------------------------");
        try {
            gerenciador.adicionaSalaChamada(nomeDoAuditorio, capacidadeDoAuditorio, obsDoAuditorio);
            gerenciador.adicionaSala(b3);
            gerenciador.adicionaSala(frescura);
            gerenciador.adicionaSala(quentura);
            List<Sala> salas = gerenciador.listaDeSalas();
            for (Sala sala : salas) {
                System.out.println(sala.getNome() + " (" + sala.getObservacoes() + ")");
            }

        }
        catch(Throwable e){
            e.printStackTrace();
        }
    }

    public static void case6(){ //testRemoveSalas

        try{
            System.out.println("----------------------------- Teste 6 -----------------------------");
            gerenciador.removeSalaChamada(nomeDaB3);
            List<Sala> salas = gerenciador.listaDeSalas();
            for (Sala sala : salas) {
                System.out.println(sala.getNome() + " (" + sala.getObservacoes() + ")");
            }
        }
        catch(Throwable e){
            e.printStackTrace();
        }
    }

    public static void case7() { // testaCancelaReservaQueNaoExiste

        System.out.println("----------------------------- Teste 7 -----------------------------");

        try {
            Reserva reserva = gerenciador.reservaSalaChamada(nomeDoAuditorio,LocalDateTime.of(2021, 8, 16, 10, 0, 0), LocalDateTime.of(2021, 8, 16, 12, 0, 0));
            gerenciador.imprimeReservasDaSala(nomeDoAuditorio);
            gerenciador.cancelaReserva(reserva);
            gerenciador.imprimeReservasDaSala(nomeDoAuditorio);
            gerenciador.cancelaReserva(reserva);
            gerenciador.imprimeReservasDaSala(nomeDoAuditorio);
        }
        catch(Throwable e){
            e.printStackTrace();

        }
    }
    public static void case8() { //testaCriacaoDeReservaComFimAntesDoComeco
        try{
            System.out.println("----------------------------- Teste 8 -----------------------------");
            Reserva reserva = gerenciador.reservaSalaChamada(nomeDoAuditorio,LocalDateTime.of(2021, 8, 16, 12, 0, 0), LocalDateTime.of(2021, 8, 16, 10, 0, 0));
        }
        catch(Throwable e){
            e.printStackTrace();
        }
    }
    public static void case9(){ //testReservaClass
        System.out.println("----------------------------- Teste 9 -----------------------------");
        try{
            Reserva reserva = gerenciador.reservaSalaChamada(nomeDoAuditorio,LocalDateTime.of(2021, 8, 16, 10, 0, 0), LocalDateTime.of(2021, 8, 16, 12, 0, 0));
            Sala temp = reserva.getSala();
            LocalDateTime ini = reserva.getInicio();
            LocalDateTime fim = reserva.getFim();
            System.out.println(temp.getNome() +": " +  dateTimeFormat.format(ini) + " - "+dateTimeFormat.format(fim));
        }
        catch(Throwable e){
            e.printStackTrace();
        }
    }
    public static void case10(){ //testReservaComSalaQueNaoExiste
        System.out.println("----------------------------- Teste 10 -----------------------------");
        try{
            Reserva reserva = gerenciador.reservaSalaChamada("Desconhecido",LocalDateTime.of(2021, 8, 16, 10, 0, 0), LocalDateTime.of(2021, 8, 16, 12, 0, 0));
        }
        catch(Throwable e){
            e.printStackTrace();
        }
    }
    public static void case11(){ //testRemoveSalaQueNaoExiste
        System.out.println("----------------------------- Teste 11 -----------------------------");
        try{
            gerenciador.removeSalaChamada("Desconhecido");
        }
        catch(Throwable e){
            e.printStackTrace();
        }
    }
}
			