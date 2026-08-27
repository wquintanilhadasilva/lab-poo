package menu.acao.ordem;

import dominio.OrdeServico;
import infra.Console;
import menu.MenuAction;
import oficina.OficinaController;

import java.util.List;

public class ListarOrdensAction extends MenuAction {
    private final OficinaController controller;

    public ListarOrdensAction(Console console, OficinaController controller) {
        super(console);
        this.controller = controller;
    }

    @Override
    public void executar() {
        List<OrdeServico> ordens = controller.listarOrdens();
        if (ordens.isEmpty()) {
            console.escrever("Nenhuma ordem de serviço cadastrada.");
            return;
        }
        console.escrever("Resumo das ordens de serviço:");
        for (OrdeServico ordem : ordens) {
            console.escrever(ordem.resumo());
        }
    }
}
