package menu.item.peca;

import infra.Console;
import menu.MenuFormulario;
import menu.acao.peca.AlterarPrecoAction;
import menu.item.VisualizacaoOficina;
import oficina.OficinaController;

public class MenuAlterarPreco extends MenuFormulario {
    private final OficinaController controller;

    public MenuAlterarPreco(OficinaController controller, Console console) {
        super("Alterar preço de peça", console);
        this.controller = controller;
    }

    @Override
    protected void coletarEExecutar() {
        VisualizacaoOficina.pecas(console, controller);
        console.escrever("");
        String codigo = console.lerTexto("Código da peça: ");
        console.escrever("Informe o percentual de ajuste (positivo aumenta, negativo reduz).");
        double percentual = console.lerDecimal("Percentual: ");
        new AlterarPrecoAction(console, controller, codigo, percentual).executar();
    }
}
