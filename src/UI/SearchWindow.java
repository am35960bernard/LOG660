package UI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JList;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.ButtonGroup;
import java.awt.Font;
import javax.swing.SwingConstants;

public class SearchWindow extends JFrame {

	private int CRITERIA_PANELS_HEIGHT = 30;
	private int CRITERIA_PANELS_MARGIN = 5;
	
	private JPanel contentPane;
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
	private JTextField directorTextField;
	private final ButtonGroup crewButtonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchWindow frame = new SearchWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SearchWindow() {
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
		
		JPanel addCriteriaPanel = new JPanel();
		GridBagConstraints gbc_addCriteriaPanel = new GridBagConstraints();
		gbc_addCriteriaPanel.anchor = GridBagConstraints.NORTH;
		gbc_addCriteriaPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_addCriteriaPanel.insets = new Insets(0, 0, 5, 0);
		gbc_addCriteriaPanel.gridx = 0;
		gbc_addCriteriaPanel.gridy = 0;
		contentPane.add(addCriteriaPanel, gbc_addCriteriaPanel);
		addCriteriaPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JLabel criteriaLabel = new JLabel("Crit\u00E8res de recherche:");
		criteriaLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		addCriteriaPanel.add(criteriaLabel);
		
		JComboBox criteriaComboBox = new JComboBox();
		criteriaComboBox.setModel(new DefaultComboBoxModel(new String[] {"Titre", "Intervale", "Pays de production", "Langue originale", "Genre", "R\u00E9alisateur", "Acteur"}));
		addCriteriaPanel.add(criteriaComboBox);
		
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
		
		JPanel criteriasPanel = new JPanel();
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
		
		JButton searchButton = new JButton("Rechercher");
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
		gbl_movieDetailsPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_movieDetailsPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_movieDetailsPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		movieDetailsPanel.setLayout(gbl_movieDetailsPanel);
		
		JLabel lblDtailsDuFilm = new JLabel("D\u00E9tails du film:");
		lblDtailsDuFilm.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblDtailsDuFilm = new GridBagConstraints();
		gbc_lblDtailsDuFilm.gridwidth = 2;
		gbc_lblDtailsDuFilm.insets = new Insets(0, 0, 5, 5);
		gbc_lblDtailsDuFilm.gridx = 0;
		gbc_lblDtailsDuFilm.gridy = 0;
		movieDetailsPanel.add(lblDtailsDuFilm, gbc_lblDtailsDuFilm);
		
		JLabel movieTitleLabel = new JLabel("Titre du film:");
		GridBagConstraints gbc_movieTitleLabel = new GridBagConstraints();
		gbc_movieTitleLabel.anchor = GridBagConstraints.EAST;
		gbc_movieTitleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_movieTitleLabel.gridx = 0;
		gbc_movieTitleLabel.gridy = 1;
		movieDetailsPanel.add(movieTitleLabel, gbc_movieTitleLabel);
		
		movieTitleTextField = new JTextField();
		movieTitleTextField.setPreferredSize(new Dimension(6, 25));
		movieTitleTextField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		movieTitleTextField.setColumns(10);
		GridBagConstraints gbc_movieTitleTextField = new GridBagConstraints();
		gbc_movieTitleTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_movieTitleTextField.insets = new Insets(0, 0, 5, 0);
		gbc_movieTitleTextField.gridx = 1;
		gbc_movieTitleTextField.gridy = 1;
		movieDetailsPanel.add(movieTitleTextField, gbc_movieTitleTextField);
		
		JLabel releaseYearLabel = new JLabel("Ann\u00E9e de sortie:");
		GridBagConstraints gbc_releaseYearLabel = new GridBagConstraints();
		gbc_releaseYearLabel.anchor = GridBagConstraints.EAST;
		gbc_releaseYearLabel.insets = new Insets(0, 0, 5, 5);
		gbc_releaseYearLabel.gridx = 0;
		gbc_releaseYearLabel.gridy = 2;
		movieDetailsPanel.add(releaseYearLabel, gbc_releaseYearLabel);
		
		releaseYearTextField = new JTextField();
		releaseYearTextField.setPreferredSize(new Dimension(6, 25));
		releaseYearTextField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		releaseYearTextField.setColumns(10);
		GridBagConstraints gbc_releaseYearTextField = new GridBagConstraints();
		gbc_releaseYearTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_releaseYearTextField.insets = new Insets(0, 0, 5, 0);
		gbc_releaseYearTextField.gridx = 1;
		gbc_releaseYearTextField.gridy = 2;
		movieDetailsPanel.add(releaseYearTextField, gbc_releaseYearTextField);
		
		JLabel productionCountryLabel = new JLabel("Pays de production:");
		GridBagConstraints gbc_productionCountryLabel = new GridBagConstraints();
		gbc_productionCountryLabel.anchor = GridBagConstraints.EAST;
		gbc_productionCountryLabel.insets = new Insets(0, 0, 5, 5);
		gbc_productionCountryLabel.gridx = 0;
		gbc_productionCountryLabel.gridy = 3;
		movieDetailsPanel.add(productionCountryLabel, gbc_productionCountryLabel);
		
		productionCountryTextField = new JTextField();
		productionCountryTextField.setPreferredSize(new Dimension(6, 25));
		productionCountryTextField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		productionCountryTextField.setColumns(10);
		GridBagConstraints gbc_productionCountryTextField = new GridBagConstraints();
		gbc_productionCountryTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_productionCountryTextField.insets = new Insets(0, 0, 5, 0);
		gbc_productionCountryTextField.gridx = 1;
		gbc_productionCountryTextField.gridy = 3;
		movieDetailsPanel.add(productionCountryTextField, gbc_productionCountryTextField);
		
		JLabel originalLanguageLabel = new JLabel("Langue originale:");
		GridBagConstraints gbc_originalLanguageLabel = new GridBagConstraints();
		gbc_originalLanguageLabel.anchor = GridBagConstraints.EAST;
		gbc_originalLanguageLabel.insets = new Insets(0, 0, 5, 5);
		gbc_originalLanguageLabel.gridx = 0;
		gbc_originalLanguageLabel.gridy = 4;
		movieDetailsPanel.add(originalLanguageLabel, gbc_originalLanguageLabel);
		
		originalLanguageTextField = new JTextField();
		originalLanguageTextField.setPreferredSize(new Dimension(6, 25));
		originalLanguageTextField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		originalLanguageTextField.setColumns(10);
		GridBagConstraints gbc_originalLanguageTextField = new GridBagConstraints();
		gbc_originalLanguageTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_originalLanguageTextField.insets = new Insets(0, 0, 5, 0);
		gbc_originalLanguageTextField.gridx = 1;
		gbc_originalLanguageTextField.gridy = 4;
		movieDetailsPanel.add(originalLanguageTextField, gbc_originalLanguageTextField);
		
		JLabel movieLengthLabel = new JLabel("Dur\u00E9e:");
		GridBagConstraints gbc_movieLengthLabel = new GridBagConstraints();
		gbc_movieLengthLabel.anchor = GridBagConstraints.EAST;
		gbc_movieLengthLabel.insets = new Insets(0, 0, 5, 5);
		gbc_movieLengthLabel.gridx = 0;
		gbc_movieLengthLabel.gridy = 5;
		movieDetailsPanel.add(movieLengthLabel, gbc_movieLengthLabel);
		
		movieLengthTextField = new JTextField();
		movieLengthTextField.setPreferredSize(new Dimension(6, 25));
		movieLengthTextField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		movieLengthTextField.setColumns(10);
		GridBagConstraints gbc_movieLengthTextField = new GridBagConstraints();
		gbc_movieLengthTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_movieLengthTextField.insets = new Insets(0, 0, 5, 0);
		gbc_movieLengthTextField.gridx = 1;
		gbc_movieLengthTextField.gridy = 5;
		movieDetailsPanel.add(movieLengthTextField, gbc_movieLengthTextField);
		
		JLabel movieGenreLabel = new JLabel("Genres:");
		GridBagConstraints gbc_movieGenreLabel = new GridBagConstraints();
		gbc_movieGenreLabel.anchor = GridBagConstraints.EAST;
		gbc_movieGenreLabel.insets = new Insets(0, 0, 5, 5);
		gbc_movieGenreLabel.gridx = 0;
		gbc_movieGenreLabel.gridy = 6;
		movieDetailsPanel.add(movieGenreLabel, gbc_movieGenreLabel);
		
		movieGenreTextField = new JTextField();
		movieGenreTextField.setPreferredSize(new Dimension(6, 25));
		movieGenreTextField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		movieGenreTextField.setColumns(10);
		GridBagConstraints gbc_movieGenreTextField = new GridBagConstraints();
		gbc_movieGenreTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_movieGenreTextField.insets = new Insets(0, 0, 5, 0);
		gbc_movieGenreTextField.gridx = 1;
		gbc_movieGenreTextField.gridy = 6;
		movieDetailsPanel.add(movieGenreTextField, gbc_movieGenreTextField);
		
		JLabel movieScenaristLabel = new JLabel("Sc\u00E9naristes:");
		GridBagConstraints gbc_movieScenaristLabel = new GridBagConstraints();
		gbc_movieScenaristLabel.anchor = GridBagConstraints.EAST;
		gbc_movieScenaristLabel.insets = new Insets(0, 0, 5, 5);
		gbc_movieScenaristLabel.gridx = 0;
		gbc_movieScenaristLabel.gridy = 7;
		movieDetailsPanel.add(movieScenaristLabel, gbc_movieScenaristLabel);
		
		movieScenaristTextField = new JTextField();
		movieScenaristTextField.setPreferredSize(new Dimension(6, 25));
		movieScenaristTextField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		movieScenaristTextField.setColumns(10);
		GridBagConstraints gbc_movieScenaristTextField = new GridBagConstraints();
		gbc_movieScenaristTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_movieScenaristTextField.insets = new Insets(0, 0, 5, 0);
		gbc_movieScenaristTextField.gridx = 1;
		gbc_movieScenaristTextField.gridy = 7;
		movieDetailsPanel.add(movieScenaristTextField, gbc_movieScenaristTextField);
		
		JLabel movieSummaryLabel = new JLabel("R\u00E9sum\u00E9 du sc\u00E9nario:");
		GridBagConstraints gbc_movieSummaryLabel = new GridBagConstraints();
		gbc_movieSummaryLabel.anchor = GridBagConstraints.NORTHEAST;
		gbc_movieSummaryLabel.insets = new Insets(0, 0, 0, 5);
		gbc_movieSummaryLabel.gridx = 0;
		gbc_movieSummaryLabel.gridy = 8;
		movieDetailsPanel.add(movieSummaryLabel, gbc_movieSummaryLabel);
		
		JTextArea movieSummaryTextArea = new JTextArea();
		movieSummaryTextArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_movieSummaryTextArea = new GridBagConstraints();
		gbc_movieSummaryTextArea.fill = GridBagConstraints.BOTH;
		gbc_movieSummaryTextArea.gridx = 1;
		gbc_movieSummaryTextArea.gridy = 8;
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
		
		JLabel crewMemberTitleLabel = new JLabel("D\u00E9tails sur cet \u00E9quipier:");
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
		
		JTextArea crewMemberBiographyTextArea = new JTextArea();
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
		
		JLabel crewTitleLabel = new JLabel("\u00C9quipe de tournage:");
		crewTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_crewTitleLabel = new GridBagConstraints();
		gbc_crewTitleLabel.gridwidth = 2;
		gbc_crewTitleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_crewTitleLabel.gridx = 0;
		gbc_crewTitleLabel.gridy = 0;
		directorAndActorsPanel.add(crewTitleLabel, gbc_crewTitleLabel);
		
		JRadioButton directorRadioButton = new JRadioButton("R\u00E9alisateur:");
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
		
		JRadioButton actorsRadioButton = new JRadioButton("Acteurs:");
		crewButtonGroup.add(actorsRadioButton);
		GridBagConstraints gbc_actorsRadioButton = new GridBagConstraints();
		gbc_actorsRadioButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_actorsRadioButton.insets = new Insets(0, 0, 0, 5);
		gbc_actorsRadioButton.gridx = 0;
		gbc_actorsRadioButton.gridy = 2;
		directorAndActorsPanel.add(actorsRadioButton, gbc_actorsRadioButton);
		
		JList actorsList = new JList();
		actorsList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_actorsList = new GridBagConstraints();
		gbc_actorsList.fill = GridBagConstraints.BOTH;
		gbc_actorsList.gridx = 1;
		gbc_actorsList.gridy = 2;
		directorAndActorsPanel.add(actorsList, gbc_actorsList);
		
		JPanel foundMoviesListPanel = new JPanel();
		foundMoviesListPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		foundMoviesSplitPane.setLeftComponent(foundMoviesListPanel);
		GridBagLayout gbl_foundMoviesListPanel = new GridBagLayout();
		gbl_foundMoviesListPanel.columnWidths = new int[]{0, 0};
		gbl_foundMoviesListPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_foundMoviesListPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_foundMoviesListPanel.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		foundMoviesListPanel.setLayout(gbl_foundMoviesListPanel);
		
		JLabel lblRsultatDeLa = new JLabel("R\u00E9sultat de la recherche:");
		lblRsultatDeLa.setHorizontalAlignment(SwingConstants.CENTER);
		lblRsultatDeLa.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblRsultatDeLa = new GridBagConstraints();
		gbc_lblRsultatDeLa.insets = new Insets(0, 0, 5, 0);
		gbc_lblRsultatDeLa.gridx = 0;
		gbc_lblRsultatDeLa.gridy = 0;
		foundMoviesListPanel.add(lblRsultatDeLa, gbc_lblRsultatDeLa);
		
		JList foundMoviesList = new JList();
		foundMoviesList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_foundMoviesList = new GridBagConstraints();
		gbc_foundMoviesList.insets = new Insets(0, 0, 5, 0);
		gbc_foundMoviesList.fill = GridBagConstraints.BOTH;
		gbc_foundMoviesList.gridx = 0;
		gbc_foundMoviesList.gridy = 1;
		foundMoviesListPanel.add(foundMoviesList, gbc_foundMoviesList);
		
		JPanel foundMoviesListButtonsPanel = new JPanel();
		GridBagConstraints gbc_foundMoviesListButtonsPanel = new GridBagConstraints();
		gbc_foundMoviesListButtonsPanel.fill = GridBagConstraints.BOTH;
		gbc_foundMoviesListButtonsPanel.gridx = 0;
		gbc_foundMoviesListButtonsPanel.gridy = 2;
		foundMoviesListPanel.add(foundMoviesListButtonsPanel, gbc_foundMoviesListButtonsPanel);
		foundMoviesListButtonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton rentMovieButton = new JButton("Louer ce film");
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
		
		JPanel initialPanel = new TitleCriteriaPanel();
		setCriteriaPanelSize(criteriaScrollPane, initialPanel);
		criteriasPanel.add(initialPanel);
	}
	
	private void setCriteriaPanelSize(JScrollPane scrollPane, JPanel criteriaPanel)
	{
		int width = scrollPane.getViewport().getWidth() - 2 * CRITERIA_PANELS_MARGIN;
		int height = CRITERIA_PANELS_HEIGHT;
		Dimension size = new Dimension(width, height);
		criteriaPanel.setPreferredSize(size);
	}
}
