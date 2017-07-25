package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Window.Type;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AnalysisWindow extends JFrame {

	private JPanel contentPane;
	private JTextField nbLocationsTextField;
	
	private class ComboBoxItem
	{
		private final String value;
		private final String description;
		
		public ComboBoxItem(String value, String description)
		{
			this.value = value;
			this.description = description;
		}
		
		public String getValue() {
			return value;
		}
		
		public String getDescriptione() {
			return description;
		}
		
		@Override
	    public String toString() {
	        return description;
	    }
	}

	/**
	 * Create the frame.
	 */
	public AnalysisWindow() {
		setResizable(false);
		setTitle("Webflix - Analyse");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 254);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel criteriaPanel = new JPanel();
		contentPane.add(criteriaPanel);
		GridBagLayout gbl_criteriaPanel = new GridBagLayout();
		gbl_criteriaPanel.columnWidths = new int[]{0, 0, 0};
		gbl_criteriaPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_criteriaPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_criteriaPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		criteriaPanel.setLayout(gbl_criteriaPanel);
		
		JLabel lblAgeGroupLabel = new JLabel("Groupe d'\u00E2ge:");
		lblAgeGroupLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblAgeGroupLabel = new GridBagConstraints();
		gbc_lblAgeGroupLabel.anchor = GridBagConstraints.EAST;
		gbc_lblAgeGroupLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblAgeGroupLabel.gridx = 0;
		gbc_lblAgeGroupLabel.gridy = 0;
		criteriaPanel.add(lblAgeGroupLabel, gbc_lblAgeGroupLabel);
		
		JComboBox<ComboBoxItem> ageGroupComboBox = new JComboBox<ComboBoxItem>();
		ageGroupComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_ageGroupComboBox = new GridBagConstraints();
		gbc_ageGroupComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_ageGroupComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_ageGroupComboBox.gridx = 1;
		gbc_ageGroupComboBox.gridy = 0;
		criteriaPanel.add(ageGroupComboBox, gbc_ageGroupComboBox);
		
		JLabel provinceLabel = new JLabel("Province:");
		provinceLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_provinceLabel = new GridBagConstraints();
		gbc_provinceLabel.anchor = GridBagConstraints.EAST;
		gbc_provinceLabel.insets = new Insets(0, 0, 5, 5);
		gbc_provinceLabel.gridx = 0;
		gbc_provinceLabel.gridy = 1;
		criteriaPanel.add(provinceLabel, gbc_provinceLabel);
		
		JComboBox provinceComboBox = new JComboBox();
		provinceComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_provinceComboBox = new GridBagConstraints();
		gbc_provinceComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_provinceComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_provinceComboBox.gridx = 1;
		gbc_provinceComboBox.gridy = 1;
		criteriaPanel.add(provinceComboBox, gbc_provinceComboBox);
		
		JLabel weekDayLabel = new JLabel("Jour de la semaine:");
		weekDayLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_weekDayLabel = new GridBagConstraints();
		gbc_weekDayLabel.anchor = GridBagConstraints.EAST;
		gbc_weekDayLabel.insets = new Insets(0, 0, 5, 5);
		gbc_weekDayLabel.gridx = 0;
		gbc_weekDayLabel.gridy = 2;
		criteriaPanel.add(weekDayLabel, gbc_weekDayLabel);
		
		JComboBox weekDayComboBox = new JComboBox();
		weekDayComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_weekDayComboBox = new GridBagConstraints();
		gbc_weekDayComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_weekDayComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_weekDayComboBox.gridx = 1;
		gbc_weekDayComboBox.gridy = 2;
		criteriaPanel.add(weekDayComboBox, gbc_weekDayComboBox);
		
		JLabel monthLabel = new JLabel("Mois de l'ann\u00E9e:");
		monthLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_monthLabel = new GridBagConstraints();
		gbc_monthLabel.anchor = GridBagConstraints.EAST;
		gbc_monthLabel.insets = new Insets(0, 0, 5, 5);
		gbc_monthLabel.gridx = 0;
		gbc_monthLabel.gridy = 3;
		criteriaPanel.add(monthLabel, gbc_monthLabel);
		
		JComboBox monthComboBox = new JComboBox();
		monthComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_monthComboBox = new GridBagConstraints();
		gbc_monthComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_monthComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_monthComboBox.gridx = 1;
		gbc_monthComboBox.gridy = 3;
		criteriaPanel.add(monthComboBox, gbc_monthComboBox);
		
		JLabel nbLocationsLabel = new JLabel("Nombre de locations:");
		nbLocationsLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_nbLocationsLabel = new GridBagConstraints();
		gbc_nbLocationsLabel.anchor = GridBagConstraints.EAST;
		gbc_nbLocationsLabel.insets = new Insets(0, 0, 0, 5);
		gbc_nbLocationsLabel.gridx = 0;
		gbc_nbLocationsLabel.gridy = 4;
		criteriaPanel.add(nbLocationsLabel, gbc_nbLocationsLabel);
		
		nbLocationsTextField = new JTextField();
		nbLocationsTextField.setEditable(false);
		nbLocationsTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_nbLocationsTextField = new GridBagConstraints();
		gbc_nbLocationsTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nbLocationsTextField.gridx = 1;
		gbc_nbLocationsTextField.gridy = 4;
		criteriaPanel.add(nbLocationsTextField, gbc_nbLocationsTextField);
		nbLocationsTextField.setColumns(10);
		
		JPanel searchPanel = new JPanel();
		FlowLayout fl_searchPanel = (FlowLayout) searchPanel.getLayout();
		fl_searchPanel.setAlignment(FlowLayout.RIGHT);
		contentPane.add(searchPanel, BorderLayout.SOUTH);
		
		JButton searchButton = new JButton("Recherche");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Interrogate DB to get number of locations...
			}
		});
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchPanel.add(searchButton);

		ageGroupComboBox.addItem(new ComboBoxItem(null, "(tous)"));
		for (int i = 0; i < 20; i++)
		{
			String value = Integer.toString(i);
			String ageMin = Integer.toString(i * 5);
			String ageMax = Integer.toString((i + 1) * 5);
			String description = "de " + ageMin + " à " + ageMax + " ans";
			ageGroupComboBox.addItem(new ComboBoxItem(value, description));
		}

		provinceComboBox.addItem(new ComboBoxItem(null, "(tous)"));
		provinceComboBox.addItem(new ComboBoxItem("AB", "Alberta"));
		provinceComboBox.addItem(new ComboBoxItem("BC", "Colombie-Britanique"));
		provinceComboBox.addItem(new ComboBoxItem("IPE", "Île-du-Prince-Édouard"));
		provinceComboBox.addItem(new ComboBoxItem("NB", "Nouveau-Brunswick"));
		provinceComboBox.addItem(new ComboBoxItem("NS", "Nouvelle-Écosse"));
		provinceComboBox.addItem(new ComboBoxItem("ON", "Ontario"));
		provinceComboBox.addItem(new ComboBoxItem("QC", "Québec"));
		provinceComboBox.addItem(new ComboBoxItem("SK", "Saskatchewan"));
		provinceComboBox.addItem(new ComboBoxItem("NL", "Terre-Neuve-et-Labrador "));
		provinceComboBox.addItem(new ComboBoxItem("NU", "Nunavut"));
		provinceComboBox.addItem(new ComboBoxItem("NT", "Territoires du Nord-Ouest"));
		provinceComboBox.addItem(new ComboBoxItem("YT", "Yukon"));

		weekDayComboBox.addItem(new ComboBoxItem(null, "(tous)"));
		weekDayComboBox.addItem(new ComboBoxItem("Monday", "lundi"));
		weekDayComboBox.addItem(new ComboBoxItem("Tuesday", "mardi"));
		weekDayComboBox.addItem(new ComboBoxItem("Wednesday", "mercredi"));
		weekDayComboBox.addItem(new ComboBoxItem("Thursday", "jeudi"));
		weekDayComboBox.addItem(new ComboBoxItem("Friday", "vendredi"));
		weekDayComboBox.addItem(new ComboBoxItem("Saturday", "samedi"));
		weekDayComboBox.addItem(new ComboBoxItem("Sunday", "dimanche"));

		monthComboBox.addItem(new ComboBoxItem(null, "(tous)"));
		monthComboBox.addItem(new ComboBoxItem("January", "janvier"));
		monthComboBox.addItem(new ComboBoxItem("February", "février"));
		monthComboBox.addItem(new ComboBoxItem("March", "mars"));
		monthComboBox.addItem(new ComboBoxItem("April", "avril"));
		monthComboBox.addItem(new ComboBoxItem("May", "mai"));
		monthComboBox.addItem(new ComboBoxItem("June", "juin"));
		monthComboBox.addItem(new ComboBoxItem("July", "juillet"));
		monthComboBox.addItem(new ComboBoxItem("August", "août"));
		monthComboBox.addItem(new ComboBoxItem("September", "septembre"));
		monthComboBox.addItem(new ComboBoxItem("October", "octobre"));
		monthComboBox.addItem(new ComboBoxItem("November", "novembre"));
		monthComboBox.addItem(new ComboBoxItem("December", "décembre"));
	}
}
