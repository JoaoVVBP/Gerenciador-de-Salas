public class Sala {
    String nomeDaSala;
    String descricao;
    String local; //?
    int capacidadeMaxima;

    public Sala(){}

    public Sala(String nomeDaSala, int capacidadeMaxima, String descricao) {
        this.nomeDaSala = nomeDaSala;
        this.descricao = descricao;
        this.capacidadeMaxima = capacidadeMaxima;
    }
    
}
