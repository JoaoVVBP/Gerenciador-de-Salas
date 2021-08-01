public class Sala {
    String nomeDaSala;
    String descricao;
    String local;
    int capacidadeMaxima;

    /*
    Construtor da classe Sala
    @param nomeDaSala: nome da sala a ser criada
    @param capacidadeMaxima: capacidade maxima da sala
    @param descrição: descrição da sala
    */
    public Sala(String nomeDaSala, int capacidadeMaxima, String descricao) {
        this.nomeDaSala = nomeDaSala;
        this.descricao = descricao;
        this.capacidadeMaxima = capacidadeMaxima;
    }
    
    //Construtor padrão
    public Sala(){}
}
