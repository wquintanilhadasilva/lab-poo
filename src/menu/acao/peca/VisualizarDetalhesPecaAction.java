package menu.acao.peca;

import dominio.Peca;
import infra.Console;
import menu.MenuAction;
import oficina.OficinaController;

public class VisualizarDetalhesPecaAction extends MenuAction {
    private final OficinaController controller;
    private final String codigo;

    public VisualizarDetalhesPecaAction(Console console, OficinaController controller, String codigo) {
        super(console);
        this.controller = controller;
        this.codigo = codigo;
    }

    @Override
    public void executar() {
        try {
            Peca peca = controller.buscarPeca(codigo);
            console.escrever(peca.detalhes());
        } catch (IllegalArgumentException e) {
            erro(e.getMessage());
        }
    }
}
