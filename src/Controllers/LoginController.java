package Controllers;

import Model.CourtierBdUtilisateur;
import UI.LoginWindow;


public class LoginController implements java.awt.event.ActionListener{

	CourtierBdUtilisateur model;
	LoginWindow view;	
	
	public void addModel(CourtierBdUtilisateur model){
		this.model = model;
	} 

	public void addView(LoginWindow view){
		this.view = view;
	} 	

	
	public void actionPerformed(java.awt.event.ActionEvent e){
		
		String txtCourrielUtilisateur = (this.view.getTxtCourrielUtilisateur().getText()).trim();
		String txtMotDePasseUtilisateur = (this.view.getTxtMotDePasseUtilisateur().getText()).trim();
		
		model.validateAuthentication(txtCourrielUtilisateur, txtMotDePasseUtilisateur);
	} 

	
}
