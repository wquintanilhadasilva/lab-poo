package menu.item;

import dominio.OrdeServico;
import dominio.Peca;
import infra.Console;
import oficina.OficinaController;

import java.util.List;

/**
 * Apoio de exibição compartilhado pelos itens de menu de peça e de ordem.
 */
public class VisualizacaoOficina {

    public static void pecas(Console console, OficinaController controller) {
        List<Peca> pecas = controller.listarPecas();
        if (pecas.isEmpty()) {
            console.escrever("(Nenhuma peça cadastrada)");
            return;
        }
        console.escrever("Peças cadastradas:");
        for (Peca peca : pecas) {
            console.escrever("  " + peca.resumo());
        }
    }

    public static void ordens(Console console, OficinaController controller) {
        List<OrdeServico> ordens = controller.listarOrdens();
        if (ordens.isEmpty()) {
            console.escrever("(Nenhuma ordem de serviço cadastrada)");
            return;
        }
        console.escrever("Ordens de serviço:");
        for (OrdeServico ordem : ordens) {
            console.escrever("  " + ordem.resumo());
        }
    }
}
