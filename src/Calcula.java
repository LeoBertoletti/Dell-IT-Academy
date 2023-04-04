import java.util.ArrayList;
import java.util.Collections;

public class Calcula {
    private static Calcula instance;
    private static double custoPequenoPorte = 4.87;
    private static double custoMedioPorte = 11.92;
    private static double custoGrandePorte = 27.44;

    // verifica se uma instancia da classe ja foi criada
    public static Calcula getInstance() {
        if (instance == null)
            instance = new Calcula();
        return instance;
    }

    // recebe uma rota(Arraylist de int) e os dados do CSV, busca nos dados usando
    // os ints do Arraylist como index.
    public static int distancia(ArrayList<Integer> rota, Dados dados) {
        int distancia = 0;
        for (int i = 0; i < rota.size() - 1; i++) {
            distancia = distancia + Integer.parseInt(dados.getDados(rota.get(i), rota.get(i + 1)));
        }
        return distancia;
    }

    public static double custoPequenoPorte(int distancia) {
        return distancia * custoPequenoPorte;
    }

    public static double custoMedioPorte(int distancia) {
        return distancia * custoMedioPorte;
    }

    public static double custoGrandePorte(int distancia) {
        return distancia * custoGrandePorte;
    }

    // recebe a frota mais eficiente e calcula o custo total dos caminhoes
    // necessarios para o transporte
    public static double custoFinal(int distancia, ArrayList<Integer> frota) {
        double custoPequeno = custoPequenoPorte(distancia) * frota.get(2);
        double custoMedio = custoMedioPorte(distancia) * frota.get(1);
        double custoGrande = custoGrandePorte(distancia) * frota.get(0);
        return custoPequeno + custoMedio + custoGrande;
    }

    /**
     * retorna a frota mais eficiente para a carga transportada
     * 
     * @param pesoCarga peso da carga
     * @return retorna um Arraylist de inteiros com a combinação mais eficiente
     */
    public static ArrayList<Integer> caminhoesNecessarios(int pesoCarga) {
        ArrayList<Double> custos = new ArrayList<>();
        ArrayList<ArrayList<Integer>> combinacoes = new ArrayList<>();
        ArrayList<Integer> caminhoes = new ArrayList<>();
        int nGrandePorte;
        int nMedioPorte;
        int nPequenoPorte;

        if (pesoCarga % 10 == 9 || pesoCarga % 10 == 3) {
            pesoCarga = pesoCarga + 1;
        }
        // confere todas as combinações possiveis, calcula o custo de cada uma e
        // armazena a que for mais barata.
        for (nGrandePorte = pesoCarga / 10; nGrandePorte >= 0; nGrandePorte--) {
            for (nMedioPorte = (pesoCarga - nGrandePorte * 10) / 4; nMedioPorte >= 0; nMedioPorte--) {
                nPequenoPorte = pesoCarga - nGrandePorte * 10 - nMedioPorte * 4;
                if (nPequenoPorte % 1 == 0) {
                    caminhoes = new ArrayList<>();
                    caminhoes.add(nGrandePorte);
                    caminhoes.add(nMedioPorte);
                    caminhoes.add(nPequenoPorte);
                    combinacoes.add(caminhoes);
                    custos.add(caminhoes.get(0) * custoGrandePorte + caminhoes.get(1) * custoMedioPorte +
                            caminhoes.get(2) * custoPequenoPorte);
                }
            }
        }
        int indexMenorCusto = custos.indexOf(Collections.min(custos));
        return combinacoes.get(indexMenorCusto);
    }

}
