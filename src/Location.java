import java.util.Date;

public class Location implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idLocation;
	private Date dateDebut;
	private Date dateRetour;
	private int idClient;
	private int numeroCopie;
	private int idFilm;
	
	public Location() {
	}

	public int getIdLocation() {
		return idLocation;
	}

	public void setIdLocation(int idLocation) {
		this.idLocation = idLocation;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getNumeroCopie() {
		return numeroCopie;
	}

	public void setNumeroCopie(int numeroCopie) {
		this.numeroCopie = numeroCopie;
	}

	public int getIdFilm() {
		return idFilm;
	}

	public void setIdFilm(int idFilm) {
		this.idFilm = idFilm;
	}

}
