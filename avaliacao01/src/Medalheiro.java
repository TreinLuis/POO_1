import java.lang.reflect.Array;
import java.util.Collection;
import java.util.ArrayList;

public class Medalheiro {
    private ArrayList<Medalha> medalhas;

    public Medalheiro() {
        medalhas = new ArrayList<>();
    }

    public boolean cadastraMedalha(Medalha m) {
        if (consultaMedalha(m.getCodigo()) != null) {
            return false;
        }
        medalhas.add(m);
        return true;
    }
    public Medalha consultaMedalha(int codigo) {
        for (int i = 0; i < this.medalhas.size(); i++) {
            Medalha m = this.medalhas.get(i);
            if (m.getCodigo() == codigo) return m;
        }
        return null;
    }
    public ArrayList<Medalha> consultaMedalhas(String modalidade) {
        ArrayList<Medalha> medalhasConsultadas = new ArrayList<>();
        if (!medalhas.isEmpty()) {
            for (int i = 0; i < this.medalhas.size(); i++) {
                Medalha m = this.medalhas.get(i);
                if (m.getModalidade().equalsIgnoreCase(modalidade)) {
                    medalhasConsultadas.add(m);
                }
            }
        }
        if (!medalhasConsultadas.isEmpty()) {
            return medalhasConsultadas;
        }
        return null;
    }
    public ArrayList<Medalha> consultaMedalhasTodosPaises() {
        ArrayList<Medalha> todasMedalhas = new ArrayList<>();
        for (Medalha m : medalhas) {
            todasMedalhas.add(m);
        }
        if (todasMedalhas.isEmpty()) return null;
        else return todasMedalhas;
    }
    public ArrayList<Medalha> consultaMedalhasPorPais(String pais) {
        ArrayList<Medalha> medalhasPorPais = new ArrayList<>();
        for (Medalha medalha : medalhas) {
            for (Atleta atleta : medalha.getAtletas()) {
                if (atleta.getPais().equals(pais)) {
                    medalhasPorPais.add(medalha);
                    break;
                }
            }
        }
        return medalhasPorPais;
    }

}