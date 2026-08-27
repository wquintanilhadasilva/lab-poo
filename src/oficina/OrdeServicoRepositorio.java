package oficina;

import dominio.OrdeServico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * package-private: persistência em memória escondida do restante do sistema.
 */
class OrdeServicoRepositorio {
    private final List<OrdeServico> ordens = new ArrayList<OrdeServico>();

    void salvar(OrdeServico ordem) {
        ordens.add(ordem);
    }

    OrdeServico buscarPorNumero(int numero) {
        for (OrdeServico ordem : ordens) {
            if (ordem.getNumero() == numero) {
                return ordem;
            }
        }
        return null;
    }

    List<OrdeServico> listar() {
        return Collections.unmodifiableList(ordens);
    }
}
