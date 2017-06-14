package Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="FORFAIT")
public class Forfait implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="IDFORFAIT")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="SEQ")
	@SequenceGenerator(name="SEQ", sequenceName="SEQ_FORFAIT")
	private int idForfait;
	
	@Column(name="NOM")
	private String nom;
	
	@Column(name="COUTMENSUEL")
	private float coutMensuel;
	
	@Column(name="LOCATIONMAXFILMS")
	private int locationMaxFilms;
	
	@Column(name="DUREEMAXJOURS")
	private int dureeMaxJours;
	
	@OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="IDCLIENT")
	private Set<Client> clients = new HashSet<Client>(0);
	
	public Forfait() {
	}

	public int getIdForfait() {
		return idForfait;
	}

	public void setIdForfait(int idForfait) {
		this.idForfait = idForfait;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public float getCoutMensuel() {
		return coutMensuel;
	}

	public void setCoutMensuel(float coutMensuel) {
		this.coutMensuel = coutMensuel;
	}

	public int getLocationMaxFilms() {
		return locationMaxFilms;
	}

	public void setLocationMaxFilms(int locationMaxFilms) {
		this.locationMaxFilms = locationMaxFilms;
	}

	public int getDureeMaxJours() {
		return dureeMaxJours;
	}

	public void setDureeMaxJours(int dureeMaxJours) {
		this.dureeMaxJours = dureeMaxJours;
	}

	public Set<Client> getClients() {
		return clients;
	}

	public void setClients(HashSet<Client> clients) {
		this.clients = clients;
	}
}
