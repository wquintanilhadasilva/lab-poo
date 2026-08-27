package menu;

import infra.Console;

import java.util.ArrayList;
import java.util.List;

/**
 * Menu base. Subclasses especializam executar() (perguntas ou navegação).
 *
 * public    - exibir(), encerrar(): contrato usado por outros pacotes
 * protected - console, adicionarItem, adicionarItens, executar: disponíveis às subclasses
 * private   - titulo, itens, busca: detalhes internos
 */
public abstract class Menu {
    private final String titulo;
    private final List<MenuItem> itens;
    private boolean emExecucao;

    protected final Console console;

    protected Menu(String titulo, Console console) {
        this.titulo = titulo;
        this.console = console;
        this.itens = new ArrayList<MenuItem>();
        this.emExecucao = true;
    }

    protected final void adicionarItem(MenuItem item) {
        itens.add(item);
    }

    protected final void adicionarItens(List<MenuItem> novos) {
        for (MenuItem item : novos) {
            adicionarItem(item);
        }
    }

    public final void exibir() {
        executar();
    }

    protected abstract void executar();

    protected void exibirCabecalho() {
        console.escrever("");
        console.escrever("=== " + titulo + " ===");
    }

    protected void exibirItensEProcessarEscolha() {
        emExecucao = true;
        while (emExecucao) {
            exibirCabecalho();
            for (MenuItem item : itens) {
                console.escrever(item.formatar());
            }
            int opcao = console.lerInteiro("Escolha uma opção: ");
            MenuItem escolhido = buscar(opcao);
            if (escolhido == null) {
                console.escrever("Opção inválida.");
                continue;
            }
            escolhido.selecionar();
        }
    }

    protected void pausar() {
        console.lerTexto("Pressione ENTER para continuar...");
    }

    public void encerrar() {
        emExecucao = false;
    }

    private MenuItem buscar(int opcao) {
        for (MenuItem item : itens) {
            if (item.temOpcao(opcao)) {
                return item;
            }
        }
        return null;
    }
}
