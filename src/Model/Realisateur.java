package Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="REALISATEUR")
@PrimaryKeyJoinColumn(name="IDREALISATEUR", referencedColumnName="IDPERSONNAGEDUCINEMA")
public class Realisateur extends PersonnageDuCinema implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy="realisateur")
	private Set<RealisateurFilm> realisateurFilms = new HashSet<RealisateurFilm>(0);
	
	public Realisateur (){}

	public Set<RealisateurFilm> getRealisateurFilms() {
		return realisateurFilms;
	}

	public void setRealisateurFilms(Set<RealisateurFilm> realisateurFilms) {
		this.realisateurFilms = realisateurFilms;
	};

}
