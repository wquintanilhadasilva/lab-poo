package menu.acao;

import infra.Console;
import menu.Menu;
import menu.MenuAction;

public class SairAction extends MenuAction {
    private final Menu menu;

    public SairAction(Console console, Menu menu) {
        super(console);
        this.menu = menu;
    }

    @Override
    public void executar() {
        console.escrever("Encerrando o sistema...");
        menu.encerrar();
    }
}
