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
     * Indica se esta musica e considerada favorita da semana.
     * Retorna true quando a mesma faixa aparece mais de uma vez
     * nos registros da semana corrente.
     */
    private boolean favorita;

    /**
     * Construtor padrao.
     */
    public MusicaDaSemana() {
        super();
        this.favorita = false;
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
        this.favorita = false;
    }

    /**
     * Construtor completo com indicador de favorita.
     *
     * @param nomeFaixa     titulo da faixa musical
     * @param artista       nome do artista ou banda
     * @param album         nome do album de origem
     * @param genero        genero musical
     * @param anoLancamento ano de lancamento da faixa
     * @param data          data em que foi escolhida como trilha do dia
     * @param favorita      true se a musica se repete durante a semana
     */
    public MusicaDaSemana(String nomeFaixa, String artista, String album, String genero, int anoLancamento, LocalDate data, boolean favorita) {
        super(nomeFaixa, artista, album, genero, anoLancamento, data);
        this.favorita = favorita;
    }

    /** @return true se esta musica e favorita da semana (aparece mais de uma vez) */
    public boolean isFavorita() {
        return favorita;
    }

    /** @param favorita define se a musica e considerada favorita da semana */
    public void setFavorita(boolean favorita) {
        this.favorita = favorita;
    }

    /**
     * Exibe as informacoes da musica do dia no terminal.
     * Inclui indicador de favorita quando aplicavel.
     *
     * <p>Sobrescreve o metodo abstrato da superclasse.</p>
     */
    @Override
    public void exibir() {
        String marcador = favorita ? " ★ FAVORITA" : "";
        System.out.println("  [" + data + "] " + nomeFaixa + " - " + artista
                + " (" + genero + ", " + anoLancamento + ")" + marcador);
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
        String marcador = favorita ? " [★ FAVORITA]" : "";
        return nomeFaixa + " - " + artista + " (" + genero + ", " + anoLancamento + ") [" + data + "]" + marcador;
    }
}