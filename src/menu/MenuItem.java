package menu;

/**
 * Item de um menu de navegação.
 * Construtor e secao() são public: os pacotes menu.item.* montam as opções por domínio.
 * selecionar/temOpcao/formatar permanecem package-private: só o Menu (mesmo pacote) os usa.
 */
public class MenuItem {
    private final Integer opcao;
    private final String rotulo;
    private final MenuAction acao;

    public MenuItem(int opcao, String rotulo, MenuAction acao) {
        this.opcao = opcao;
        this.rotulo = rotulo;
        this.acao = acao;
    }

    private MenuItem(String tituloSecao) {
        this.opcao = null;
        this.rotulo = tituloSecao;
        this.acao = null;
    }

    public static MenuItem secao(String titulo) {
        return new MenuItem(titulo);
    }

    void selecionar() {
        if (acao != null) {
            acao.executar();
        }
    }

    boolean temOpcao(int opcao) {
        return this.opcao != null && this.opcao == opcao;
    }

    String formatar() {
        if (opcao == null) {
            return "\n--- " + rotulo + " ---";
        }
        return opcao + " - " + rotulo;
    }
}
