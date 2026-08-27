package dominio;

/**
 * package-private: só classes do pacote dominio usam estas regras.
 * Demonstra o modificador padrão (sem palavra-chave).
 */
class Validador {

    static void exigirTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " é obrigatório.");
        }
    }

    static void exigirNaoNegativo(double valor, String campo) {
        if (valor < 0) {
            throw new IllegalArgumentException(campo + " não pode ser negativo.");
        }
    }

    static void exigirPositivo(int valor, String campo) {
        if (valor <= 0) {
            throw new IllegalArgumentException(campo + " deve ser maior que zero.");
        }
    }
}
