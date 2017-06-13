package Model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="CLIENT")
@PrimaryKeyJoinColumn(name="IDCLIENT", referencedColumnName="IDUTILISATEUR")
public class Client extends Utilisateur implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne//(fetch=FetchType.EAGER, targetEntity=Forfait.class)
    @JoinColumn(name="IDFORFAIT")
	private Forfait forfait;

	public Client(){};	
	
	/*public Client(String nom,String prenom,String motDePasse,String courriel,String noTelephone,Date dateAnniversaire,int idAdresse,int idForfait) {
		super(nom,prenom,motDePasse,courriel,noTelephone,dateAnniversaire,idAdresse);
		this.idForfait = idForfait;
	}*/

	public Forfait getForfait() {
		return forfait;
	}

	public void setForfait(Forfait forfait) {
		this.forfait = forfait;
	}
	
}
