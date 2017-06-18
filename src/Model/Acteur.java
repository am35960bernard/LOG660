package Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="ACTEUR")
@PrimaryKeyJoinColumn(name="IDACTEUR", referencedColumnName="IDPERSONNAGEDUCINEMA")
public class Acteur extends PersonnageDuCinema implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy="acteur")
	private Set<ActeurFilm> acteurFilms = new HashSet<ActeurFilm>(0);
	
	public Acteur(){}

	public Set<ActeurFilm> getActeurFilms() {
		return acteurFilms;
	}

	public void setActeurFilms(Set<ActeurFilm> acteurFilms) {
		this.acteurFilms = acteurFilms;
	}

}
