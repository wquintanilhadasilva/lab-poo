package menu;

import infra.Console;

/**
 * Especialização de Menu para operações com perguntas.
 * Template Method: cabeçalho → coleta/ação → pausa.
 */
public abstract class MenuFormulario extends Menu {

    protected MenuFormulario(String titulo, Console console) {
        super(titulo, console);
    }

    @Override
    protected final void executar() {
        exibirCabecalho();
        coletarEExecutar();
        pausar();
    }

    protected abstract void coletarEExecutar();
}
