package menu.item.peca;

import infra.Console;
import menu.MenuFormulario;
import menu.acao.peca.IncluirPecaAction;
import oficina.OficinaController;

public class MenuIncluirPeca extends MenuFormulario {
    private final OficinaController controller;

    public MenuIncluirPeca(OficinaController controller, Console console) {
        super("Incluir nova peça", console);
        this.controller = controller;
    }

    @Override
    protected void coletarEExecutar() {
        String codigo = console.lerTexto("Código da peça: ");
        String descricao = console.lerTexto("Descrição: ");
        double precoBasico = console.lerDecimal("Preço básico: ");
        new IncluirPecaAction(console, controller, codigo, descricao, precoBasico).executar();
    }
}
