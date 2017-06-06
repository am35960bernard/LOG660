package Model;

public class Genre implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idGenre;
	private String nom;
	
	public Genre() {
	}

	public int getIdGenre() {
		return idGenre;
	}

	public void setIdGenre(int idGenre) {
		this.idGenre = idGenre;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
