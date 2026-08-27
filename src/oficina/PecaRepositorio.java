package oficina;

import dominio.Peca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * package-private: apenas o OficinaController (mesmo pacote) acessa a lista.
 */
class PecaRepositorio {
    private final List<Peca> pecas = new ArrayList<Peca>();

    void salvar(Peca peca) {
        pecas.add(peca);
    }

    Peca buscarPorCodigo(String codigo) {
        for (Peca peca : pecas) {
            if (peca.getCodigo().equalsIgnoreCase(codigo.trim())) {
                return peca;
            }
        }
        return null;
    }

    boolean existe(String codigo) {
        return buscarPorCodigo(codigo) != null;
    }

    List<Peca> listar() {
        return Collections.unmodifiableList(pecas);
    }
}
