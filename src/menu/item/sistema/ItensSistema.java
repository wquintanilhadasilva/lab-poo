package menu.item.sistema;

import infra.Console;
import menu.Menu;
import menu.MenuItem;
import menu.acao.SairAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Centraliza os itens de menu do sistema (sair e seções gerais).
 */
public final class ItensSistema {

    private ItensSistema() {
    }

    public static List<MenuItem> criar(Menu menuPrincipal, Console console) {
        List<MenuItem> itens = new ArrayList<MenuItem>();
        itens.add(MenuItem.secao("Sistema"));
        itens.add(new MenuItem(0, "Sair", new SairAction(console, menuPrincipal)));
        return itens;
    }
}
