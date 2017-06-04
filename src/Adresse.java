
public class Adresse implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idAdresse;
	private String codePostale;
	private String province;
	private String ville;
	private int numeroCivique;
	
	public Adresse() {
	}

	public int getIdAdresse() {
		return idAdresse;
	}

	public void setIdAdresse(int idAdresse) {
		this.idAdresse = idAdresse;
	}

	public String getCodePostale() {
		return codePostale;
	}

	public void setCodePostale(String codePostale) {
		this.codePostale = codePostale;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getNumeroCivique() {
		return numeroCivique;
	}

	public void setNumeroCivique(int numeroCivique) {
		this.numeroCivique = numeroCivique;
	}

}
