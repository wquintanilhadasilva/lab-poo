package dominio;

/**
 * package-private: item existe só dentro de uma OrdeServico.
 * Outros pacotes não acessam esta classe; veem o detalhe via OrdeServico.detalhes().
 */
class ItemOrdemServico {
    private final String codigoPeca;
    private final String descricaoPeca;
    private final double valorUnitario;
    private int quantidade;

    ItemOrdemServico(Peca peca, int quantidade) {
        Validador.exigirPositivo(quantidade, "Quantidade");
        this.codigoPeca = peca.getCodigo();
        this.descricaoPeca = peca.getDescricao();
        this.valorUnitario = peca.getPrecoAtual();
        this.quantidade = quantidade;
    }

    boolean temCodigo(String codigo) {
        return codigoPeca.equalsIgnoreCase(codigo.trim());
    }

    void adicionarQuantidade(int extra) {
        Validador.exigirPositivo(extra, "Quantidade");
        this.quantidade += extra;
    }

    double subtotal() {
        return valorUnitario * quantidade;
    }

    String formatar() {
        return "  " + codigoPeca + " - " + descricaoPeca
                + " | " + quantidade + " x " + Formatador.moeda(valorUnitario)
                + " = " + Formatador.moeda(subtotal());
    }
}
