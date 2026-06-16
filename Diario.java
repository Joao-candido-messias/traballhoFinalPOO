import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Gerencia o diario musical: cadastro, listagem, ranking semanal e ranking mensal.
 *
 * <p>Usa o tipo da superclasse Musica nas colecoes (polimorfismo de inclusao)
 * e instancia MusicaDaSemana no momento do cadastro.</p>
 */
public class Diario {

    private static final String ARQUIVO_CSV = "musicas.csv";
    private static final String CABECALHO = "Nome da Faixa,Artista,Álbum,Gênero,Ano de Lançamento,Data";

    /**
     * Cadastra a musica do dia via terminal e salva no CSV.
     *
     * <p>Lanca MusicaJaRegistradaException se ja houver registro para hoje.</p>
     *
     * @throws MusicaJaRegistradaException se ja houver registro para a data atual
     * @throws IOException                 se ocorrer erro de leitura ou escrita no CSV
     * @throws NumberFormatException       se o ano digitado nao for numerico
     */
    public void cadastrar() throws MusicaJaRegistradaException, IOException {
        Scanner sc = new Scanner(System.in);
        LocalDate hoje = LocalDate.now();

        List<Musica> lista = lerCSV();
        for (Musica m : lista) {
            if (m.getData().equals(hoje)) {
                throw new MusicaJaRegistradaException(
                        "Voce ja registrou a musica do dia " + hoje + ". Volte amanha!");
            }
        }

        System.out.print("\nNome da faixa: ");
        String nome = sc.nextLine().trim();

        System.out.print("Artista: ");
        String artista = sc.nextLine().trim();

        System.out.print("Album: ");
        String album = sc.nextLine().trim();

        System.out.print("Genero: ");
        String genero = sc.nextLine().trim();

        System.out.print("Ano de lancamento: ");
        int ano = Integer.parseInt(sc.nextLine().trim());

        MusicaDaSemana m = new MusicaDaSemana(nome, artista, album, genero, ano, hoje);

        try {
            m.registrar();
        } catch (Exception e) {
            System.out.println("Aviso ao registrar: " + e.getMessage());
        }

        salvarNoCSV(m);
        System.out.println("Musica registrada: " + nome + " em " + hoje);
    }

    /**
     * Le o CSV e exibe todas as musicas cadastradas.
     *
     * <p>Chama exibir() polimorficamente para cada elemento da lista.</p>
     *
     * @throws IOException se ocorrer erro de leitura no CSV
     */
    public void listar() throws IOException {
        List<Musica> lista = lerCSV();

        if (lista.isEmpty()) {
            System.out.println("\nNenhuma musica cadastrada ainda.");
            return;
        }

        System.out.println("\n=== HISTORICO MUSICAL ===");
        int i = 1;
        for (Musica m : lista) {
            System.out.print(i + ".");
            m.exibir();
            i++;
        }
        System.out.println("Total: " + lista.size() + " musica(s).");
    }

    /**
     * Gera e exibe o ranking das musicas dos ultimos 7 dias corridos.
     *
     * <p>Exibe: lista do periodo, repeticoes, artista e genero mais presentes.</p>
     *
     * @throws IOException se ocorrer erro de leitura no CSV
     */
    public void rankingSemanal() throws IOException {
        List<Musica> todas = lerCSV();

        if (todas.isEmpty()) {
            System.out.println("\nNenhuma musica cadastrada para gerar ranking.");
            return;
        }

        LocalDate hoje = LocalDate.now();
        LocalDate seteDiasAtras = hoje.minusDays(6);
        List<Musica> semana = new ArrayList<>();

        for (Musica m : todas) {
            if (!m.getData().isBefore(seteDiasAtras) && !m.getData().isAfter(hoje)) {
                semana.add(m);
            }
        }

        if (semana.isEmpty()) {
            System.out.println("\nNenhuma musica registrada nos ultimos 7 dias.");
            return;
        }

        System.out.println("\n=== RANKING SEMANAL ===");

        System.out.println("\nMusicas da semana:");
        for (Musica m : semana) {
            m.exibir();
        }

        // Repeticoes por faixa
        Map<String, Integer> contagemFaixa = new HashMap<>();
        for (Musica m : semana) {
            String chave = m.getNomeFaixa();
            if (contagemFaixa.containsKey(chave)) {
                contagemFaixa.put(chave, contagemFaixa.get(chave) + 1);
            } else {
                contagemFaixa.put(chave, 1);
            }
        }

        System.out.println("\nRepeticoes:");
        boolean houveRepeticao = false;
        for (String faixa : contagemFaixa.keySet()) {
            if (contagemFaixa.get(faixa) > 1) {
                System.out.println("  - \"" + faixa + "\" apareceu " + contagemFaixa.get(faixa) + "x");
                houveRepeticao = true;
            }
        }
        if (!houveRepeticao) {
            System.out.println("  Nenhuma musica repetida esta semana.");
        }

        // Artista mais presente
        Map<String, Integer> contagemArtista = new HashMap<>();
        for (Musica m : semana) {
            String chave = m.getArtista();
            if (contagemArtista.containsKey(chave)) {
                contagemArtista.put(chave, contagemArtista.get(chave) + 1);
            } else {
                contagemArtista.put(chave, 1);
            }
        }
        String artistaTop = maiorContagem(contagemArtista);
        System.out.println("\nArtista da semana: " + artistaTop
                + " (" + contagemArtista.get(artistaTop) + " musica(s))");

        // Genero mais presente
        Map<String, Integer> contagemGenero = new HashMap<>();
        for (Musica m : semana) {
            String chave = m.getGenero();
            if (contagemGenero.containsKey(chave)) {
                contagemGenero.put(chave, contagemGenero.get(chave) + 1);
            } else {
                contagemGenero.put(chave, 1);
            }
        }
        String generoTop = maiorContagem(contagemGenero);
        System.out.println("Genero da semana:  " + generoTop
                + " (" + contagemGenero.get(generoTop) + " ocorrencia(s))");
    }

    /**
     * Gera e exibe o ranking das musicas do mes corrente.
     *
     * <p>Exibe: lista do periodo, repeticoes, artista e genero mais presentes.</p>
     *
     * @throws IOException se ocorrer erro de leitura no CSV
     */
    public void rankingMensal() throws IOException {
        List<Musica> todas = lerCSV();

        if (todas.isEmpty()) {
            System.out.println("\nNenhuma musica cadastrada para gerar ranking.");
            return;
        }

        LocalDate hoje = LocalDate.now();
        LocalDate inicioMes = hoje.withDayOfMonth(1);
        List<Musica> mes = new ArrayList<>();

        for (Musica m : todas) {
            if (!m.getData().isBefore(inicioMes) && !m.getData().isAfter(hoje)) {
                mes.add(m);
            }
        }

        if (mes.isEmpty()) {
            System.out.println("\nNenhuma musica registrada no mes de " + hoje.getMonth() + ".");
            return;
        }

        System.out.println("\n=== RANKING MENSAL - " + hoje.getMonth() + " " + hoje.getYear() + " ===");

        System.out.println("\nMusicas do mes:");
        for (Musica m : mes) {
            m.exibir();
        }

        // Repeticoes por faixa
        Map<String, Integer> contagemFaixa = new HashMap<>();
        for (Musica m : mes) {
            String chave = m.getNomeFaixa();
            contagemFaixa.put(chave, contagemFaixa.getOrDefault(chave, 0) + 1);
        }

        System.out.println("\nRepeticoes:");
        boolean houveRepeticao = false;
        for (String faixa : contagemFaixa.keySet()) {
            if (contagemFaixa.get(faixa) > 1) {
                System.out.println("  - \"" + faixa + "\" apareceu " + contagemFaixa.get(faixa) + "x");
                houveRepeticao = true;
            }
        }
        if (!houveRepeticao) {
            System.out.println("  Nenhuma musica repetida este mes.");
        }

        // Artista mais presente
        Map<String, Integer> contagemArtista = new HashMap<>();
        for (Musica m : mes) {
            String chave = m.getArtista();
            contagemArtista.put(chave, contagemArtista.getOrDefault(chave, 0) + 1);
        }
        String artistaTop = maiorContagem(contagemArtista);
        System.out.println("\nArtista do mes: " + artistaTop
                + " (" + contagemArtista.get(artistaTop) + " musica(s))");

        // Genero mais presente
        Map<String, Integer> contagemGenero = new HashMap<>();
        for (Musica m : mes) {
            String chave = m.getGenero();
            contagemGenero.put(chave, contagemGenero.getOrDefault(chave, 0) + 1);
        }
        String generoTop = maiorContagem(contagemGenero);
        System.out.println("Genero do mes:  " + generoTop
                + " (" + contagemGenero.get(generoTop) + " ocorrencia(s))");
    }

    /**
     * Salva uma musica no CSV em modo de adicao (append).
     *
     * @param m objeto Musica a ser salvo
     * @throws IOException se ocorrer erro de escrita
     */
    private void salvarNoCSV(Musica m) throws IOException {
        File arquivo = new File(ARQUIVO_CSV);
        boolean novo = !arquivo.exists() || arquivo.length() == 0;

        try (PrintWriter pw = new PrintWriter(new FileWriter(arquivo, true))) {
            if (novo) pw.println(CABECALHO);
            pw.println(m.getNomeFaixa() + "," + m.getArtista() + "," + m.getAlbum()
                    + "," + m.getGenero() + "," + m.getAnoLancamento() + "," + m.getData());
        }
    }

    /**
     * Le o CSV e retorna lista de Musica (instancias de MusicaDaSemana).
     *
     * <p>Apos carregar todas as entradas, marca como favorita (favorita = true)
     * qualquer faixa cujo nome apareca mais de uma vez na semana corrente
     * (ultimos 7 dias).</p>
     *
     * @return lista de objetos Musica; vazia se o arquivo nao existir
     * @throws IOException se ocorrer erro de leitura
     */
    private List<Musica> lerCSV() throws IOException {
        List<Musica> lista = new ArrayList<>();
        File arquivo = new File(ARQUIVO_CSV);

        if (!arquivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(arquivo), "UTF-8"))) {
            String linha;
            boolean primeiraLinha = true;

            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) { primeiraLinha = false; continue; }
                linha = linha.trim();
                if (linha.isEmpty() || linha.startsWith(",")) continue;

                String[] campos = linha.split(",", -1);
                if (campos.length < 6) continue;

                try {
                    String nome    = campos[0].trim();
                    String artista = campos[1].trim();
                    String album   = campos[2].trim();
                    String genero  = campos[3].trim();
                    int ano        = Integer.parseInt(campos[4].trim());
                    LocalDate data = LocalDate.parse(campos[5].trim());

                    lista.add(new MusicaDaSemana(nome, artista, album, genero, ano, data));
                } catch (Exception e) {
                    // linha invalida, ignora
                }
            }
        }

        // Marca como favorita qualquer faixa que se repita na semana corrente
        LocalDate hoje = LocalDate.now();
        LocalDate seteDiasAtras = hoje.minusDays(6);

        Map<String, Integer> contagemSemana = new HashMap<>();
        for (Musica m : lista) {
            if (!m.getData().isBefore(seteDiasAtras) && !m.getData().isAfter(hoje)) {
                String chave = m.getNomeFaixa();
                contagemSemana.put(chave, contagemSemana.getOrDefault(chave, 0) + 1);
            }
        }

        for (Musica m : lista) {
            if (m instanceof MusicaDaSemana) {
                MusicaDaSemana mds = (MusicaDaSemana) m;
                Integer contagem = contagemSemana.get(mds.getNomeFaixa());
                mds.setFavorita(contagem != null && contagem > 1);
            }
        }

        return lista;
    }

    /**
     * Retorna a chave com o maior valor de contagem em um mapa String para Integer.
     *
     * @param mapa mapa com chaves e suas contagens
     * @return chave com maior contagem, ou "-" se o mapa estiver vazio
     */
    private String maiorContagem(Map<String, Integer> mapa) {
        String maior = "-";
        int max = 0;

        for (String chave : mapa.keySet()) {
            if (mapa.get(chave) > max) {
                max = mapa.get(chave);
                maior = chave;
            }
        }

        return maior;
    }
}