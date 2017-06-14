package Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="REALISATEUR")
@PrimaryKeyJoinColumn(name="IDREALISATEUR", referencedColumnName="IDPERSONNAGEDUCINEMA")
public class Realisateur extends PersonnageDuCinema implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToMany(mappedBy="realisateurs")
	private Set<Film> films = new HashSet<Film>(0);
	
	public Realisateur (){};
	
	public Set<Film> getFilms() {
		return films;
	}

	public void setFilms(Set<Film> films) {
		this.films = films;
	}

}
