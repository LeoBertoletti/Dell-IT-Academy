import java.util.ArrayList;
import java.util.List;

public class Dados {
    private ArrayList<List<String>> dados = new ArrayList<>();
    private static Dados instance;
    private ArrayList<Integer> rotas = new ArrayList<>();
    private ArrayList<CadTransporte> historico = new ArrayList<>();

    public static Dados getInstance() {
        if (instance == null)
            instance = new Dados();

        return instance;
    }

    public void inserirHistorico(CadTransporte transporte) {
        this.historico.add(transporte);
    }

    public ArrayList<CadTransporte> getHistorico() {
        return historico;
    }

    public CadTransporte consultarHistorico(int index) {
        return historico.get(index);
    }

    public void setDados(ArrayList<List<String>> dados) {
        this.dados = dados;
    }

    public ArrayList<Integer> getRotas() {
        return rotas;
    }

    public void setRotas(ArrayList<Integer> rotas) {
        this.rotas = rotas;
    }

    public void addRota(Integer rota) {
        this.rotas.add(rota);
    }

    public List<String> getDados(int row) {
        return dados.get(row);
    }

    public String getDados(int row, int col) {
        return dados.get(row).get(col - 1);
    }

    // imprime uma lista numerada das cidades
    public void printCidades() {
        List<String> listaCidades = getDados(0);
        for (int i = 0; i < listaCidades.size(); i = i + 2) {
            System.out.printf((i + 1) + " - %-30s" + (i + 2) + " - %-30s\n", listaCidades.get(i),
                    listaCidades.get(i + 1));
        }
    }

}
