package Model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PERSONNAGEDUCINEMA")
@Inheritance(strategy=InheritanceType.JOINED)
public class PersonnageDuCinema implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="IDPERSONNAGEDUCINEMA")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="SEQ")
	@SequenceGenerator(name="SEQ", sequenceName="SEQ_PERSONNAGEDUCINEMA")
	protected int idPersonneDuCinema;
	
	@Column(name="NOMCOMPLET")
	protected String nomComplet;
	
	@Column(name="DATEANNIVERSAIRE")
	protected Date dateAnniversaire;
	
	@Column(name="LIEUNAISSANCE")
	protected String lieuNaissance;
	
	@Column(name="BIOGRAPHIE")
	protected String biographie;
	
	@Column(name="LIENPHOTO")
	protected String lienPhoto;

	
	public PersonnageDuCinema(){}

	public int getIdPersonneDuCinema() {
		return idPersonneDuCinema;
	}

	public void setIdPersonneDuCinema(int idPersonneDuCinema) {
		this.idPersonneDuCinema = idPersonneDuCinema;
	}

	public String getNomComplet() {
		return nomComplet;
	}

	public void setNomComplet(String nomComplet) {
		this.nomComplet = nomComplet;
	}

	public Date getDateAnniversaire() {
		return dateAnniversaire;
	}

	public void setDateAnniversaire(Date dateAnniversaire) {
		this.dateAnniversaire = dateAnniversaire;
	}

	public String getLieuNaissance() {
		return lieuNaissance;
	}

	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}

	public String getBiographie() {
		return biographie;
	}

	public void setBiographie(String biographie) {
		this.biographie = biographie;
	}

	public String getLienPhoto() {
		return lienPhoto;
	}

	public void setLienPhoto(String lienPhoto) {
		this.lienPhoto = lienPhoto;
	}

}
