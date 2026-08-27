package menu.item.ordem;

import infra.Console;
import menu.MenuFormulario;
import menu.acao.ordem.ListarOrdensAction;
import oficina.OficinaController;

public class MenuListarOrdens extends MenuFormulario {
    private final OficinaController controller;

    public MenuListarOrdens(OficinaController controller, Console console) {
        super("Listar ordens de serviço", console);
        this.controller = controller;
    }

    @Override
    protected void coletarEExecutar() {
        new ListarOrdensAction(console, controller).executar();
    }
}
