package Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="CLIENT")
@PrimaryKeyJoinColumn(name="IDCLIENT", referencedColumnName="IDUTILISATEUR")
public class Client extends Utilisateur implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="IDFORFAIT")
	private Forfait forfait;

	@OneToMany(mappedBy="client")
	private Set<Location> locations = new HashSet<Location>(0);
	
	public Client(){};	

	public Forfait getForfait() {
		return forfait;
	}

	public void setForfait(Forfait forfait) {
		this.forfait = forfait;
	}

	public Set<Location> getLocations() {
		return locations;
	}

	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}
	
}
