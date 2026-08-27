package dominio;

/**
 * package-private: gera números sequenciais só para OrdeServico.
 */
class SequenciaOrdem {
    private static int atual = 0;

    static int proxima() {
        return ++atual;
    }
}
