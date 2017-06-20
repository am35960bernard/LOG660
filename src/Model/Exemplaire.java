package Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="EXEMPLAIRE")
public class Exemplaire implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="IDEXEMPLAIRE")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="SEQ")
	@SequenceGenerator(name="SEQ", sequenceName="SEQ_EXEMPLAIRE")
	private int idExemplaire;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="IDFILM")
	private Film film;
	
	@Column(name="NUMCOPIE")
	private int numCopie;
	
	@Column(name="ESTLOUE")
	private String estLoue;
	
	@OneToMany(mappedBy="exemplaire")
	protected Set<Location> locations = new HashSet<Location>(0);
	
	
	public Exemplaire() {}

	public String isEstLoue() {
		return estLoue;
	}

	public void setEstLoue(String estLoue) {
		this.estLoue = estLoue;
	}
	
	public Set<Location> getLocations() {
		return locations;
	}

	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}

	public int getIdExemplaire() {
		return idExemplaire;
	}

	public void setIdExemplaire(int idExemplaire) {
		this.idExemplaire = idExemplaire;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public int getNumCopie() {
		return numCopie;
	}

	public void setNumCopie(int numCopie) {
		this.numCopie = numCopie;
	}

}
