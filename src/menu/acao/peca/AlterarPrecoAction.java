package menu.acao.peca;

import dominio.Peca;
import infra.Console;
import menu.MenuAction;
import oficina.OficinaController;

public class AlterarPrecoAction extends MenuAction {
    private final OficinaController controller;
    private final String codigo;
    private final double percentual;

    public AlterarPrecoAction(Console console, OficinaController controller,
                              String codigo, double percentual) {
        super(console);
        this.controller = controller;
        this.codigo = codigo;
        this.percentual = percentual;
    }

    @Override
    public void executar() {
        try {
            Peca peca = controller.alterarPreco(codigo, percentual);
            sucesso("Preço de " + peca.getCodigo() + " atualizado para o valor atual da peça.");
            console.escrever("  " + peca.resumo());
        } catch (IllegalArgumentException e) {
            erro(e.getMessage());
        }
    }
}
