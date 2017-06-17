package Model;

import Model.Observable;
import Services.CourtierBdUtilisateur;

public class GestionnaireDeConnexion extends Observable{

	public GestionnaireDeConnexion() {}

	public void connect(String txtCourrielUtilisateur,String txtMotDePasseUtilisateur){
		
		String authenticationIdUser;
		
		CourtierBdUtilisateur courtierUtilisateur = new CourtierBdUtilisateur();		
		
		txtCourrielUtilisateur = txtCourrielUtilisateur.replaceAll("\"","").replaceAll("\'","");
		txtMotDePasseUtilisateur = txtMotDePasseUtilisateur.replaceAll("\"","").replaceAll("\'","");
		
		authenticationIdUser = courtierUtilisateur.validateAuthentication(txtCourrielUtilisateur,txtMotDePasseUtilisateur);
		
		this.notifyObserver(authenticationIdUser);
	}

}
