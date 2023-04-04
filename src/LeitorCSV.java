import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LeitorCSV {
    private Scanner scan;

    // le o arquivo CSV
    public LeitorCSV(String caminhoArquivo) throws FileNotFoundException {
        scan = new Scanner(new File(caminhoArquivo));
    }

    // tranforma os dados obtidos do CSV em um ArrayList de duas dimensoes
    public ArrayList<List<String>> getCsvData() {
        ArrayList<List<String>> dados = new ArrayList<>();
        scan.useDelimiter(";");

        while (scan.hasNext()) {
            String[] linha = scan.nextLine().split(";");
            dados.add(Arrays.asList(linha));
        }
        return dados;
    }

}