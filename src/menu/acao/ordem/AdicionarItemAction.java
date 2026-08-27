package menu.acao.ordem;

import dominio.OrdeServico;
import infra.Console;
import menu.MenuAction;
import oficina.OficinaController;

public class AdicionarItemAction extends MenuAction {
    private final OficinaController controller;
    private final int numeroOrdem;
    private final String codigoPeca;
    private final int quantidade;

    public AdicionarItemAction(Console console, OficinaController controller,
                               int numeroOrdem, String codigoPeca, int quantidade) {
        super(console);
        this.controller = controller;
        this.numeroOrdem = numeroOrdem;
        this.codigoPeca = codigoPeca;
        this.quantidade = quantidade;
    }

    @Override
    public void executar() {
        try {
            OrdeServico ordem = controller.adicionarItem(numeroOrdem, codigoPeca, quantidade);
            sucesso("Peça adicionada à OS-" + ordem.getNumero() + ".");
            console.escrever(ordem.detalhes());
        } catch (IllegalArgumentException e) {
            erro(e.getMessage());
        }
    }
}
