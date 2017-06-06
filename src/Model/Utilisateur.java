package Model;
import java.util.Date;

public class Utilisateur implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	protected int idUtilisateur;
	protected String nom;
	protected String prenom;
	protected String motDePasse;
	protected String courriel;
	protected String noTelephone;
	protected Date dateAnniversaire;
	protected int idAdresse;

	public Utilisateur(){};
	
	public Utilisateur(int idUtilisateur,String nom,String prenom,String motDePasse,String courriel,String noTelephone,Date dateAnniversaire,int idAdresse) {
		this.idUtilisateur = idUtilisateur;
		this.nom = nom;
		this.prenom = prenom;
		this.motDePasse = motDePasse;
		this.courriel = courriel;
		this.noTelephone = noTelephone;
		this.dateAnniversaire = dateAnniversaire;
		this.idAdresse = idAdresse;	
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public Date getDateAnniversaire() {
		return dateAnniversaire;
	}

	public void setDateAnniversaire(Date dateAnniversaire) {
		this.dateAnniversaire = dateAnniversaire;
	}

	public String getCourriel() {
		return courriel;
	}

	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	public String getNoTelephone() {
		return noTelephone;
	}

	public void setNoTelephone(String noTelephone) {
		this.noTelephone = noTelephone;
	}

	public int getIdAdresse() {
		return idAdresse;
	}

	public void setIdAdresse(int idAdresse) {
		this.idAdresse = idAdresse;
	}

}
