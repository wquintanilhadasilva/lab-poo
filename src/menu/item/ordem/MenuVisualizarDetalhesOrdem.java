package menu.item.ordem;

import infra.Console;
import menu.MenuFormulario;
import menu.acao.ordem.VisualizarDetalhesOrdemAction;
import menu.item.VisualizacaoOficina;
import oficina.OficinaController;

public class MenuVisualizarDetalhesOrdem extends MenuFormulario {
    private final OficinaController controller;

    public MenuVisualizarDetalhesOrdem(OficinaController controller, Console console) {
        super("Visualizar detalhes da ordem de serviço", console);
        this.controller = controller;
    }

    @Override
    protected void coletarEExecutar() {
        VisualizacaoOficina.ordens(console, controller);
        console.escrever("");
        int numeroOrdem = console.lerInteiro("Número da ordem de serviço: ");
        new VisualizarDetalhesOrdemAction(console, controller, numeroOrdem).executar();
    }
}
