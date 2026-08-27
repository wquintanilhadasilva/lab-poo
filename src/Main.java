import infra.Console;
import menu.MenuPrincipal;
import oficina.OficinaController;

/**
 * Ponto de entrada. Apenas monta as dependências e inicia o menu.
 * A orquestração das regras fica no OficinaController (GRASP Controller).
 */
public class Main {
    public static void main(String[] args) {
        Console console = new Console();
        OficinaController controller = new OficinaController();
        popularExemplos(controller);
        new MenuPrincipal(controller, console).exibir();
    }


    private static void popularExemplos(OficinaController controller) {
        controller.incluirPeca("FLT001", "Filtro de óleo", 45.90);
        controller.incluirPeca("VEL002", "Vela de ignição", 22.50);
        controller.incluirPeca("PDG003", "Pastilha de freio", 89.00);
    }
}
