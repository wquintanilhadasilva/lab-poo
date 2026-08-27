package menu.item.ordem;

import infra.Console;
import menu.MenuItem;
import menu.acao.AbrirMenuAction;
import oficina.OficinaController;

import java.util.ArrayList;
import java.util.List;

/**
 * Centraliza os itens de menu do domínio ordem de serviço.
 */
public final class ItensOrdem {

    private ItensOrdem() {
    }

    public static List<MenuItem> criar(OficinaController controller, Console console) {
        List<MenuItem> itens = new ArrayList<MenuItem>();
        itens.add(MenuItem.secao("Ordens de serviço"));
        itens.add(new MenuItem(5, "Criar ordem de serviço",
                new AbrirMenuAction(console, new MenuCriarOrdem(controller, console))));
        itens.add(new MenuItem(6, "Adicionar peça à ordem de serviço",
                new AbrirMenuAction(console, new MenuAdicionarItem(controller, console))));
        itens.add(new MenuItem(7, "Remover peça da ordem de serviço",
                new AbrirMenuAction(console, new MenuRemoverItem(controller, console))));
        itens.add(new MenuItem(8, "Listar ordens de serviço",
                new AbrirMenuAction(console, new MenuListarOrdens(controller, console))));
        itens.add(new MenuItem(9, "Visualizar detalhes de ordem de serviço",
                new AbrirMenuAction(console, new MenuVisualizarDetalhesOrdem(controller, console))));
        return itens;
    }
}
