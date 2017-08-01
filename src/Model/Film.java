package Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="FILM")
public class Film implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="IDFILM")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="SEQ")
	@SequenceGenerator(name="SEQ", sequenceName="SEQ_FILM")
	private int idFilm;
	
    @Column(name="XMLID")
	private int xmlId;
	
	@Column(name="TITRE")
	private String titre;
	
	@Column(name="ANNEESORTIE")
	private int anneeSortie;
	
	@Column(name="LANGUEORIGINALE")
	private String langueOriginale;
	
	@Column(name="DUREEMINUTE")
	private int duree;
	
	@Column(name="RESUME")
	private String resume;
	
	@Column(name="LIENPOSTER")
	private String photo;

	@OneToMany(mappedBy="film")
	private Set<ActeurFilm> acteurFilms = new HashSet<ActeurFilm>(0);
	
	@OneToMany(mappedBy="film")
	private Set<FilmGenre> filmGenres = new HashSet<FilmGenre>(0);
	
	@OneToMany(mappedBy="film")
	private Set<RealisateurFilm> realisateurFilms = new HashSet<RealisateurFilm>(0);
	
	@OneToMany(mappedBy="film")
	private Set<PaysFilm> paysFilms = new HashSet<PaysFilm>(0);
	
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name="SCENARISTEFILM", 
			joinColumns={ @JoinColumn(name="IDFILM", nullable=false, updatable=false) },
			inverseJoinColumns={ @JoinColumn(name="IDSCENARISTE", nullable=false, updatable=false) })
	private Set<Scenariste> scenaristes = new HashSet<Scenariste>(0);
	
	@OneToMany(mappedBy="film")
	private Set<Exemplaire> exemplaires = new HashSet<Exemplaire>(0);
	
	public Film() {}
	
	public int getIdFilm() {
		return idFilm;
	}

	public void setIdFilm(int idFilm) {
		this.idFilm = idFilm;
	}
	
	public int getXmlId() {
		return xmlId;
	}

	public void setXmlId(int xmlId) {
		this.xmlId = xmlId;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getAnneeSortie() {
		return anneeSortie;
	}

	public void setAnneeSortie(int anneeSortie) {
		this.anneeSortie = anneeSortie;
	}

	public String getLangueOriginale() {
		return langueOriginale;
	}

	public void setLangueOriginale(String langueOriginale) {
		this.langueOriginale = langueOriginale;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Set<ActeurFilm> getActeurFilms() {
		return acteurFilms;
	}

	public void setActeurFilms(Set<ActeurFilm> acteurFilms) {
		this.acteurFilms = acteurFilms;
	}

	public Set<Scenariste> getScenaristes() {
		return scenaristes;
	}

	public void setScenaristes(Set<Scenariste> scenaristes) {
		this.scenaristes = scenaristes;
	}

	public Set<FilmGenre> getFilmGenres() {
		return filmGenres;
	}

	public void setFilmGenres(Set<FilmGenre> filmGenres) {
		this.filmGenres = filmGenres;
	}

	public Set<Exemplaire> getExemplaires() {
		return exemplaires;
	}

	public void setExemplaires(Set<Exemplaire> exemplaires) {
		this.exemplaires = exemplaires;
	}

	public Set<RealisateurFilm> getRealisateurFilms() {
		return realisateurFilms;
	}

	public void setRealisateurFilms(Set<RealisateurFilm> realisateurFilms) {
		this.realisateurFilms = realisateurFilms;
	}

	public Set<PaysFilm> getPaysFilms() {
		return paysFilms;
	}

	public void setPaysFilms(Set<PaysFilm> paysFilms) {
		this.paysFilms = paysFilms;
	}

}
