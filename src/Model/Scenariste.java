package Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="SCENARISTE")
public class Scenariste implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="IDSCENARISTE")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="SEQ")
	@SequenceGenerator(name="SEQ", sequenceName="SEQ_SCENARISTE")
	private int idScenariste;
	
	@Column(name="NOMSCENARISTE")
	private String nomScenariste;
	
	@ManyToMany(mappedBy="scenaristes")
	private Set<Film> films = new HashSet<Film>(0);
	
	public Scenariste() {}

	public int getIdScenariste() {
		return idScenariste;
	}

	public void setIdScenariste(int idScenariste) {
		this.idScenariste = idScenariste;
	}

	public String getNomScenariste() {
		return nomScenariste;
	}

	public void setNomScenariste(String nomScenariste) {
		this.nomScenariste = nomScenariste;
	}

	public Set<Film> getFilms() {
		return films;
	}

	public void setFilms(Set<Film> films) {
		this.films = films;
	}

}
