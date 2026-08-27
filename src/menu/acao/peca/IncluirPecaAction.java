package menu.acao.peca;

import infra.Console;
import menu.MenuAction;
import oficina.OficinaController;

public class IncluirPecaAction extends MenuAction {
    private final OficinaController controller;
    private final String codigo;
    private final String descricao;
    private final double precoBasico;

    public IncluirPecaAction(Console console, OficinaController controller,
                             String codigo, String descricao, double precoBasico) {
        super(console);
        this.controller = controller;
        this.codigo = codigo;
        this.descricao = descricao;
        this.precoBasico = precoBasico;
    }

    @Override
    public void executar() {
        try {
            controller.incluirPeca(codigo, descricao, precoBasico);
            sucesso("Peça " + codigo + " incluída.");
        } catch (IllegalArgumentException e) {
            erro(e.getMessage());
        }
    }
}
