package dominio;

/**
 * Information Expert da peça: guarda dados e o comportamento de ajustar preço.
 *
 * Modificadores demonstrados nesta classe:
 * - private: estado encapsulado e validação interna
 * - public: contrato usado por outros pacotes
 * - protected: gancho para subclasses especializarem o ajuste
 */
public class Peca {
    private final String codigo;
    private final String descricao;
    private final double precoBasico;
    private double precoAtual;

    public Peca(String codigo, String descricao, double precoBasico) {
        Validador.exigirTexto(codigo, "Código da peça");
        Validador.exigirTexto(descricao, "Descrição da peça");
        Validador.exigirNaoNegativo(precoBasico, "Preço básico");
        this.codigo = codigo.trim().toUpperCase();
        this.descricao = descricao.trim();
        this.precoBasico = precoBasico;
        this.precoAtual = precoBasico;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPrecoBasico() {
        return precoBasico;
    }

    public double getPrecoAtual() {
        return precoAtual;
    }

    /**
     * Comportamento público de ajuste. O percentual pode ser negativo (desconto).
     * Template Method: calcula e aplica por ganchos protected.
     */
    public final void ajustarPreco(double percentual) {
        double novoPreco = calcularPrecoAjustado(percentual);
        aplicarAjuste(novoPreco);
    }

    protected double calcularPrecoAjustado(double percentual) {
        return precoAtual * (1.0 + percentual / 100.0);
    }

    protected void aplicarAjuste(double novoPreco) {
        validarPreco(novoPreco);
        this.precoAtual = novoPreco;
    }

    private void validarPreco(double preco) {
        Validador.exigirNaoNegativo(preco, "Preço ajustado");
    }

    public String resumo() {
        return codigo + " - " + descricao
                + " | Básico: " + Formatador.moeda(precoBasico)
                + " | Atual: " + Formatador.moeda(precoAtual);
    }

    public String detalhes() {
        StringBuilder texto = new StringBuilder();
        texto.append("======================================\n");
        texto.append("Peça ").append(codigo).append("\n");
        texto.append("Descrição: ").append(descricao).append("\n");
        texto.append("Preço básico: ").append(Formatador.moeda(precoBasico)).append("\n");
        texto.append("Preço atual: ").append(Formatador.moeda(precoAtual)).append("\n");
        texto.append("======================================");
        return texto.toString();
    }

    @Override
    public String toString() {
        return resumo();
    }
}
