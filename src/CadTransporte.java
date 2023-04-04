import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CadTransporte {
    private ArrayList<Item> itens;
    private ArrayList<Integer> veiculos;
    private ArrayList<Integer> rota;
    private LocalDateTime data;
    Dados dados = Dados.getInstance();
    DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public CadTransporte() {
        itens = new ArrayList<>();
    }

    public void setData() {
        this.data = LocalDateTime.now();
    }

    public LocalDateTime getData() {
        return data;
    }

    public String dataToString() {
        return data.format(formatoData);
    }

    public String rotaToString() {
        StringBuilder mensagem = new StringBuilder();
        for (Integer i : rota) {
            mensagem.append(dados.getDados(0, i) + ", ");
        }
        return mensagem.toString();
    }

    public void inserirMercadoria(Item item) {
        itens.add(item);
    }

    public void setRota(ArrayList<Integer> rota) {
        this.rota = rota;
    }

    public ArrayList<Integer> getRota() {
        return rota;
    }

    public ArrayList<Item> getItems() {
        return itens;
    }

    public double getPesoTransporte() {
        double pesoTotalCarga = 0;
        for (Item item : itens) {
            pesoTotalCarga = pesoTotalCarga + item.getPesoTotal();
        }
        return pesoTotalCarga / 1000;
    }

    public void limparListaItens() {
        itens.clear();
    }

    public int getTotalItens() {
        int total = 0;
        for (Item item : itens) {
            total = total + item.quantidade;
        }
        return total;
    }

    public void setVeiculos(ArrayList<Integer> veiculos) {
        this.veiculos = veiculos;
    }

    public String toString() {
        StringBuilder mensagem = new StringBuilder();
        int distanciaFinal = Calcula.distancia(rota, dados);
        DecimalFormat formatoDinheiro = new DecimalFormat("###,###.00");
        double custoFinal = Calcula.custoFinal(distanciaFinal, veiculos);
        mensagem.append("Rota: ");
        mensagem.append(rotaToString());
        mensagem.append("\nDistância total: " + distanciaFinal + " Km\n\n");
        mensagem.append("Itens no pedido:\n");
        for (Item item : itens) {
            mensagem.append(item.getNome() + " x " + item.getQuantidade() + "\n");
        }
        mensagem.append("\nPeso total do pedido: " + getPesoTransporte() + " T");
        mensagem.append("\n\nFrota necessária: \n");
        if (veiculos.get(0) == 1) {
            mensagem.append(veiculos.get(0) + " Caminhão Grande\n");
        } else if (veiculos.get(0) != 0)
            mensagem.append(veiculos.get(0) + " Caminhões Grandes\n");

        if (veiculos.get(1) == 1) {
            mensagem.append(veiculos.get(1) + " Caminhão Médio\n");
        } else if (veiculos.get(1) != 0)
            mensagem.append(veiculos.get(1) + " Caminhões Médios\n");

        if (veiculos.get(2) == 1) {
            mensagem.append(veiculos.get(2) + " Caminhão Pequeno\n");
        } else if (veiculos.get(2) != 0)
            mensagem.append(veiculos.get(2) + " Caminhões Pequenos");

        mensagem.append("\n\nCusto final: R$" + formatoDinheiro.format(custoFinal));
        mensagem.append("\nCusto médio unitário: R$" + formatoDinheiro.format(custoFinal / getTotalItens()));
        mensagem.append("\nCusto por trecho: " + formatoDinheiro.format(custoFinal / (getRota().size() - 1)));

        return mensagem.toString();
    }

}
