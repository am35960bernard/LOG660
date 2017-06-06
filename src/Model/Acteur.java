package Model;
import java.util.Date;

public class Acteur extends PersonnageDuCinema implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int idActeur;
	
	public Acteur(){};
	
	public Acteur(int idPersonneDuCinema,String nomComplet,Date dateAnniversaire,String lieuNaissance,String biographie,String lienPhoto) {
		super(idPersonneDuCinema,nomComplet,dateAnniversaire,lieuNaissance,biographie,lienPhoto);
		this.idActeur = idPersonneDuCinema;
	}
	

	public int getIdActeur() {
		return idActeur;
	}

	public void setIdActeur(int idActeur) {
		this.idActeur = idActeur;
	}

}
