package menu.acao.ordem;

import dominio.OrdeServico;
import infra.Console;
import menu.MenuAction;
import oficina.OficinaController;

public class VisualizarDetalhesOrdemAction extends MenuAction {
    private final OficinaController controller;
    private final int numeroOrdem;

    public VisualizarDetalhesOrdemAction(Console console, OficinaController controller, int numeroOrdem) {
        super(console);
        this.controller = controller;
        this.numeroOrdem = numeroOrdem;
    }

    @Override
    public void executar() {
        try {
            OrdeServico ordem = controller.buscarOrdem(numeroOrdem);
            console.escrever(ordem.detalhes());
        } catch (IllegalArgumentException e) {
            erro(e.getMessage());
        }
    }
}
