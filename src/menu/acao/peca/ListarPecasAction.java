package menu.acao.peca;

import dominio.Peca;
import infra.Console;
import menu.MenuAction;
import oficina.OficinaController;

import java.util.List;

public class ListarPecasAction extends MenuAction {
    private final OficinaController controller;

    public ListarPecasAction(Console console, OficinaController controller) {
        super(console);
        this.controller = controller;
    }

    @Override
    public void executar() {
        List<Peca> pecas = controller.listarPecas();
        if (pecas.isEmpty()) {
            console.escrever("Nenhuma peça cadastrada.");
            return;
        }
        console.escrever("Resumo das peças:");
        for (Peca peca : pecas) {
            console.escrever(peca.resumo());
        }
    }
}
