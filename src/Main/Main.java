package Main;
import java.awt.EventQueue;
import Controllers.LoginController;
import Model.GestionnaireDeConnexion;
import UI.LoginWindow;
import UI.SearchWindow;

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
