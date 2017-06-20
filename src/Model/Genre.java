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
@Table(name="GENRE")
public class Genre implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="IDGENRE")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="SEQ")
	@SequenceGenerator(name="SEQ", sequenceName="SEQ_GENRE")
	private int idGenre;
	
	@Column(name="NOM")
	private String nom;
	
	@OneToMany(mappedBy="genre")
	private Set<FilmGenre> filmGenres = new HashSet<FilmGenre>(0);
	
	
	public Genre() {}

	public int getIdGenre() {
		return idGenre;
	}

	public void setIdGenre(int idGenre) {
		this.idGenre = idGenre;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Set<FilmGenre> getFilmGenres() {
		return filmGenres;
	}

	public void setFilmGenres(Set<FilmGenre> filmGenres) {
		this.filmGenres = filmGenres;
	}

}
