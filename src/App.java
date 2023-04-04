import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) {

        Menus menus = new Menus();
        try {
            menus.menuPrincipal();
        } catch (FileNotFoundException | InterruptedException e) {
            System.out.println("Algo inesperado aconteceu. Encerrando o sistema.");
        }

    }
}
