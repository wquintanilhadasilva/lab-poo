package menu.item.peca;

import infra.Console;
import menu.MenuItem;
import menu.acao.AbrirMenuAction;
import oficina.OficinaController;

import java.util.ArrayList;
import java.util.List;

/**
 * Centraliza os itens de menu do domínio peça.
 */
public final class ItensPeca {

    private ItensPeca() {
    }

    public static List<MenuItem> criar(OficinaController controller, Console console) {
        List<MenuItem> itens = new ArrayList<MenuItem>();
        itens.add(MenuItem.secao("Peças"));
        itens.add(new MenuItem(1, "Incluir nova peça",
                new AbrirMenuAction(console, new MenuIncluirPeca(controller, console))));
        itens.add(new MenuItem(2, "Alterar preço de peça",
                new AbrirMenuAction(console, new MenuAlterarPreco(controller, console))));
        itens.add(new MenuItem(3, "Listar peças",
                new AbrirMenuAction(console, new MenuListarPecas(controller, console))));
        itens.add(new MenuItem(4, "Visualizar detalhes de peça",
                new AbrirMenuAction(console, new MenuVisualizarDetalhesPeca(controller, console))));
        return itens;
    }
}
