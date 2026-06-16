import java.time.LocalDate;

/**
 * Subclasse concreta de Musica que representa a musica escolhida
 * como trilha sonora de um dia especifico.
 *
 * <p>MusicaDaSemana E UMA Musica. Herda seus atributos e implementa
 * o contrato da interface Registravel.</p>
 */
public class MusicaDaSemana extends Musica implements Registravel {

    /**
     * Construtor padrao.
     */
    public MusicaDaSemana() {
        super();
    }

    /**
     * Construtor completo.
     *
     * @param nomeFaixa     titulo da faixa musical
     * @param artista       nome do artista ou banda
     * @param album         nome do album de origem
     * @param genero        genero musical
     * @param anoLancamento ano de lancamento da faixa
     * @param data          data em que foi escolhida como trilha do dia
     */
    public MusicaDaSemana(String nomeFaixa, String artista, String album, String genero, int anoLancamento, LocalDate data) {
        super(nomeFaixa, artista, album, genero, anoLancamento, data);
    }

    /**
     * Exibe as informacoes da musica do dia no terminal.
     *
     * <p>Sobrescreve o metodo abstrato da superclasse.</p>
     */
    @Override
    public void exibir() {
        System.out.println("  [" + data + "] " + nomeFaixa + " - " + artista
                + " (" + genero + ", " + anoLancamento + ")");
    }

    /**
     * Implementacao do contrato da interface Registravel.
     *
     * @throws Exception se ocorrer qualquer erro durante o registro
     */
    @Override
    public void registrar() throws Exception {
        System.out.println("Registrando: " + nomeFaixa + " em " + data);
    }

    /**
     * Retorna uma representacao textual da musica do dia.
     *
     * @return string com os dados principais da faixa
     */
    @Override
    public String toString() {
        return nomeFaixa + " - " + artista + " (" + genero + ", " + anoLancamento + ") [" + data + "]";
    }
}