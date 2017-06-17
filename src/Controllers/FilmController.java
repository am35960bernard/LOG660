package Controllers;

import java.awt.event.ActionEvent;

import UI.LoginWindow;

public class FilmController implements java.awt.event.ActionListener{


	GestionnaireDeFilm model;
		
	
	public void addModel(GestionnaireDeFilm model){
		this.model = model;
	} 


	
	public void actionPerformed(java.awt.event.ActionEvent e){
		
		//model.chercher();
	} 
}
