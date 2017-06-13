package Main;
import java.awt.EventQueue;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Controllers.LoginController;
import Model.Acteur;
import Model.Client;
import Model.Film;
import Model.GestionnaireDeConnexion;
import Model.PersonnageDuCinema;
import Services.CourtierBdUtilisateur;
import Services.HibernateUtil;
import UI.LoginWindow;

public class Main {
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
										
					LoginController loginController = new LoginController();
					GestionnaireDeConnexion loginModel 	= new GestionnaireDeConnexion();
					LoginWindow loginView = new LoginWindow();

					 
					loginModel.addObserver(loginView);
					
					loginController.addModel(loginModel);
					loginController.addView(loginView);

					loginView.addController(loginController);
					
					loginView.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});			
		
		
	}
}

