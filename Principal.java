import java.util.Scanner;

/**
 * Classe principal do Diario Musical.
 *
 * <p>Responsavel pelo fluxo de execucao: exibe o menu, captura a opcao
 * do usuario e delega as operacoes ao objeto Diario.</p>
 */
public class Principal {

    public static void main(String[] args) {

        Diario diario = new Diario();
        int opcao;

        do {
            try {
                opcao = menu();

                switch (opcao) {
                    case 1:
                        diario.cadastrar();
                        break;

                    case 2:
                        diario.listar();
                        break;

                    case 3:
                        diario.rankingSemanal();
                        break;

                    case 4:
                        diario.rankingMensal();
                        break;

                    case 0:
                        System.out.println("\nPrograma encerrado.");
                        break;

                    default:
                        System.out.println("\nOpcao invalida.");
                }

            } catch (MusicaJaRegistradaException e) {
                System.out.println("\n" + e.getMessage());
                opcao = -1;

            } catch (NumberFormatException e) {
                System.out.println("\nValor invalido. Digite um numero.");
                opcao = -1;

            } catch (Exception e) {
                System.out.println("\nErro: " + e.getMessage());
                opcao = -1;
            }
        } while (opcao != 0);
    }

    /**
     * Exibe o menu no terminal e retorna a opcao digitada pelo usuario.
     *
     * @return inteiro correspondente a opcao escolhida
     */
    private static int menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== DIARIO MUSICAL ===");
        System.out.println("1. Cadastrar musica do dia");
        System.out.println("2. Listar musicas");
        System.out.println("3. Ranking semanal");
        System.out.println("4. Ranking mensal");
        System.out.println("0. Sair");
        System.out.print("Opcao: ");
        return Integer.parseInt(sc.nextLine().trim());
    }
}