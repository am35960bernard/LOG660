package Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PAYS")
public class Pays implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="IDPAYS")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="SEQ")
	@SequenceGenerator(name="SEQ", sequenceName="SEQ_ADRESSE")
	private int idPays;
	
	@Column(name="NOM")
	private String nom;
	
	@OneToMany(mappedBy="pays")
	private Set<PaysFilm> paysFilms = new HashSet<PaysFilm>(0);
	
	public Pays(){}

	public int getIdPays() {
		return idPays;
	}

	public void setIdPays(int idPays) {
		this.idPays = idPays;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Set<PaysFilm> getPaysFilms() {
		return paysFilms;
	}

	public void setPaysFilms(Set<PaysFilm> paysFilms) {
		this.paysFilms = paysFilms;
	}
}
