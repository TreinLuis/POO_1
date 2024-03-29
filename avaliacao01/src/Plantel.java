import java.util.ArrayList;

public class Plantel {
	private ArrayList<Atleta> atletas;

	public Plantel() {
		atletas = new ArrayList<>();
	}
	public boolean cadastraAtleta(Atleta a) {
		if (consultaAtleta(a.getNumero()) != null) {
			return false;
		}
		return atletas.add(a);
	}

	public Atleta consultaAtleta(String nome) {
		for (Atleta a : atletas) {
			if (a.getNome().equalsIgnoreCase(nome))
				return a;
		}
		return null;
	}
	public Atleta consultaAtleta(int numero) {
		for (Atleta a : atletas) {
			if (a.getNumero() == numero)
				return a;
		}
		return null;
	}
	public ArrayList<Atleta> consultaAtletasPorPais(String pais) {
		ArrayList<Atleta> atletas2 = new ArrayList<>();
		for (Atleta a : atletas) {
			if (a.getPais().equalsIgnoreCase(pais)) {
				atletas2.add(a);
			}
		}
		return atletas2;
	}
	public Atleta maiorMedalhista() {
		// Primeiro crio um array com quem foi medalhista
		ArrayList<Atleta> medalhista = new ArrayList<>();
		Atleta maiorMedalhista;
		for (Atleta a : atletas) {
			if (a.consultaQuantidadeMedalhas() > 0) {
				medalhista.add(a);
			}
		}
		//se aquele vetor for vazio retorno nullo para dar mensagem de erro
		if (medalhista.isEmpty()) {
			return null;
		} else {
			//Aqui eu percorro meu array com os medalhistas e valido quem possuí mais medalhas
			maiorMedalhista = medalhista.get(0);
			for (Atleta a : medalhista) {
				if (a.consultaQuantidadeMedalhas() > maiorMedalhista.consultaQuantidadeMedalhas()) {
					maiorMedalhista = a;
				}
			}
			return maiorMedalhista;
		}
	}
	public ArrayList<Atleta> atletasPorMedalhas(int tipoMedalha) {
		ArrayList<Atleta> atletasMedalhas = new ArrayList<>();
		for (Atleta a : atletas) {
			for (Medalha m : a.getMedalhas()) {
				if (m.getTipo() == tipoMedalha) {
					atletasMedalhas.add(a);
				}
			}
		}
		return atletasMedalhas;
	}
}


