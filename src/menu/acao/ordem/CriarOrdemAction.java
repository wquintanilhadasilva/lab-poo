package menu.acao.ordem;

import dominio.OrdeServico;
import infra.Console;
import menu.MenuAction;
import oficina.OficinaController;

import java.time.LocalDate;

public class CriarOrdemAction extends MenuAction {
    private final OficinaController controller;
    private final String descricao;
    private final LocalDate data;

    public CriarOrdemAction(Console console, OficinaController controller,
                            String descricao, LocalDate data) {
        super(console);
        this.controller = controller;
        this.descricao = descricao;
        this.data = data;
    }

    @Override
    public void executar() {
        try {
            OrdeServico ordem = controller.criarOrdem(descricao, data);
            sucesso("Ordem de serviço OS-" + ordem.getNumero() + " criada.");
        } catch (IllegalArgumentException e) {
            erro(e.getMessage());
        }
    }
}
