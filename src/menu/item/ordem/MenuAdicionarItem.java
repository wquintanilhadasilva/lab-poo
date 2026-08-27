package menu.item.ordem;

import infra.Console;
import menu.MenuFormulario;
import menu.acao.ordem.AdicionarItemAction;
import menu.item.VisualizacaoOficina;
import oficina.OficinaController;

public class MenuAdicionarItem extends MenuFormulario {
    private final OficinaController controller;

    public MenuAdicionarItem(OficinaController controller, Console console) {
        super("Adicionar peça à ordem de serviço", console);
        this.controller = controller;
    }

    @Override
    protected void coletarEExecutar() {
        VisualizacaoOficina.ordens(console, controller);
        VisualizacaoOficina.pecas(console, controller);
        console.escrever("");
        int numeroOrdem = console.lerInteiro("Número da ordem de serviço: ");
        String codigoPeca = console.lerTexto("Código da peça: ");
        int quantidade = console.lerInteiro("Quantidade: ");
        new AdicionarItemAction(console, controller, numeroOrdem, codigoPeca, quantidade).executar();
    }
}
