package menu.item.ordem;

import dominio.OrdeServico;
import infra.Console;
import menu.MenuFormulario;
import menu.acao.ordem.RemoverItemAction;
import menu.item.VisualizacaoOficina;
import oficina.OficinaController;

public class MenuRemoverItem extends MenuFormulario {
    private final OficinaController controller;

    public MenuRemoverItem(OficinaController controller, Console console) {
        super("Remover peça da ordem de serviço", console);
        this.controller = controller;
    }

    @Override
    protected void coletarEExecutar() {
        VisualizacaoOficina.ordens(console, controller);
        console.escrever("");
        int numeroOrdem = console.lerInteiro("Número da ordem de serviço: ");
        try {
            OrdeServico ordem = controller.buscarOrdem(numeroOrdem);
            console.escrever(ordem.detalhes());
        } catch (IllegalArgumentException e) {
            console.escrever("[ERRO] " + e.getMessage());
            return;
        }
        String codigoPeca = console.lerTexto("Código da peça a remover: ");
        new RemoverItemAction(console, controller, numeroOrdem, codigoPeca).executar();
    }
}
