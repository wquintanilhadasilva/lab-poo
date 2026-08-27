package dominio;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * package-private: formatação usada apenas pelas entidades deste pacote.
 */
class Formatador {
    private static final NumberFormat MOEDA =
            NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));
    private static final DateTimeFormatter DATA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    static String moeda(double valor) {
        return MOEDA.format(valor);
    }

    static String data(LocalDate data) {
        return data.format(DATA);
    }
}
