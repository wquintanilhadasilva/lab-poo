package menu.acao;

import infra.Console;
import menu.Menu;
import menu.MenuAction;

public class AbrirMenuAction extends MenuAction {
    private final Menu proximo;

    public AbrirMenuAction(Console console, Menu proximo) {
        super(console);
        this.proximo = proximo;
    }

    @Override
    public void executar() {
        proximo.exibir();
    }
}
