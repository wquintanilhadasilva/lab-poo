package menu.item.ordem;

import infra.Console;
import menu.MenuFormulario;
import menu.acao.ordem.CriarOrdemAction;
import oficina.OficinaController;

import java.time.LocalDate;

public class MenuCriarOrdem extends MenuFormulario {
    private final OficinaController controller;

    public MenuCriarOrdem(OficinaController controller, Console console) {
        super("Criar ordem de serviço", console);
        this.controller = controller;
    }

    @Override
    protected void coletarEExecutar() {
        String descricao = console.lerTexto("Descrição do serviço: ");
        LocalDate data = console.lerData("Data do serviço (AAAA-MM-DD, ENTER = hoje): ");
        new CriarOrdemAction(console, controller, descricao, data).executar();
    }
}
