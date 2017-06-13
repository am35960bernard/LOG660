package Model;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="UTILISATEUR")
@Inheritance(strategy=InheritanceType.JOINED)
public class Utilisateur implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
    @Id
    @Column(name="IDUTILISATEUR")
    @GeneratedValue
	protected int idUtilisateur;
    
    @Column(name="NOM")
	protected String nom;
	
    @Column(name="PRENOM")
	protected String prenom;
	
    @Column(name="MOTDEPASSE")
	protected String motDePasse;
	
    @Column(name="COURRIEL")
	protected String courriel;
	
    @Column(name="NOTELEPHONE")
	protected String noTelephone;
	
    @Column(name="DATEANNIVERSAIRE")
	protected Date dateAnniversaire;
	
    @OneToMany(mappedBy="utilisateur")
	protected Set<Adresse> adresses = new HashSet<Adresse>(0);

	
	public Utilisateur(){};
	
	/*public Utilisateur(String nom,String prenom,String motDePasse,String courriel,String noTelephone,Date dateAnniversaire,int idAdresse) {
		this.nom = nom;
		this.prenom = prenom;
		this.motDePasse = motDePasse;
		this.courriel = courriel;
		this.noTelephone = noTelephone;
		this.dateAnniversaire = dateAnniversaire;
		this.idAdresse = idAdresse;	
	}*/

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

	public Set<Adresse> getAdresses() {
		return adresses;
	}

	public void setIdAdresse(HashSet<Adresse> adresses) {
		this.adresses = adresses;
	}

}
