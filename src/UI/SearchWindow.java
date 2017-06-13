package UI;

import java.awt.BorderLayout;
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

public class SearchWindow extends JFrame {

	private JPanel contentPane;

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
		setBounds(100, 100, 603, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{568, 0};
		gbl_contentPane.rowHeights = new int[]{35, 260, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel addCriteriaPanel = new JPanel();
		GridBagConstraints gbc_addCriteriaPanel = new GridBagConstraints();
		gbc_addCriteriaPanel.anchor = GridBagConstraints.NORTH;
		gbc_addCriteriaPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_addCriteriaPanel.insets = new Insets(0, 0, 5, 5);
		gbc_addCriteriaPanel.gridx = 0;
		gbc_addCriteriaPanel.gridy = 0;
		contentPane.add(addCriteriaPanel, gbc_addCriteriaPanel);
		addCriteriaPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JLabel criteriaLabel = new JLabel("Crit\u00E8re:");
		addCriteriaPanel.add(criteriaLabel);
		
		JComboBox criteriaComboBox = new JComboBox();
		criteriaComboBox.setModel(new DefaultComboBoxModel(new String[] {"Titre", "Intervale", "Pays de production", "Langue originale", "Genre", "R\u00E9alisateur", "Acteur"}));
		addCriteriaPanel.add(criteriaComboBox);
		
		JPanel criteriasPanel = new JPanel();
		GridBagConstraints gbc_criteriasPanel = new GridBagConstraints();
		gbc_criteriasPanel.anchor = GridBagConstraints.NORTH;
		gbc_criteriasPanel.insets = new Insets(0, 0, 0, 5);
		gbc_criteriasPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_criteriasPanel.gridx = 0;
		gbc_criteriasPanel.gridy = 1;
		contentPane.add(criteriasPanel, gbc_criteriasPanel);
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
				
				Dimension size = new Dimension(criteriasPanel.getWidth(), 35);
				panel.setPreferredSize(size);
				panel.setMinimumSize(size);
				panel.setAlignmentY(TOP_ALIGNMENT);
				
				criteriasPanel.add(panel);
				criteriasPanel.revalidate();
			}
		});
		addCriteriaPanel.add(addCriteriaButton);
	}

}
