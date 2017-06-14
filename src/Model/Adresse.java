package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ADRESSE")
public class Adresse implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="IDADRESSE")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="SEQ")
	@SequenceGenerator(name="SEQ", sequenceName="SEQ_ADRESSE")
	private int idAdresse;
	
	@Column(name="CODEPOSTAL")
	private String codePostal;
	
	@Column(name="PROVINCE")
	private String province;
	
	@Column(name="VILLE")
	private String ville;
	
	@Column(name="NUMCIVIQUE")
	private int numeroCivique;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="IDUTILISATEUR")
	private Utilisateur utilisateur;
	
	public Adresse() {}

	public int getIdAdresse() {
		return idAdresse;
	}

	public void setIdAdresse(int idAdresse) {
		this.idAdresse = idAdresse;
	}

	public String getCodePostale() {
		return codePostal;
	}

	public void setCodePostale(String codePostale) {
		this.codePostal = codePostale;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getNumeroCivique() {
		return numeroCivique;
	}

	public void setNumeroCivique(int numeroCivique) {
		this.numeroCivique = numeroCivique;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
}
