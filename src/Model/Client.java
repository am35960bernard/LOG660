package Model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CLIENT")
public class Client extends Utilisateur implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="IDCLIENT")
	private int IdClient = idUtilisateur;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="IDFORFAIT")
	private int idForfait;

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
