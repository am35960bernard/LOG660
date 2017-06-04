public class Forfait implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idForfait;
	private String nom;
	private float coutMensuel;
	private int locationMaxFilms;
	private int dureeMaxJours;
	
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

}
