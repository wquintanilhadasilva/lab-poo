package oficina;

import dominio.OrdeServico;
import dominio.Peca;

import java.time.LocalDate;
import java.util.List;

/**
 * GRASP Controller: orquestra os casos de uso sem conhecer o terminal.
 * Os repositórios são package-private; menus de outros pacotes só falam com este controller.
 */
public class OficinaController {
    private final PecaRepositorio pecas;
    private final OrdeServicoRepositorio ordens;

    public OficinaController() {
        this.pecas = new PecaRepositorio();
        this.ordens = new OrdeServicoRepositorio();
    }

    public Peca incluirPeca(String codigo, String descricao, double precoBasico) {
        if (pecas.existe(codigo)) {
            throw new IllegalArgumentException("Já existe uma peça com o código " + codigo + ".");
        }
        Peca peca = new Peca(codigo, descricao, precoBasico);
        pecas.salvar(peca);
        return peca;
    }

    public Peca alterarPreco(String codigo, double percentual) {
        Peca peca = exigirPeca(codigo);
        peca.ajustarPreco(percentual);
        return peca;
    }

    public OrdeServico criarOrdem(String descricao, LocalDate data) {
        OrdeServico ordem = new OrdeServico(descricao, data);
        ordens.salvar(ordem);
        return ordem;
    }

    public OrdeServico adicionarItem(int numeroOrdem, String codigoPeca, int quantidade) {
        OrdeServico ordem = exigirOrdem(numeroOrdem);
        Peca peca = exigirPeca(codigoPeca);
        ordem.adicionarPeca(peca, quantidade);
        return ordem;
    }

    public OrdeServico removerItem(int numeroOrdem, String codigoPeca) {
        OrdeServico ordem = exigirOrdem(numeroOrdem);
        ordem.removerPeca(codigoPeca);
        return ordem;
    }

    public List<OrdeServico> listarOrdens() {
        return ordens.listar();
    }

    public List<Peca> listarPecas() {
        return pecas.listar();
    }

    public OrdeServico buscarOrdem(int numero) {
        return exigirOrdem(numero);
    }

    public Peca buscarPeca(String codigo) {
        return exigirPeca(codigo);
    }

    private Peca exigirPeca(String codigo) {
        Peca peca = pecas.buscarPorCodigo(codigo);
        if (peca == null) {
            throw new IllegalArgumentException("Peça não encontrada: " + codigo);
        }
        return peca;
    }

    private OrdeServico exigirOrdem(int numero) {
        OrdeServico ordem = ordens.buscarPorNumero(numero);
        if (ordem == null) {
            throw new IllegalArgumentException("Ordem de serviço não encontrada: " + numero);
        }
        return ordem;
    }

}
