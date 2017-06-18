package UI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;

import javax.swing.JButton;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import Controllers.FilmController;
import Controllers.GestionnaireDeFilm;
import Controllers.Observer;

import com.jgoodies.forms.layout.FormSpecs;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;


public class LoginWindow extends JFrame implements Observer{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCourrielUtilisateur;
	private JButton btnConnexion;
	private JPasswordField txtMotDePasseUtilisateur;
	private JPanel messagePanel;
	private JLabel messageLabel;

	public void addController (ActionListener controller){
		btnConnexion.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				messageLabel.setText("Connexion en cours...");
				messageLabel.setForeground(Color.BLACK);

				SwingUtilities.invokeLater(new Runnable() {
				    public void run() {
				    	controller.actionPerformed(e);
				    }
				});
			}
		});
	}	
	
	public void update(String str) {
		Color couleurValiditeConnexion;
		String messageValiditeConnexion;
		
		if(str == null){
			couleurValiditeConnexion = Color.RED;
			messageValiditeConnexion = "Échec de l'authentification, veuillez réessayer...";
		}else{
			couleurValiditeConnexion = Color.BLACK;
			messageValiditeConnexion = "Veuillez vous identifier...";;
			SearchWindow searchWindow = new SearchWindow(this);
			FilmController filmController = new FilmController();
			GestionnaireDeFilm filmModel = new GestionnaireDeFilm();
			filmController.addModel(filmModel);
			filmController.addView(searchWindow);
			searchWindow.addController(filmController);
			searchWindow.setVisible(true);
			this.dispose();
		}		
		messageLabel.setText(messageValiditeConnexion);
		messageLabel.setForeground(couleurValiditeConnexion);
	} 


	/**
	 * Create the login frame.
	 */
	public LoginWindow() {
		setResizable(false);
		setTitle("Webflix - Connexion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 178);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnConnexion = new JButton("Connexion");
		btnConnexion.setFont(new Font("Tahoma", Font.BOLD, 13));
				
		panel.add(btnConnexion);
		
		JPanel mainPanel = new JPanel();
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNomDutilisateur = new JLabel("Adresse courriel:");
		mainPanel.add(lblNomDutilisateur, "2, 2, right, default");
		
		txtCourrielUtilisateur = new JTextField();
		txtCourrielUtilisateur.setPreferredSize(new Dimension(6, 25));
		txtCourrielUtilisateur.setText("WilmaSHuff27@hotmail.com");
		mainPanel.add(txtCourrielUtilisateur, "4, 2, fill, default");
		txtCourrielUtilisateur.setColumns(10);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe:");
		mainPanel.add(lblMotDePasse, "2, 4, right, default");
		
		txtMotDePasseUtilisateur = new JPasswordField();
		txtMotDePasseUtilisateur.setPreferredSize(new Dimension(6, 25));
		txtMotDePasseUtilisateur.setText("Thi0ruimie");
		mainPanel.add(txtMotDePasseUtilisateur, "4, 4");
		
		messagePanel = new JPanel();
		contentPane.add(messagePanel, BorderLayout.NORTH);
		
		messageLabel = new JLabel("Veuillez vous identifier...");
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		messageLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		messagePanel.add(messageLabel);

	}
	 
	public JTextField getTxtCourrielUtilisateur(){
    	return txtCourrielUtilisateur;
	}

	public JTextField getTxtMotDePasseUtilisateur() {
		return txtMotDePasseUtilisateur;
	}

}
