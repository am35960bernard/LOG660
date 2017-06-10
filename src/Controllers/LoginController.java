package Controllers;

import Model.GestionnaireDeConnexion;
import UI.LoginWindow;


public class LoginController implements java.awt.event.ActionListener{

	GestionnaireDeConnexion model;
	LoginWindow view;	
	
	public void addModel(GestionnaireDeConnexion model){
		this.model = model;
	} 

	public void addView(LoginWindow view){
		this.view = view;
	} 	

	
	public void actionPerformed(java.awt.event.ActionEvent e){
		
		String txtCourrielUtilisateur = (this.view.getTxtCourrielUtilisateur().getText()).trim();
		String txtMotDePasseUtilisateur = (this.view.getTxtMotDePasseUtilisateur().getText()).trim();
		
		model.connect(txtCourrielUtilisateur, txtMotDePasseUtilisateur);
	} 

	
}
