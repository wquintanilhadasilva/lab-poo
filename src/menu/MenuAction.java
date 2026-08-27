package menu;

import infra.Console;

/**
 * Contrato das ações do menu (Open/Closed + Polymorphism).
 * Novas operações entram como subclasses, sem alterar o Menu.
 */
public abstract class MenuAction {
    protected final Console console;

    protected MenuAction(Console console) {
        this.console = console;
    }

    public abstract void executar();

    protected void sucesso(String mensagem) {
        console.escrever("[OK] " + mensagem);
    }

    protected void erro(String mensagem) {
        console.escrever("[ERRO] " + mensagem);
    }
}
