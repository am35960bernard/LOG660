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
	name="ACTEURFILM",
	uniqueConstraints=@UniqueConstraint(columnNames={ "IDACTEUR", "IDFILM" })
)
public class ActeurFilm implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="IDACTEURFILM")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="SEQ")
	@SequenceGenerator(name="SEQ", sequenceName="SEQ_ACTEURFILM")
	private int idActeurFilm;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="IDACTEUR")
	private Acteur acteur;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="IDFILM")
	private Film film;
	
    @Column(name="ROLE")
    private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getActeurFilmId() {
		return idActeurFilm;
	}

	public void setActeurFilmId(int acteurFilmId) {
		this.idActeurFilm = acteurFilmId;
	}

	public Acteur getActeur() {
		return acteur;
	}

	public void setActeur(Acteur acteur) {
		this.acteur = acteur;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}
    
}
