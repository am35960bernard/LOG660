package Model;

import Model.Observable;
import Services.CourtierBdUtilisateur;

public class GestionnaireDeConnexion extends Observable{

	public GestionnaireDeConnexion() {}

	public void connect(String txtCourrielUtilisateur,String txtMotDePasseUtilisateur){
		
		CourtierBdUtilisateur courtierUtilisateur = new CourtierBdUtilisateur();		
		String authenticationIdUser = courtierUtilisateur.validateAuthentication(txtCourrielUtilisateur,txtMotDePasseUtilisateur);
		
		this.notifyObserver(authenticationIdUser);		
	}

}
