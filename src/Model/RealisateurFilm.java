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
import javax.persistence.UniqueConstraint;

@Entity
@Table(
	name="REALISATEURFILM",
	uniqueConstraints=@UniqueConstraint(columnNames={ "IDREALISATEUR", "IDFILM" })
)
public class RealisateurFilm implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="IDREALISATEURFILM")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="SEQ")
	@SequenceGenerator(name="SEQ", sequenceName="SEQ_REALISATEURFILM")
	private int idRealisateurFilm;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="IDREALISATEUR")
	private Realisateur realisateur;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="IDFILM")
	private Film film;

	public int getIdRealisateurFilm() {
		return idRealisateurFilm;
	}

	public void setIdRealisateurFilm(int idRealisateurFilm) {
		this.idRealisateurFilm = idRealisateurFilm;
	}

	public Realisateur getRealisateur() {
		return realisateur;
	}

	public void setRealisateur(Realisateur realisateur) {
		this.realisateur = realisateur;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}
	
}
