package UI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;

import javax.swing.JScrollPane;

import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Instant;

import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.SwingConstants;

import Controllers.Observer;
import Model.Acteur;
import Model.ActeurFilm;
import Model.Film;
import Model.FilmGenre;
import Model.Genre;
import Model.Pays;
import Model.PaysFilm;
import Model.PersonnageDuCinema;
import Model.Realisateur;
import Model.RealisateurFilm;
import Model.Scenariste;
import Services.CourtierBdFilm;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.AbstractListModel;

public class SearchWindow extends JFrame implements Observer{

	private static final long serialVersionUID = 8598471863495375567L;
	private int CRITERIA_PANELS_HEIGHT = 30;
	private int CRITERIA_PANELS_MARGIN = 5;

	private JPanel contentPane;
	private JPanel criteriasPanel;
	private JTextArea movieSummaryTextArea;
	private JTextField movieTitleTextField;
	private JTextField releaseYearTextField;
	private JTextField productionCountryTextField;
	private JTextField originalLanguageTextField;
	private JTextField movieLengthTextField;
	private JTextField movieGenreTextField;
	private JTextField movieScenaristTextField;
	private JTextField crewMemberNameTextField;
	private JTextField crewMemberBirthdayTextField;
	private JTextField crewMemberBirthLocationTextField;
	private JTextArea crewMemberBiographyTextArea;
	private JTextField directorTextField;
	private final ButtonGroup crewButtonGroup = new ButtonGroup();
	private JButton searchButton;
	private JList<Film> foundMoviesList;
	private JList<Film> recommandationsList;
	private JList<Acteur> actorsList;
	private JRadioButton directorRadioButton;
	private JRadioButton actorsRadioButton;
	private JButton rentMovieButton;
	private JTextField averageRatingTextField;

	/**
	 * Create the frame.
	 */
	public SearchWindow(LoginWindow loginWindow) {
		setTitle("Webflix - Catalogue de films");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{568};
		gbl_contentPane.rowHeights = new int[]{35, 105, 250};
		gbl_contentPane.columnWeights = new double[]{1.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0};
		contentPane.setLayout(gbl_contentPane);

		JPanel topToolbarPanel = new JPanel();
		GridBagConstraints gbc_topToolbarPanel = new GridBagConstraints();
		gbc_topToolbarPanel.anchor = GridBagConstraints.NORTH;
		gbc_topToolbarPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_topToolbarPanel.insets = new Insets(0, 0, 5, 0);
		gbc_topToolbarPanel.gridx = 0;
		gbc_topToolbarPanel.gridy = 0;
		contentPane.add(topToolbarPanel, gbc_topToolbarPanel);
		topToolbarPanel.setLayout(new BorderLayout(0, 0));

		JPanel addCriteriaPanel = new JPanel();
		topToolbarPanel.add(addCriteriaPanel, BorderLayout.EAST);

		JLabel searchCriteriasTitleLabel = new JLabel("Critères de recherche:");
		addCriteriaPanel.add(searchCriteriasTitleLabel);
		searchCriteriasTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		searchCriteriasTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 15));

		JComboBox criteriaComboBox = new JComboBox();
		criteriaComboBox.setModel(new DefaultComboBoxModel(new String[] {"Titre", "Intervale", "Pays de production", "Langue originale", "Genre", "R\u00E9alisateur", "Acteur"}));
		addCriteriaPanel.add(criteriaComboBox);

		JPanel disconnectPanel = new JPanel();
		topToolbarPanel.add(disconnectPanel, BorderLayout.WEST);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Tahoma", Font.BOLD, 15));
		disconnectPanel.add(menuBar);

		JMenu userMenu = new JMenu("Menu");
		userMenu.setFont(new Font("Tahoma", Font.BOLD, 15));
		menuBar.add(userMenu);

		JMenuItem analysisMenuItem = new JMenuItem("Analyse des locations");
		analysisMenuItem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		analysisMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AnalysisWindow analysisWindow = new AnalysisWindow();
				analysisWindow.setVisible(true);
			}
		});
		userMenu.add(analysisMenuItem);

		JMenuItem disconnectUserMenuItem = new JMenuItem("D\u00E9connexion");
		disconnectUserMenuItem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		disconnectUserMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (loginWindow != null)
				{
					SearchWindow.this.dispose();
					loginWindow.setVisible(true);		
				}
			}
		});
		userMenu.add(disconnectUserMenuItem);

		JScrollPane criteriaScrollPane = new JScrollPane();
		criteriaScrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		criteriaScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		criteriaScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_criteriaScrollPane = new GridBagConstraints();
		gbc_criteriaScrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_criteriaScrollPane.fill = GridBagConstraints.BOTH;
		gbc_criteriaScrollPane.gridx = 0;
		gbc_criteriaScrollPane.gridy = 1;
		contentPane.add(criteriaScrollPane, gbc_criteriaScrollPane);

		JPanel criteriaScrollPanel = new JPanel();
		criteriaScrollPane.setViewportView(criteriaScrollPanel);
		criteriaScrollPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		criteriasPanel = new JPanel();
		criteriaScrollPanel.add(criteriasPanel);
		criteriasPanel.setLayout(new BoxLayout(criteriasPanel, BoxLayout.Y_AXIS));

		JButton addCriteriaButton = new JButton("Ajouter");
		addCriteriaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel panel;
				switch ((String)criteriaComboBox.getSelectedItem())
				{
				case "Titre":
					panel = new TitleCriteriaPanel();
					break;
				case "Intervale":
					panel = new IntervalCriteriaPanel();
					break;
				case "Pays de production":
					panel = new CountryCriteriaPanel();
					break;
				case "Langue originale":
					panel = new LanguageCriteriaPanel();
					break;
				case "Genre":
					panel = new GenreCriteriaPanel();
					break;
				case "Réalisateur":
					panel = new DirectorCriteriaPanel();
					break;
				case "Acteur":
					panel = new ActorCriteriaPanel();
					break;
				default:
					return;
				}

				setCriteriaPanelSize(criteriaScrollPane, panel);
				panel.setAlignmentY(TOP_ALIGNMENT);

				criteriasPanel.add(panel);
				criteriasPanel.revalidate();
			}
		});
		addCriteriaPanel.add(addCriteriaButton);

		searchButton = new JButton("Rechercher");
		addCriteriaPanel.add(searchButton);

		JPanel foundMoviesPanel = new JPanel();
		GridBagConstraints gbc_foundMoviesPanel = new GridBagConstraints();
		gbc_foundMoviesPanel.fill = GridBagConstraints.BOTH;
		gbc_foundMoviesPanel.gridx = 0;
		gbc_foundMoviesPanel.gridy = 2;
		contentPane.add(foundMoviesPanel, gbc_foundMoviesPanel);
		foundMoviesPanel.setLayout(new BorderLayout(0, 0));

		JSplitPane foundMoviesSplitPane = new JSplitPane();
		foundMoviesSplitPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		foundMoviesSplitPane.setResizeWeight(0.25);
		foundMoviesPanel.add(foundMoviesSplitPane, BorderLayout.CENTER);

		JSplitPane movieDetailsSplitPane = new JSplitPane();
		movieDetailsSplitPane.setBorder(null);
		movieDetailsSplitPane.setResizeWeight(0.75);
		foundMoviesSplitPane.setRightComponent(movieDetailsSplitPane);

		JPanel movieDetailsPanel = new JPanel();
		movieDetailsPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		movieDetailsSplitPane.setLeftComponent(movieDetailsPanel);
		GridBagLayout gbl_movieDetailsPanel = new GridBagLayout();
		gbl_movieDetailsPanel.columnWidths = new int[]{0, 0, 0};
		gbl_movieDetailsPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_movieDetailsPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_movieDetailsPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		movieDetailsPanel.setLayout(gbl_movieDetailsPanel);

		JLabel lblDtailsDuFilm = new JLabel("Détails du film:");
		lblDtailsDuFilm.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblDtailsDuFilm = new GridBagConstraints();
		gbc_lblDtailsDuFilm.gridwidth = 2;
		gbc_lblDtailsDuFilm.insets = new Insets(0, 0, 5, 0);
		gbc_lblDtailsDuFilm.gridx = 0;
		gbc_lblDtailsDuFilm.gridy = 0;
		movieDetailsPanel.add(lblDtailsDuFilm, gbc_lblDtailsDuFilm);

		JLabel averageRatingLabel = new JLabel("Cote moyenne:");
		GridBagConstraints gbc_averageRatingLabel = new GridBagConstraints();
		gbc_averageRatingLabel.anchor = GridBagConstraints.EAST;
		gbc_averageRatingLabel.insets = new Insets(0, 0, 5, 5);
		gbc_averageRatingLabel.gridx = 0;
		gbc_averageRatingLabel.gridy = 1;
		movieDetailsPanel.add(averageRatingLabel, gbc_averageRatingLabel);

		averageRatingTextField = new JTextField();
		averageRatingTextField.setPreferredSize(new Dimension(6, 25));
		averageRatingTextField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_averageRatingTextField = new GridBagConstraints();
		gbc_averageRatingTextField.insets = new Insets(0, 0, 5, 0);
		gbc_averageRatingTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_averageRatingTextField.gridx = 1;
		gbc_averageRatingTextField.gridy = 1;
		movieDetailsPanel.add(averageRatingTextField, gbc_averageRatingTextField);
		averageRatingTextField.setColumns(10);

		JLabel movieTitleLabel = new JLabel("Titre du film:");
		GridBagConstraints gbc_movieTitleLabel = new GridBagConstraints();
		gbc_movieTitleLabel.anchor = GridBagConstraints.EAST;
		gbc_movieTitleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_movieTitleLabel.gridx = 0;
		gbc_movieTitleLabel.gridy = 2;
		movieDetailsPanel.add(movieTitleLabel, gbc_movieTitleLabel);

		movieTitleTextField = new JTextField();
		movieTitleTextField.setPreferredSize(new Dimension(6, 25));
		movieTitleTextField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		movieTitleTextField.setColumns(10);
		GridBagConstraints gbc_movieTitleTextField = new GridBagConstraints();
		gbc_movieTitleTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_movieTitleTextField.insets = new Insets(0, 0, 5, 0);
		gbc_movieTitleTextField.gridx = 1;
		gbc_movieTitleTextField.gridy = 2;
		movieDetailsPanel.add(movieTitleTextField, gbc_movieTitleTextField);

		JLabel releaseYearLabel = new JLabel("Année de sortie:");
		GridBagConstraints gbc_releaseYearLabel = new GridBagConstraints();
		gbc_releaseYearLabel.anchor = GridBagConstraints.EAST;
		gbc_releaseYearLabel.insets = new Insets(0, 0, 5, 5);
		gbc_releaseYearLabel.gridx = 0;
		gbc_releaseYearLabel.gridy = 3;
		movieDetailsPanel.add(releaseYearLabel, gbc_releaseYearLabel);

		releaseYearTextField = new JTextField();
		releaseYearTextField.setPreferredSize(new Dimension(6, 25));
		releaseYearTextField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		releaseYearTextField.setColumns(10);
		GridBagConstraints gbc_releaseYearTextField = new GridBagConstraints();
		gbc_releaseYearTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_releaseYearTextField.insets = new Insets(0, 0, 5, 0);
		gbc_releaseYearTextField.gridx = 1;
		gbc_releaseYearTextField.gridy = 3;
		movieDetailsPanel.add(releaseYearTextField, gbc_releaseYearTextField);

		JLabel productionCountryLabel = new JLabel("Pays de production:");
		GridBagConstraints gbc_productionCountryLabel = new GridBagConstraints();
		gbc_productionCountryLabel.anchor = GridBagConstraints.EAST;
		gbc_productionCountryLabel.insets = new Insets(0, 0, 5, 5);
		gbc_productionCountryLabel.gridx = 0;
		gbc_productionCountryLabel.gridy = 4;
		movieDetailsPanel.add(productionCountryLabel, gbc_productionCountryLabel);

		productionCountryTextField = new JTextField();
		productionCountryTextField.setPreferredSize(new Dimension(6, 25));
		productionCountryTextField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		productionCountryTextField.setColumns(10);
		GridBagConstraints gbc_productionCountryTextField = new GridBagConstraints();
		gbc_productionCountryTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_productionCountryTextField.insets = new Insets(0, 0, 5, 0);
		gbc_productionCountryTextField.gridx = 1;
		gbc_productionCountryTextField.gridy = 4;
		movieDetailsPanel.add(productionCountryTextField, gbc_productionCountryTextField);

		JLabel originalLanguageLabel = new JLabel("Langue originale:");
		GridBagConstraints gbc_originalLanguageLabel = new GridBagConstraints();
		gbc_originalLanguageLabel.anchor = GridBagConstraints.EAST;
		gbc_originalLanguageLabel.insets = new Insets(0, 0, 5, 5);
		gbc_originalLanguageLabel.gridx = 0;
		gbc_originalLanguageLabel.gridy = 5;
		movieDetailsPanel.add(originalLanguageLabel, gbc_originalLanguageLabel);

		originalLanguageTextField = new JTextField();
		originalLanguageTextField.setPreferredSize(new Dimension(6, 25));
		originalLanguageTextField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		originalLanguageTextField.setColumns(10);
		GridBagConstraints gbc_originalLanguageTextField = new GridBagConstraints();
		gbc_originalLanguageTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_originalLanguageTextField.insets = new Insets(0, 0, 5, 0);
		gbc_originalLanguageTextField.gridx = 1;
		gbc_originalLanguageTextField.gridy = 5;
		movieDetailsPanel.add(originalLanguageTextField, gbc_originalLanguageTextField);

		JLabel movieLengthLabel = new JLabel("Durée:");
		GridBagConstraints gbc_movieLengthLabel = new GridBagConstraints();
		gbc_movieLengthLabel.anchor = GridBagConstraints.EAST;
		gbc_movieLengthLabel.insets = new Insets(0, 0, 5, 5);
		gbc_movieLengthLabel.gridx = 0;
		gbc_movieLengthLabel.gridy = 6;
		movieDetailsPanel.add(movieLengthLabel, gbc_movieLengthLabel);

		movieLengthTextField = new JTextField();
		movieLengthTextField.setPreferredSize(new Dimension(6, 25));
		movieLengthTextField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		movieLengthTextField.setColumns(10);
		GridBagConstraints gbc_movieLengthTextField = new GridBagConstraints();
		gbc_movieLengthTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_movieLengthTextField.insets = new Insets(0, 0, 5, 0);
		gbc_movieLengthTextField.gridx = 1;
		gbc_movieLengthTextField.gridy = 6;
		movieDetailsPanel.add(movieLengthTextField, gbc_movieLengthTextField);

		JLabel movieGenreLabel = new JLabel("Genres:");
		GridBagConstraints gbc_movieGenreLabel = new GridBagConstraints();
		gbc_movieGenreLabel.anchor = GridBagConstraints.EAST;
		gbc_movieGenreLabel.insets = new Insets(0, 0, 5, 5);
		gbc_movieGenreLabel.gridx = 0;
		gbc_movieGenreLabel.gridy = 7;
		movieDetailsPanel.add(movieGenreLabel, gbc_movieGenreLabel);

		movieGenreTextField = new JTextField();
		movieGenreTextField.setPreferredSize(new Dimension(6, 25));
		movieGenreTextField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		movieGenreTextField.setColumns(10);
		GridBagConstraints gbc_movieGenreTextField = new GridBagConstraints();
		gbc_movieGenreTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_movieGenreTextField.insets = new Insets(0, 0, 5, 0);
		gbc_movieGenreTextField.gridx = 1;
		gbc_movieGenreTextField.gridy = 7;
		movieDetailsPanel.add(movieGenreTextField, gbc_movieGenreTextField);

		JLabel movieScenaristLabel = new JLabel("Sc\u00E9naristes:");
		GridBagConstraints gbc_movieScenaristLabel = new GridBagConstraints();
		gbc_movieScenaristLabel.anchor = GridBagConstraints.EAST;
		gbc_movieScenaristLabel.insets = new Insets(0, 0, 5, 5);
		gbc_movieScenaristLabel.gridx = 0;
		gbc_movieScenaristLabel.gridy = 8;
		movieDetailsPanel.add(movieScenaristLabel, gbc_movieScenaristLabel);

		movieScenaristTextField = new JTextField();
		movieScenaristTextField.setPreferredSize(new Dimension(6, 25));
		movieScenaristTextField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		movieScenaristTextField.setColumns(10);
		GridBagConstraints gbc_movieScenaristTextField = new GridBagConstraints();
		gbc_movieScenaristTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_movieScenaristTextField.insets = new Insets(0, 0, 5, 0);
		gbc_movieScenaristTextField.gridx = 1;
		gbc_movieScenaristTextField.gridy = 8;
		movieDetailsPanel.add(movieScenaristTextField, gbc_movieScenaristTextField);

		JLabel movieSummaryLabel = new JLabel("Résumé du scénario:");
		GridBagConstraints gbc_movieSummaryLabel = new GridBagConstraints();
		gbc_movieSummaryLabel.anchor = GridBagConstraints.NORTHEAST;
		gbc_movieSummaryLabel.insets = new Insets(0, 0, 0, 5);
		gbc_movieSummaryLabel.gridx = 0;
		gbc_movieSummaryLabel.gridy = 9;
		movieDetailsPanel.add(movieSummaryLabel, gbc_movieSummaryLabel);

		movieSummaryTextArea = new JTextArea();
		movieSummaryTextArea.setWrapStyleWord(true);
		movieSummaryTextArea.setLineWrap(true);
		movieSummaryTextArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_movieSummaryTextArea = new GridBagConstraints();
		gbc_movieSummaryTextArea.fill = GridBagConstraints.BOTH;
		gbc_movieSummaryTextArea.gridx = 1;
		gbc_movieSummaryTextArea.gridy = 9;
		movieDetailsPanel.add(movieSummaryTextArea, gbc_movieSummaryTextArea);

		JSplitPane actorsSplitPane = new JSplitPane();
		actorsSplitPane.setBorder(null);
		actorsSplitPane.setResizeWeight(0.5);
		actorsSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		movieDetailsSplitPane.setRightComponent(actorsSplitPane);

		JPanel actorDetailsPanel = new JPanel();
		actorDetailsPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		actorsSplitPane.setRightComponent(actorDetailsPanel);
		GridBagLayout gbl_actorDetailsPanel = new GridBagLayout();
		gbl_actorDetailsPanel.columnWidths = new int[]{0, 0, 0};
		gbl_actorDetailsPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_actorDetailsPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_actorDetailsPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		actorDetailsPanel.setLayout(gbl_actorDetailsPanel);

		JLabel crewMemberTitleLabel = new JLabel("Détails sur cet équipier:");
		crewMemberTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_crewMemberTitleLabel = new GridBagConstraints();
		gbc_crewMemberTitleLabel.gridwidth = 2;
		gbc_crewMemberTitleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_crewMemberTitleLabel.gridx = 0;
		gbc_crewMemberTitleLabel.gridy = 0;
		actorDetailsPanel.add(crewMemberTitleLabel, gbc_crewMemberTitleLabel);

		JLabel crewMemberNameLabel = new JLabel("Nom:");
		GridBagConstraints gbc_crewMemberNameLabel = new GridBagConstraints();
		gbc_crewMemberNameLabel.anchor = GridBagConstraints.EAST;
		gbc_crewMemberNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_crewMemberNameLabel.gridx = 0;
		gbc_crewMemberNameLabel.gridy = 1;
		actorDetailsPanel.add(crewMemberNameLabel, gbc_crewMemberNameLabel);

		crewMemberNameTextField = new JTextField();
		crewMemberNameTextField.setPreferredSize(new Dimension(6, 25));
		crewMemberNameTextField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_crewMemberNameTextField = new GridBagConstraints();
		gbc_crewMemberNameTextField.insets = new Insets(0, 0, 5, 0);
		gbc_crewMemberNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_crewMemberNameTextField.gridx = 1;
		gbc_crewMemberNameTextField.gridy = 1;
		actorDetailsPanel.add(crewMemberNameTextField, gbc_crewMemberNameTextField);
		crewMemberNameTextField.setColumns(10);

		JLabel crewMemberBirthdayLabel = new JLabel("Date de naissance:");
		GridBagConstraints gbc_crewMemberBirthdayLabel = new GridBagConstraints();
		gbc_crewMemberBirthdayLabel.anchor = GridBagConstraints.EAST;
		gbc_crewMemberBirthdayLabel.insets = new Insets(0, 0, 5, 5);
		gbc_crewMemberBirthdayLabel.gridx = 0;
		gbc_crewMemberBirthdayLabel.gridy = 2;
		actorDetailsPanel.add(crewMemberBirthdayLabel, gbc_crewMemberBirthdayLabel);

		crewMemberBirthdayTextField = new JTextField();
		crewMemberBirthdayTextField.setPreferredSize(new Dimension(6, 25));
		crewMemberBirthdayTextField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_crewMemberBirthdayTextField = new GridBagConstraints();
		gbc_crewMemberBirthdayTextField.insets = new Insets(0, 0, 5, 0);
		gbc_crewMemberBirthdayTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_crewMemberBirthdayTextField.gridx = 1;
		gbc_crewMemberBirthdayTextField.gridy = 2;
		actorDetailsPanel.add(crewMemberBirthdayTextField, gbc_crewMemberBirthdayTextField);
		crewMemberBirthdayTextField.setColumns(10);

		JLabel crewMemberBirthLocationLabel = new JLabel("Lieu de naissance:");
		GridBagConstraints gbc_crewMemberBirthLocationLabel = new GridBagConstraints();
		gbc_crewMemberBirthLocationLabel.anchor = GridBagConstraints.EAST;
		gbc_crewMemberBirthLocationLabel.insets = new Insets(0, 0, 5, 5);
		gbc_crewMemberBirthLocationLabel.gridx = 0;
		gbc_crewMemberBirthLocationLabel.gridy = 3;
		actorDetailsPanel.add(crewMemberBirthLocationLabel, gbc_crewMemberBirthLocationLabel);

		crewMemberBirthLocationTextField = new JTextField();
		crewMemberBirthLocationTextField.setPreferredSize(new Dimension(6, 25));
		crewMemberBirthLocationTextField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_crewMemberBirthLocationTextField = new GridBagConstraints();
		gbc_crewMemberBirthLocationTextField.insets = new Insets(0, 0, 5, 0);
		gbc_crewMemberBirthLocationTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_crewMemberBirthLocationTextField.gridx = 1;
		gbc_crewMemberBirthLocationTextField.gridy = 3;
		actorDetailsPanel.add(crewMemberBirthLocationTextField, gbc_crewMemberBirthLocationTextField);
		crewMemberBirthLocationTextField.setColumns(10);

		JLabel crewMemberBiographyLabel = new JLabel("Biographie:");
		GridBagConstraints gbc_crewMemberBiographyLabel = new GridBagConstraints();
		gbc_crewMemberBiographyLabel.anchor = GridBagConstraints.NORTHEAST;
		gbc_crewMemberBiographyLabel.insets = new Insets(0, 0, 0, 5);
		gbc_crewMemberBiographyLabel.gridx = 0;
		gbc_crewMemberBiographyLabel.gridy = 4;
		actorDetailsPanel.add(crewMemberBiographyLabel, gbc_crewMemberBiographyLabel);

		crewMemberBiographyTextArea = new JTextArea();
		crewMemberBiographyTextArea.setWrapStyleWord(true);
		crewMemberBiographyTextArea.setLineWrap(true);
		crewMemberBiographyTextArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_crewMemberBiographyTextArea = new GridBagConstraints();
		gbc_crewMemberBiographyTextArea.fill = GridBagConstraints.BOTH;
		gbc_crewMemberBiographyTextArea.gridx = 1;
		gbc_crewMemberBiographyTextArea.gridy = 4;
		actorDetailsPanel.add(crewMemberBiographyTextArea, gbc_crewMemberBiographyTextArea);

		JPanel directorAndActorsPanel = new JPanel();
		directorAndActorsPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		actorsSplitPane.setLeftComponent(directorAndActorsPanel);
		GridBagLayout gbl_directorAndActorsPanel = new GridBagLayout();
		gbl_directorAndActorsPanel.columnWidths = new int[]{0, 0, 0};
		gbl_directorAndActorsPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_directorAndActorsPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_directorAndActorsPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		directorAndActorsPanel.setLayout(gbl_directorAndActorsPanel);

		JLabel crewTitleLabel = new JLabel("Équipe de tournage:");
		crewTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_crewTitleLabel = new GridBagConstraints();
		gbc_crewTitleLabel.gridwidth = 2;
		gbc_crewTitleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_crewTitleLabel.gridx = 0;
		gbc_crewTitleLabel.gridy = 0;
		directorAndActorsPanel.add(crewTitleLabel, gbc_crewTitleLabel);

		directorRadioButton = new JRadioButton("R\u00E9alisateur:");
		directorRadioButton.setSelected(true);
		crewButtonGroup.add(directorRadioButton);
		GridBagConstraints gbc_directorRadioButton = new GridBagConstraints();
		gbc_directorRadioButton.anchor = GridBagConstraints.WEST;
		gbc_directorRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_directorRadioButton.gridx = 0;
		gbc_directorRadioButton.gridy = 1;
		directorAndActorsPanel.add(directorRadioButton, gbc_directorRadioButton);

		directorTextField = new JTextField();
		directorTextField.setPreferredSize(new Dimension(6, 25));
		directorTextField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_directorTextField = new GridBagConstraints();
		gbc_directorTextField.insets = new Insets(0, 0, 5, 0);
		gbc_directorTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_directorTextField.gridx = 1;
		gbc_directorTextField.gridy = 1;
		directorAndActorsPanel.add(directorTextField, gbc_directorTextField);
		directorTextField.setColumns(10);

		actorsRadioButton = new JRadioButton("Acteurs:");
		crewButtonGroup.add(actorsRadioButton);
		GridBagConstraints gbc_actorsRadioButton = new GridBagConstraints();
		gbc_actorsRadioButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_actorsRadioButton.insets = new Insets(0, 0, 0, 5);
		gbc_actorsRadioButton.gridx = 0;
		gbc_actorsRadioButton.gridy = 2;
		directorAndActorsPanel.add(actorsRadioButton, gbc_actorsRadioButton);

		ItemListener crewMemberTypeListener = new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (directorRadioButton.isSelected())
				{
					actorsList.clearSelection();
					actorsList.setEnabled(false);
				}
				else if (actorsRadioButton.isSelected())
				{
					if (actorsList.getModel().getSize() > 0)
					{
						actorsList.setSelectedIndex(0);
					}
					actorsList.setEnabled(true);
				}
				showCrewMemberDetails();
			}
		};
		directorRadioButton.addItemListener(crewMemberTypeListener);
		actorsRadioButton.addItemListener(crewMemberTypeListener);

		JScrollPane actorsScrollPane = new JScrollPane();
		GridBagConstraints gbc_actorsScrollPane = new GridBagConstraints();
		gbc_actorsScrollPane.fill = GridBagConstraints.BOTH;
		gbc_actorsScrollPane.gridx = 1;
		gbc_actorsScrollPane.gridy = 2;
		directorAndActorsPanel.add(actorsScrollPane, gbc_actorsScrollPane);

		actorsList = new JList<Acteur>();
		actorsScrollPane.setViewportView(actorsList);
		actorsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		actorsList.setEnabled(false);
		actorsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				showCrewMemberDetails();
			}
		});
		actorsList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		actorsList.setCellRenderer(new ActorListCellRenderer());

		JPanel foundMoviesListPanel = new JPanel();
		foundMoviesListPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		foundMoviesSplitPane.setLeftComponent(foundMoviesListPanel);
		GridBagLayout gbl_foundMoviesListPanel = new GridBagLayout();
		gbl_foundMoviesListPanel.columnWidths = new int[] {0};
		gbl_foundMoviesListPanel.rowHeights = new int[] {0, 0, 0};
		gbl_foundMoviesListPanel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_foundMoviesListPanel.rowWeights = new double[]{1.0, 0.0, 0.0};
		foundMoviesListPanel.setLayout(gbl_foundMoviesListPanel);

		JPanel topFoundMoviesListPanel = new JPanel();
		GridBagConstraints gbc_topFoundMoviesListPanel = new GridBagConstraints();
		gbc_topFoundMoviesListPanel.weighty = 1.0;
		gbc_topFoundMoviesListPanel.fill = GridBagConstraints.BOTH;
		gbc_topFoundMoviesListPanel.insets = new Insets(0, 0, 5, 0);
		gbc_topFoundMoviesListPanel.gridx = 0;
		gbc_topFoundMoviesListPanel.gridy = 0;
		foundMoviesListPanel.add(topFoundMoviesListPanel, gbc_topFoundMoviesListPanel);
		GridBagLayout gbl_topFoundMoviesListPanel = new GridBagLayout();
		gbl_topFoundMoviesListPanel.columnWidths = new int[]{0, 0};
		gbl_topFoundMoviesListPanel.rowHeights = new int[] {0, 0};
		gbl_topFoundMoviesListPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_topFoundMoviesListPanel.rowWeights = new double[]{0.0, 1.0};
		topFoundMoviesListPanel.setLayout(gbl_topFoundMoviesListPanel);

		JLabel lblRsultatDeLa = new JLabel("R\u00E9sultat de la recherche:");
		GridBagConstraints gbc_lblRsultatDeLa = new GridBagConstraints();
		gbc_lblRsultatDeLa.insets = new Insets(0, 0, 5, 0);
		gbc_lblRsultatDeLa.gridx = 0;
		gbc_lblRsultatDeLa.gridy = 0;
		topFoundMoviesListPanel.add(lblRsultatDeLa, gbc_lblRsultatDeLa);
		lblRsultatDeLa.setHorizontalAlignment(SwingConstants.CENTER);
		lblRsultatDeLa.setFont(new Font("Tahoma", Font.BOLD, 15));

		JScrollPane foundMoviesScrollPane = new JScrollPane();
		GridBagConstraints gbc_foundMoviesScrollPane = new GridBagConstraints();
		gbc_foundMoviesScrollPane.fill = GridBagConstraints.BOTH;
		gbc_foundMoviesScrollPane.gridx = 0;
		gbc_foundMoviesScrollPane.gridy = 1;
		topFoundMoviesListPanel.add(foundMoviesScrollPane, gbc_foundMoviesScrollPane);

		foundMoviesList = new JList<Film>();
		foundMoviesScrollPane.setViewportView(foundMoviesList);
		foundMoviesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		foundMoviesList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		foundMoviesList.setCellRenderer(new FilmListCellRenderer());

		JPanel bottomFoundMoviesListPanel = new JPanel();
		GridBagConstraints gbc_bottomFoundMoviesListPanel = new GridBagConstraints();
		gbc_bottomFoundMoviesListPanel.weighty = 1.0;
		gbc_bottomFoundMoviesListPanel.insets = new Insets(0, 0, 5, 0);
		gbc_bottomFoundMoviesListPanel.fill = GridBagConstraints.BOTH;
		gbc_bottomFoundMoviesListPanel.gridx = 0;
		gbc_bottomFoundMoviesListPanel.gridy = 1;
		foundMoviesListPanel.add(bottomFoundMoviesListPanel, gbc_bottomFoundMoviesListPanel);
		GridBagLayout gbl_bottomFoundMoviesListPanel = new GridBagLayout();
		gbl_bottomFoundMoviesListPanel.columnWidths = new int[]{0, 0};
		gbl_bottomFoundMoviesListPanel.rowHeights = new int[] {0, 0};
		gbl_bottomFoundMoviesListPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_bottomFoundMoviesListPanel.rowWeights = new double[]{0.0, 1.0};
		bottomFoundMoviesListPanel.setLayout(gbl_bottomFoundMoviesListPanel);

		JLabel lblRecommandations = new JLabel("Recommandations:");
		lblRecommandations.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecommandations.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblRecommandations = new GridBagConstraints();
		gbc_lblRecommandations.insets = new Insets(0, 0, 5, 0);
		gbc_lblRecommandations.gridx = 0;
		gbc_lblRecommandations.gridy = 0;
		bottomFoundMoviesListPanel.add(lblRecommandations, gbc_lblRecommandations);

		JScrollPane recommandationsScrollPane = new JScrollPane();
		GridBagConstraints gbc_recommandationsScrollPane = new GridBagConstraints();
		gbc_recommandationsScrollPane.fill = GridBagConstraints.BOTH;
		gbc_recommandationsScrollPane.gridx = 0;
		gbc_recommandationsScrollPane.gridy = 1;
		bottomFoundMoviesListPanel.add(recommandationsScrollPane, gbc_recommandationsScrollPane);

		recommandationsList = new JList<Film>();
		recommandationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		recommandationsList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		recommandationsList.setCellRenderer(new FilmListCellRenderer());
		recommandationsScrollPane.setViewportView(recommandationsList);

		JPanel foundMoviesListButtonsPanel = new JPanel();
		GridBagConstraints gbc_foundMoviesListButtonsPanel = new GridBagConstraints();
		gbc_foundMoviesListButtonsPanel.fill = GridBagConstraints.BOTH;
		gbc_foundMoviesListButtonsPanel.insets = new Insets(0, 0, 5, 0);
		gbc_foundMoviesListButtonsPanel.gridx = 0;
		gbc_foundMoviesListButtonsPanel.gridy = 2;
		foundMoviesListPanel.add(foundMoviesListButtonsPanel, gbc_foundMoviesListButtonsPanel);
		foundMoviesListButtonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		rentMovieButton = new JButton("Louer ce film");
		rentMovieButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		rentMovieButton.setEnabled(false);
		foundMoviesListButtonsPanel.add(rentMovieButton);

		contentPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				for(Component panel : criteriasPanel.getComponents())
				{
					setCriteriaPanelSize(criteriaScrollPane, (JPanel)panel);
				}
				criteriasPanel.revalidate();
			}
		});

		foundMoviesList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				Film foundMovie = foundMoviesList.getSelectedValue();
				if (foundMovie != null){
					recommandationsList.clearSelection();
					showRecommandedMovies(foundMovie);	
				}
				setSelectedMovie(foundMovie);
			}
		});

		recommandationsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				Film recommandedMovie = recommandationsList.getSelectedValue();
				if (recommandedMovie != null){
					foundMoviesList.clearSelection();
				}
				setSelectedMovie(recommandedMovie);
			}
		});

		JPanel initialPanel = new TitleCriteriaPanel();
		setCriteriaPanelSize(criteriaScrollPane, initialPanel);
		criteriasPanel.add(initialPanel);
	}

	private void showRecommandedMovies(Film film)
	{
		DefaultListModel<Film> model = new DefaultListModel<Film>();
		if (film != null)
		{
			List<Film> films = CourtierBdFilm.getRecommandedMovies(film);
			for (Film recommandedFilm: films)
			{
				model.addElement(recommandedFilm);
			}
		}
		recommandationsList.setModel(model);
	}

	private void setCriteriaPanelSize(JScrollPane scrollPane, JPanel criteriaPanel)
	{
		int width = scrollPane.getViewport().getWidth() - 2 * CRITERIA_PANELS_MARGIN;
		int height = CRITERIA_PANELS_HEIGHT;
		Dimension size = new Dimension(width, height);
		criteriaPanel.setPreferredSize(size);
	}

	public Component[] getSearchComponents()
	{
		return criteriasPanel.getComponents();

	}

	public Film getSelectedMovie()
	{
		return foundMoviesList.getSelectedValue();
	}

	public void addController (ActionListener controller)
	{
		searchButton.addActionListener(controller);
		rentMovieButton.addActionListener(controller);
	}

	@Override
	public void update(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	@Override
	public void update(List<Film> films) {
		if (films != null)
		{
			DefaultListModel<Film> model = new DefaultListModel<Film>();
			for (Film film: films)
			{
				model.addElement(film);
			}
			foundMoviesList.setModel(model);

			if (films.size() > 0)
			{
				foundMoviesList.setSelectedIndex(0);
			}
		}

		showCrewMemberDetails();
	}

	private void showCrewMemberDetails()
	{
		Film film = foundMoviesList.getSelectedValue();

		PersonnageDuCinema crewMember = null;
		if (film != null)
		{
			if (directorRadioButton.isSelected() && film.getRealisateurFilms().size() > 0)
			{
				// Hack because there should be only one director.
				RealisateurFilm[] rf = new RealisateurFilm[1];
				crewMember = (Realisateur)film.getRealisateurFilms().toArray(rf)[0].getRealisateur();
			}
			else if (actorsRadioButton.isSelected() && !actorsList.isSelectionEmpty())
			{
				crewMember = actorsList.getSelectedValue();
			}
		}

		if (crewMember == null)
		{
			clearCrewMemberDetails();
		}
		else
		{
			showCrewMemberDetails(crewMember);
		}
	}

	private void showCrewMemberDetails(PersonnageDuCinema crewMember)
	{
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");

		crewMemberNameTextField.setText(crewMember.getNomComplet());
		if (crewMember.getDateAnniversaire() == null)
		{
			crewMemberBirthdayTextField.setText(null);
		}
		else
		{
			crewMemberBirthdayTextField.setText(formatter.format(crewMember.getDateAnniversaire()));
		}
		crewMemberBirthLocationTextField.setText(crewMember.getLieuNaissance());
		crewMemberBiographyTextArea.setText(crewMember.getBiographie());
	}

	private void clearCrewMemberDetails()
	{
		crewMemberNameTextField.setText(null);
		crewMemberBirthdayTextField.setText(null);
		crewMemberBirthLocationTextField.setText(null);
		crewMemberBiographyTextArea.setText(null);
	}

	@Override
	public void update(Film contentToUpdate) {
		// TODO Auto-generated method stub

	}

	private void setSelectedMovie(Film film)
	{
		if (film == null)
		{
			rentMovieButton.setEnabled(false);
			movieTitleTextField.setText(null);
			releaseYearTextField.setText(null);
			productionCountryTextField.setText(null);
			originalLanguageTextField.setText(null);
			movieLengthTextField.setText(null);
			movieGenreTextField.setText(null);
			movieScenaristTextField.setText(null);
			directorTextField.setText(null);
			movieSummaryTextArea.setText(null);
			actorsList.setModel(new DefaultListModel<Acteur>());
		}
		else
		{
			movieTitleTextField.setText(film.getTitre());
			releaseYearTextField.setText(String.valueOf(film.getAnneeSortie()));
			String pays="";

			for(PaysFilm p : film.getPaysFilms())
				pays += p.getPays().getNom() +",";
			productionCountryTextField.setText(pays);

			originalLanguageTextField.setText(film.getLangueOriginale());
			movieLengthTextField.setText(String.valueOf(film.getDuree()));

			String genre = "";
			String separateurGenre = "";
			for(FilmGenre g : film.getFilmGenres())
			{
				genre += separateurGenre + g.getGenre().getNom();
				separateurGenre = ", ";
			}
			movieGenreTextField.setText(genre);

			// Hack because there should be only one director.
			RealisateurFilm[] rf = new RealisateurFilm[1];
			Realisateur realisateur = (Realisateur)film.getRealisateurFilms().toArray(rf)[0].getRealisateur();

			String scenaristes = "";
			String separateurScenaristes = "";
			for(Scenariste s : film.getScenaristes())
			{
				scenaristes += separateurScenaristes + s.getNomScenariste();
				separateurScenaristes = ", ";
			}
			movieScenaristTextField.setText(scenaristes);

			if (realisateur == null)
			{
				directorTextField.setText(null);										
			}
			else
			{
				directorTextField.setText(realisateur.getNomComplet());										
			}
			movieSummaryTextArea.setText(film.getResume());
			DefaultListModel<Acteur> model = new DefaultListModel<Acteur>();
			for (ActeurFilm acteurFilm: film.getActeurFilms())
			{
				Acteur acteur = acteurFilm.getActeur();
				model.addElement(acteur);
			}
			actorsList.setModel(model);

			rentMovieButton.setEnabled(true);
		}

		directorRadioButton.setSelected(true);
		showCrewMemberDetails();
	}
}
