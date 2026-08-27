package menu;

import infra.Console;
import menu.item.ordem.ItensOrdem;
import menu.item.peca.ItensPeca;
import menu.item.sistema.ItensSistema;
import oficina.OficinaController;

public class MenuPrincipal extends Menu {

    public MenuPrincipal(OficinaController controller, Console console) {
        super("Oficina - Menu Principal", console);
        adicionarItens(ItensPeca.criar(controller, console));
        adicionarItens(ItensOrdem.criar(controller, console));
        adicionarItens(ItensSistema.criar(this, console));
    }

    @Override
    protected void executar() {
        exibirItensEProcessarEscolha();
    }
}
