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
	name="PAYSFILM",
	uniqueConstraints=@UniqueConstraint(columnNames={ "IDPAYS", "IDFILM" })
)
public class PaysFilm implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="IDPAYSFILM")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="SEQ")
	@SequenceGenerator(name="SEQ", sequenceName="SEQ_PAYSFILM")
	private int idPaysFilm;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="IDPAYS")
	private Pays pays;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="IDFILM")
	private Film film;

	public int getIdPaysFilm() {
		return idPaysFilm;
	}

	public void setIdPaysFilm(int idPaysFilm) {
		this.idPaysFilm = idPaysFilm;
	}

	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}
	
}
