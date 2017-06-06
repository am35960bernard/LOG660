package Model;
import java.util.Date;

public class Client extends Utilisateur implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int idForfait;

	public Client(){};	
	
	public Client(int idUtilisateur,String nom,String prenom,String motDePasse,String courriel,String noTelephone,Date dateAnniversaire,int idAdresse,int idForfait) {
		super(idUtilisateur,nom,prenom,motDePasse,courriel,noTelephone,dateAnniversaire,idAdresse);
		this.idForfait = idForfait;
	}	

	public int getIdClient() {
		return idUtilisateur;
	}

	public void setIdClient(int idClient) {
		this.idUtilisateur = idClient;
	}

	public int getIdForfait() {
		return idForfait;
	}

	public void setIdForfait(int idForfait) {
		this.idForfait = idForfait;
	}

}
