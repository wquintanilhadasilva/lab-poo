package menu.item.peca;

import infra.Console;
import menu.MenuFormulario;
import menu.acao.peca.VisualizarDetalhesPecaAction;
import menu.item.VisualizacaoOficina;
import oficina.OficinaController;

public class MenuVisualizarDetalhesPeca extends MenuFormulario {
    private final OficinaController controller;

    public MenuVisualizarDetalhesPeca(OficinaController controller, Console console) {
        super("Visualizar detalhes da peça", console);
        this.controller = controller;
    }

    @Override
    protected void coletarEExecutar() {
        VisualizacaoOficina.pecas(console, controller);
        console.escrever("");
        String codigo = console.lerTexto("Código da peça: ");
        new VisualizarDetalhesPecaAction(console, controller, codigo).executar();
    }
}
