package menu.item.peca;

import infra.Console;
import menu.MenuFormulario;
import menu.acao.peca.ListarPecasAction;
import oficina.OficinaController;

public class MenuListarPecas extends MenuFormulario {
    private final OficinaController controller;

    public MenuListarPecas(OficinaController controller, Console console) {
        super("Listar peças", console);
        this.controller = controller;
    }

    @Override
    protected void coletarEExecutar() {
        new ListarPecasAction(console, controller).executar();
    }
}
