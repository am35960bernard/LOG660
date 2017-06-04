import java.util.Date;

public class Realisateur extends PersonnageDuCinema implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private int idRealisateur;

	public Realisateur (){};	
		
	public Realisateur(int idPersonneDuCinema,String nomComplet,Date dateAnniversaire,String lieuNaissance,String biographie,String lienPhoto) {
		super(idPersonneDuCinema,nomComplet,dateAnniversaire,lieuNaissance,biographie,lienPhoto);
		this.idRealisateur = idPersonneDuCinema;
	}	
	
	public int getIdRealisateur() {
		return idRealisateur;
	}

	public void setIdRealisateur(int idRealisateur) {
		this.idRealisateur = idRealisateur;
	}

}
