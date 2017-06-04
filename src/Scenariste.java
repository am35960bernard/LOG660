
public class Scenariste implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idScenariste;
	private String nomScenariste;
	
	public Scenariste() {

	}

	public int getIdScenariste() {
		return idScenariste;
	}

	public void setIdScenariste(int idScenariste) {
		this.idScenariste = idScenariste;
	}

	public String getNomScenariste() {
		return nomScenariste;
	}

	public void setNomScenariste(String nomScenariste) {
		this.nomScenariste = nomScenariste;
	}

}
