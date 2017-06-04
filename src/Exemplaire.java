
public class Exemplaire implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int numeroCopie;
	private int idFilm;
	private boolean estLoue;
	
	public Exemplaire() {
	}

	public int getNumeroCopie() {
		return numeroCopie;
	}

	public int getIdFilm() {
		return idFilm;
	}

	public void setIdFilm(int idFilm) {
		this.idFilm = idFilm;
	}

	public void setNumeroCopie(int numeroCopie) {
		this.numeroCopie = numeroCopie;
	}

	public boolean isEstLoue() {
		return estLoue;
	}

	public void setEstLoue(boolean estLoue) {
		this.estLoue = estLoue;
	}

}
