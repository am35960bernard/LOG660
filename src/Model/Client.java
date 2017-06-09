package Model;
import java.util.Date;

public class Client extends Utilisateur implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	
	private int idForfait;
	private int IdClient = idUtilisateur;

	public Client(){};	
	
	public Client(int idUtilisateur,String nom,String prenom,String motDePasse,String courriel,String noTelephone,Date dateAnniversaire,int idAdresse,int idForfait) {
		super(idUtilisateur,nom,prenom,motDePasse,courriel,noTelephone,dateAnniversaire,idAdresse);
		this.idForfait = idForfait;
		this.IdClient = idUtilisateur;
	}	

	public int getIdClient() {
		return IdClient;
	}

	public void setIdClient(int idClient) {
		this.IdClient = idClient;
	}

	public int getIdForfait() {
		return idForfait;
	}

	public void setIdForfait(int idForfait) {
		this.idForfait = idForfait;
	}
	
}
