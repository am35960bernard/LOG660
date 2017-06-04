import java.util.Date;

public class PersonnageDuCinema implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	protected int idPersonneDuCinema;
	protected String nomComplet;
	protected Date dateAnniversaire;
	protected String lieuNaissance;
	protected String biographie;
	protected String lienPhoto;

	public PersonnageDuCinema(){};	
	
	public PersonnageDuCinema(int idPersonneDuCinema,String nomComplet,Date dateAnniversaire,String lieuNaissance,String biographie,String lienPhoto) {
		this.idPersonneDuCinema = idPersonneDuCinema;
		this.nomComplet = nomComplet;
		this.dateAnniversaire = dateAnniversaire;
		this.lieuNaissance = lieuNaissance;
		this.biographie = biographie;
		this.lienPhoto = lienPhoto;
	}

	public int getIdPersonneDuCinema() {
		return idPersonneDuCinema;
	}

	public void setIdPersonneDuCinema(int idPersonneDuCinema) {
		this.idPersonneDuCinema = idPersonneDuCinema;
	}

	public String getNomComplet() {
		return nomComplet;
	}

	public void setNomComplet(String nomComplet) {
		this.nomComplet = nomComplet;
	}

	public Date getDateAnniversaire() {
		return dateAnniversaire;
	}

	public void setDateAnniversaire(Date dateAnniversaire) {
		this.dateAnniversaire = dateAnniversaire;
	}

	public String getLieuNaissance() {
		return lieuNaissance;
	}

	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}

	public String getBiographie() {
		return biographie;
	}

	public void setBiographie(String biographie) {
		this.biographie = biographie;
	}

	public String getLienPhoto() {
		return lienPhoto;
	}

	public void setLienPhoto(String lienPhoto) {
		this.lienPhoto = lienPhoto;
	}

}
