import java.time.LocalDate;

/**
 * Classe abstrata que representa uma musica no diario musical.
 *
 * <p>Define os atributos e comportamentos comuns a qualquer tipo de musica
 * registrada no sistema. Nao pode ser instanciada diretamente.</p>
 */
public abstract class Musica {

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
     * @param genero        genero musical
     * @param anoLancamento ano de lancamento da faixa
     * @param data          data em que foi escolhida como trilha do dia
     */
    public Musica(String nomeFaixa, String artista, String album, String genero, int anoLancamento, LocalDate data) {
        this.nomeFaixa = nomeFaixa;
        this.artista = artista;
        this.album = album;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
        this.data = data;
    }

    // Getters

    /** @return nome da faixa */
    public String getNomeFaixa() { return nomeFaixa; }

    /** @return artista */
    public String getArtista() { return artista; }

    /** @return album */
    public String getAlbum() { return album; }

    /** @return genero */
    public String getGenero() { return genero; }

    /** @return ano de lancamento */
    public int getAnoLancamento() { return anoLancamento; }

    /** @return data do registro */
    public LocalDate getData() { return data; }

    // Setters

    /** @param nomeFaixa novo nome da faixa */
    public void setNomeFaixa(String nomeFaixa) { this.nomeFaixa = nomeFaixa; }

    /** @param artista novo artista */
    public void setArtista(String artista) { this.artista = artista; }

    /** @param album novo album */
    public void setAlbum(String album) { this.album = album; }

    /** @param genero novo genero */
    public void setGenero(String genero) { this.genero = genero; }

    /** @param anoLancamento novo ano */
    public void setAnoLancamento(int anoLancamento) { this.anoLancamento = anoLancamento; }

    /** @param data nova data de registro */
    public void setData(LocalDate data) { this.data = data; }

    /**
     * Exibe as informacoes da musica no terminal.
     *
     * <p>Metodo abstrato: cada subclasse define sua propria forma de exibicao.</p>
     */
    public abstract void exibir();
}