package infra;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Isola a leitura/escrita do terminal (GRASP Pure Fabrication).
 * O restante do sistema não conhece Scanner.
 */
public class Console {
    private final Scanner scanner;

    public Console() {
        this.scanner = new Scanner(System.in);
    }

    public void escrever(String mensagem) {
        System.out.println(mensagem);
    }

    public String lerTexto(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public int lerInteiro(String prompt) {
        while (true) {
            String linha = lerTexto(prompt);
            try {
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                escrever("Valor inválido. Digite um número inteiro.");
            }
        }
    }

    public double lerDecimal(String prompt) {
        while (true) {
            String linha = lerTexto(prompt).replace(",", ".");
            try {
                return Double.parseDouble(linha);
            } catch (NumberFormatException e) {
                escrever("Valor inválido. Digite um número decimal.");
            }
        }
    }

    public LocalDate lerData(String prompt) {
        while (true) {
            String linha = lerTexto(prompt);
            if (linha.isEmpty()) {
                return LocalDate.now();
            }
            try {
                return LocalDate.parse(linha);
            } catch (DateTimeParseException e) {
                escrever("Data inválida. Use o formato AAAA-MM-DD.");
            }
        }
    }
}
