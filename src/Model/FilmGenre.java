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
	name="FILMGENRE",
	uniqueConstraints=@UniqueConstraint(columnNames={ "IDGENRE", "IDFILM" })
)
public class FilmGenre implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="IDFILMGENRE")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="SEQ")
	@SequenceGenerator(name="SEQ", sequenceName="SEQ_FILMGENRE")
	private int idFilmGenre;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="IDGENRE")
	private Genre genre;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="IDFILM")
	private Film film;

	public int getFilmGenreId() {
		return idFilmGenre;
	}

	public void setFilmGenreId(int idFilmGenre) {
		this.idFilmGenre = idFilmGenre;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setActeur(Genre genre) {
		this.genre = genre;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}
    
}
