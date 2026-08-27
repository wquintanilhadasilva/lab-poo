package dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Information Expert da ordem: conhece peças, valores e calcula totais.
 *
 * Creator: a própria ordem cria seus ItemOrdemServico.
 */
public class OrdeServico {
    private final int numero;
    private final String descricao;
    private final LocalDate data;
    private final List<ItemOrdemServico> itens;

    public OrdeServico(String descricao, LocalDate data) {
        Validador.exigirTexto(descricao, "Descrição do serviço");
        if (data == null) {
            throw new IllegalArgumentException("Data do serviço é obrigatória.");
        }
        this.numero = SequenciaOrdem.proxima();
        this.descricao = descricao.trim();
        this.data = data;
        this.itens = new ArrayList<ItemOrdemServico>();
    }

    public int getNumero() {
        return numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void adicionarPeca(Peca peca, int quantidade) {
        if (peca == null) {
            throw new IllegalArgumentException("Peça é obrigatória.");
        }
        ItemOrdemServico existente = localizarItem(peca.getCodigo());
        if (existente != null) {
            existente.adicionarQuantidade(quantidade);
            return;
        }
        itens.add(new ItemOrdemServico(peca, quantidade));
    }

    public void removerPeca(String codigoPeca) {
        Validador.exigirTexto(codigoPeca, "Código da peça");
        ItemOrdemServico item = localizarItem(codigoPeca);
        if (item == null) {
            throw new IllegalArgumentException(
                    "A peça " + codigoPeca + " não está nesta ordem de serviço.");
        }
        itens.remove(item);
    }

    public double getTotal() {
        return calcularTotal();
    }

    public int getQuantidadeItens() {
        return itens.size();
    }

    public String resumo() {
        return "OS-" + numero
                + " | " + Formatador.data(data)
                + " | " + descricao
                + " | " + itens.size() + " item(ns)"
                + " | Total: " + Formatador.moeda(getTotal());
    }

    public String detalhes() {
        StringBuilder texto = new StringBuilder();
        texto.append("======================================\n");
        texto.append("Ordem de Serviço nº ").append(numero).append("\n");
        texto.append("Data: ").append(Formatador.data(data)).append("\n");
        texto.append("Descrição: ").append(descricao).append("\n");
        texto.append("--------------------------------------\n");
        if (itens.isEmpty()) {
            texto.append("Nenhuma peça adicionada.\n");
        } else {
            texto.append("Peças:\n");
            for (ItemOrdemServico item : itens) {
                texto.append(item.formatar()).append("\n");
            }
        }
        texto.append("--------------------------------------\n");
        texto.append("Quantidade de itens: ").append(itens.size()).append("\n");
        texto.append("Total: ").append(Formatador.moeda(getTotal())).append("\n");
        texto.append("======================================");
        return texto.toString();
    }

    protected double calcularTotal() {
        double total = 0;
        for (ItemOrdemServico item : itens) {
            total += item.subtotal();
        }
        return total;
    }

    private ItemOrdemServico localizarItem(String codigoPeca) {
        for (ItemOrdemServico item : itens) {
            if (item.temCodigo(codigoPeca)) {
                return item;
            }
        }
        return null;
    }
}
