package Model;


public class Film implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int idFilm;
	private String titre;
	private int anneeSortie;
	private String langueOriginale;
	private int duree;
	private String resume;
	private String photo;

	public Film(){};	
	
	public Film(int idFilm,String titre,int anneeSortie,String pays,String langueOriginale,int duree,String resume,String photo) {
		this.idFilm = idFilm;
		this.titre = titre;
		this.anneeSortie = anneeSortie;
		this.langueOriginale = langueOriginale;
		this.duree = duree;
		this.resume = resume;
		this.photo = photo;	
	}

	
	public int getIdFilm() {
		return idFilm;
	}

	public void setIdFilm(int idFilm) {
		this.idFilm = idFilm;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getAnneeSortie() {
		return anneeSortie;
	}

	public void setAnneeSortie(int anneeSortie) {
		this.anneeSortie = anneeSortie;
	}

	public String getLangueOriginale() {
		return langueOriginale;
	}

	public void setLangueOriginale(String langueOriginale) {
		this.langueOriginale = langueOriginale;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
