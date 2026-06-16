/**
 * Excecao customizada lancada quando o usuario tenta registrar
 * uma musica em uma data que ja possui registro.
 *
 * <p>Protege a regra de negocio de uma musica por dia.</p>
 */
public class MusicaJaRegistradaException extends Exception {

    /**
     * Construtor que recebe a mensagem descritiva do erro.
     *
     * @param mensagem descricao do motivo da excecao
     */
    public MusicaJaRegistradaException(String mensagem) {
        super(mensagem);
    }
}