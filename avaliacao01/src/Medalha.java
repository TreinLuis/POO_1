import java.util.ArrayList;
//Medalha diagrama ok
public class Medalha {
    private ArrayList<Atleta> atletas;

    private int codigo;

    private int tipo;

    private boolean individual;
    private String modalidade;
    public Medalha(int codigo, int tipo, boolean individual, String modalidade) {
        atletas = new ArrayList<>();
        this.codigo = codigo;
        this.tipo = tipo;
        this.individual = individual;
        this.modalidade = modalidade;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public boolean isIndividual() {
        return individual;
    }

    public void setIndividual(boolean individual) {
        this.individual = individual;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public ArrayList<Atleta> getAtletas() {
        return atletas;
    }

    public void adicionaAtleta(Atleta atleta) {
        atletas.add(atleta);
    }
}
