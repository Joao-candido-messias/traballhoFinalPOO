import java.time.LocalDate;

/**
 * Classe abstrata que representa uma musica no diario musical.
 *
 * <p>Define os atributos e comportamentos comuns a qualquer tipo de musica
 * registrada no sistema. Nao pode ser instanciada diretamente — deve ser
 * estendida por uma subclasse concreta.</p>
 */
public abstract class Musica {

    // Atributos protegidos para acesso nas subclasses
    protected String nomeFaixa;
    protected String artista;
    protected String album;
    protected String genero;
    protected int anoLancamento;
    protected LocalDate data;

    /**
     * Construtor padrao.
     */
    public Musica() {}

    /**
     * Construtor completo.
     *
     * @param nomeFaixa     titulo da faixa musical
     * @param artista       nome do artista ou banda
     * @param album         nome do album de origem
     * @param genero        genero musical (ex.: MPB, Rock, Choro)
     * @param anoLancamento ano de lancamento da faixa
     * @param data          data em que a musica foi escolhida como trilha do dia
     */
    public Musica(String nomeFaixa, String artista, String album, String genero, int anoLancamento, LocalDate data) {
        this.nomeFaixa = nomeFaixa;
        this.artista = artista;
        this.album = album;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
        this.data = data;
    }

    // ── Getters ───────────────────────────────────────────────────────────────

    /**
     * Retorna o titulo da faixa.
     *
     * @return nome da faixa
     */
    public String getNomeFaixa() { return nomeFaixa; }

    /**
     * Retorna o nome do artista ou banda.
     *
     * @return artista
     */
    public String getArtista() { return artista; }

    /**
     * Retorna o nome do album.
     *
     * @return album
     */
    public String getAlbum() { return album; }

    /**
     * Retorna o genero musical.
     *
     * @return genero
     */
    public String getGenero() { return genero; }

    /**
     * Retorna o ano de lancamento.
     *
     * @return ano de lancamento
     */
    public int getAnoLancamento() { return anoLancamento; }

    /**
     * Retorna a data em que a musica foi registrada.
     *
     * @return data do registro
     */
    public LocalDate getData() { return data; }

    // ── Setters ───────────────────────────────────────────────────────────────

    /**
     * Define o titulo da faixa.
     *
     * @param nomeFaixa novo nome da faixa
     */
    public void setNomeFaixa(String nomeFaixa) { this.nomeFaixa = nomeFaixa; }

    /**
     * Define o artista ou banda.
     *
     * @param artista novo artista
     */
    public void setArtista(String artista) { this.artista = artista; }

    /**
     * Define o nome do album.
     *
     * @param album novo album
     */
    public void setAlbum(String album) { this.album = album; }

    /**
     * Define o genero musical.
     *
     * @param genero novo genero
     */
    public void setGenero(String genero) { this.genero = genero; }

    /**
     * Define o ano de lancamento.
     *
     * @param anoLancamento novo ano
     */
    public void setAnoLancamento(int anoLancamento) { this.anoLancamento = anoLancamento; }

    /**
     * Define a data de registro.
     *
     * @param data nova data
     */
    public void setData(LocalDate data) { this.data = data; }

    /**
     * Exibe as informacoes da musica no terminal.
     *
     * <p>Metodo abstrato: cada subclasse decide como apresentar seus dados.</p>
     */
    public abstract void exibir();
}