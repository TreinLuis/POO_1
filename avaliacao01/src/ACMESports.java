import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
//DIAGRAM ASTAH OK
public class ACMESports {
    private Plantel plantel;
    private Scanner entrada = new Scanner(System.in);
    private Medalheiro medalheiro;
    private PrintStream saidaPadrao = System.out;   // Guarda a saida padrao - tela (console)
    private final String nomeArquivoEntrada = "dadosin.txt";  // Nome do arquivo de entrada de dados
    private final String nomeArquivoSaida = "dadosout.txt";  // Nome do arquivo de saida de dados
    public ACMESports() {

        plantel = new Plantel();
        medalheiro = new Medalheiro();
        redirecionaES();
    }
    private void redirecionaES() {
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader(nomeArquivoEntrada));
            entrada = new Scanner(streamEntrada);   // Usa como entrada um arquivo
            PrintStream streamSaida = new PrintStream(new File(nomeArquivoSaida), Charset.forName("UTF-8"));
			System.setOut(streamSaida);             // Usa como saida um arquivo
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal
        entrada.useLocale(Locale.ENGLISH);   // Ajusta para leitura para ponto decimal
    }

    // Restaura E/S padrao de tela(console)/teclado
    // Chame este metodo para retornar a leitura e escrita de dados para o padrao
    private void restauraES() {
        System.setOut(saidaPadrao);
        entrada = new Scanner(System.in);
    }


    public void executar() {
        cadastraAtleta();
        cadastraMedalha();
        cadastraMedalhasAtletas();
        exibeDadosAtletaPorNumero();
        exibeDadosAtletaPorNome();
        exibeDadosMedalha();
        exibeDadosAtletasPorPais();
        exibeDadosAtletasPorTipoDeMedalhas();
        exibeDadosPorModalidade();
        exibeDadosAtletaComMaisMedalhas();
        mostrarMedalhasPorPais();
    }

    private void cadastraAtleta() {
        int numero;
        String nome;
        String pais;
        numero = entrada.nextInt();
        while (numero != -1) {
            nome = entrada.nextLine();
            nome = entrada.nextLine();
            pais = entrada.nextLine();
            Atleta a = new Atleta(numero, nome, pais);
            if (plantel.cadastraAtleta(a)) {
                System.out.println("1:" + a.getNumero() + "," + a.getNome() + "," + a.getPais());
            }
            numero = entrada.nextInt();
        }
    }
    private void cadastraMedalha() {
        int codigo;
        int tipo;
        String modalidade;
        boolean individual;
        codigo = entrada.nextInt();
        while (codigo != -1) {
            tipo = entrada.nextInt();
            individual = entrada.nextBoolean();
            modalidade = entrada.nextLine();
            modalidade = entrada.nextLine();
            Medalha m = new Medalha(codigo, tipo, individual, modalidade);
            if (medalheiro.cadastraMedalha(m)) {
                System.out.println("2:" + m.getCodigo() + "," + m.getTipo() + "," + m.isIndividual() + "," + m.getModalidade());
            }
            codigo = entrada.nextInt();
        }
    }
    private void cadastraMedalhasAtletas() {
        int codigo = entrada.nextInt();
        while (codigo != -1) {
            Medalha m = this.medalheiro.consultaMedalha(codigo);
            if (m != null) {
                int numero = entrada.nextInt();
                Atleta a = this.plantel.consultaAtleta(numero);
                if (a != null) {
                    m.adicionaAtleta(a);
                    a.adicionaMedalha(m);
                    System.out.println("3:" + m.getCodigo() + "," + a.getNumero());
                }
            }
            codigo = entrada.nextInt();
        }
    }
    private void exibeDadosAtletaPorNumero() {
        int numero;
        numero = entrada.nextInt();
        Atleta a = this.plantel.consultaAtleta(numero);
        if (a != null) {
            System.out.println("4: " + a.getNumero() + " " + a.getNome() + " " + a.getPais());
        } else {
            System.out.println("4: Nenhum atleta encontrado.");
        }
    }

    private void exibeDadosAtletaPorNome() {
        String nome;
        nome = entrada.nextLine();
        nome = entrada.nextLine();
        Atleta a = this.plantel.consultaAtleta(nome);
        if (a != null) {
            System.out.println("5: " + a.getNumero() + " " + a.getNome() + " " + a.getPais());
        } else {
            System.out.println("5: Nenhum atleta encontrado.");
        }
    }

    private void exibeDadosMedalha() {
        int codigo;
        codigo = entrada.nextInt();
        Medalha m = this.medalheiro.consultaMedalha(codigo);
        if (m != null) {
            System.out.println("5: " + m.getCodigo() + " " + m.getTipo() + " " + m.isIndividual() + " " + m.getModalidade());
        } else {
            System.out.println("5: Nenhuma medalha encontrada.");
        }
    }

    private void exibeDadosAtletasPorPais() {
        ArrayList<Atleta> atletasDoPais;
        String pais = entrada.next();
        atletasDoPais = plantel.consultaAtletasPorPais(pais);
        if (atletasDoPais.isEmpty()) {
            System.out.println("7:Pais nao encontrado");
        } else {
            for (Atleta atleta : atletasDoPais) {
                System.out.println("7:" + atleta.getNumero() + "," + atleta.getNome() + "," + atleta.getPais());
            }
        }

    }

    private void exibeDadosAtletasPorTipoDeMedalhas() {
        int tipoMedalha;
        ArrayList<Atleta> atletasEncontrados;
        tipoMedalha = entrada.nextInt();
        atletasEncontrados = plantel.atletasPorMedalhas(tipoMedalha);
        if (atletasEncontrados.isEmpty()) {
            System.out.println("8:Nenhum atleta encontrado");
        } else {
            for (Atleta atleta : atletasEncontrados) {
                System.out.println("8:" + atleta.getNumero() + "," + atleta.getNome() + "," + atleta.getPais());
            }
        }
    }

    private void exibeDadosPorModalidade() {
        ArrayList<Medalha> medalhasModalidade;
        String modalidade = entrada.next();
        medalhasModalidade = medalheiro.consultaMedalhas(modalidade);
        if (medalheiro.consultaMedalhas(modalidade) == null) {
            System.out.println("modalidade não encontrada");
        } else {
            for (Medalha m : medalhasModalidade) {
                if (m.getAtletas().isEmpty()) {
                    System.out.println("9:" + modalidade + "," + m.getTipo() + ",Sem atletas com medalha.");
                } else {
                    for (Atleta a : m.getAtletas()) {
                        System.out.println("9:" + modalidade + "," + m.getTipo() + "," + a.getNumero() + "," + a.getNome() + "," + a.getPais());
                    }
                }
            }
        }
    }
    private void exibeDadosAtletaComMaisMedalhas() {
        //Crio as variaveis para contabilizar cada tipo de medalha
        int ouro = 0;
        int prata = 0;
        int bronze = 0;
        Atleta maiorMedalista = plantel.maiorMedalhista();
        if (maiorMedalista == null) {
            System.out.println("10:Nenhum atleta com medalha");
        } else {
            //Criando e percorrendo meu vetor de maiorMedalista(para pegar meu array da classe atleta) já pegando as suas medalhas
            // para verificar o tipo das mesmas.
            //Classifica as medalhas do atleta
            for (Medalha medalhasAtleta : maiorMedalista.getMedalhas()) {
                switch (medalhasAtleta.getTipo()) {
                    case 1:
                        ouro++;
                        break;
                    case 2:
                        prata++;
                        break;
                    case 3:
                        bronze++;
                        break;
                }
            }
            //Printando as informações dos atletas
            System.out.println("10: " + maiorMedalista.getNumero() + " " + maiorMedalista.getNome() + " " + maiorMedalista.getPais() + " Ouro: " + ouro + " Prata: " + prata + " Bronze: " + bronze);
        }
    }
    public void mostrarMedalhasPorPais() {
        ArrayList<Medalha> todasMedalhas = medalheiro.consultaMedalhasTodosPaises();
        if (todasMedalhas == null || todasMedalhas.isEmpty()) {
            System.out.println("Nenhuma medalha encontrada.");
            return;
        }
        ArrayList<String> paises = new ArrayList<>();
        // Itera sobre todas as medalhas para obter a lista de países
        for (Medalha m : todasMedalhas) {
            for (Atleta a : m.getAtletas()) {
                String pais = a.getPais();
                // Se o país ainda não estiver na lista, adiciona
                if (!paises.contains(pais)) {
                    paises.add(pais);
                }
            }
        }
        // Exibe as medalhas por país
        for (String pais : paises) {
            int ouro = 0;
            int prata = 0;
            int bronze = 0;
            for (Medalha m : medalheiro.consultaMedalhasPorPais(pais)) {
                switch (m.getTipo()) {
                    case 1:
                        ouro++;
                        break;
                    case 2:
                        prata++;
                        break;
                    case 3:
                        bronze++;
                        break;
                }
            }
            System.out.println("11: " + pais + " Ouro: " + ouro + " Prata: " + prata + " Bronze: " + bronze);
        }
    }
}