package menu.acao.ordem;

import dominio.OrdeServico;
import infra.Console;
import menu.MenuAction;
import oficina.OficinaController;

public class RemoverItemAction extends MenuAction {
    private final OficinaController controller;
    private final int numeroOrdem;
    private final String codigoPeca;

    public RemoverItemAction(Console console, OficinaController controller,
                             int numeroOrdem, String codigoPeca) {
        super(console);
        this.controller = controller;
        this.numeroOrdem = numeroOrdem;
        this.codigoPeca = codigoPeca;
    }

    @Override
    public void executar() {
        try {
            OrdeServico ordem = controller.removerItem(numeroOrdem, codigoPeca);
            sucesso("Peça removida da OS-" + ordem.getNumero() + ".");
            console.escrever(ordem.detalhes());
        } catch (IllegalArgumentException e) {
            erro(e.getMessage());
        }
    }
}
