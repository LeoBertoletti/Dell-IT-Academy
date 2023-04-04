public class Item {
    String nome;
    double peso;
    int quantidade;

    public Item(String nome, double peso, int quantidade) {
        this.nome = nome;
        this.peso = peso;
        this.quantidade = quantidade;
    }

    public double getPeso() {
        return peso;
    }

    public double getPesoTotal() {
        return peso * quantidade;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
