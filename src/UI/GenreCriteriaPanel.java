package UI;

import javax.swing.JPanel;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JTextField;

import java.awt.GridBagConstraints;

import javax.swing.JLabel;

import java.awt.Insets;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import Model.Genre;
import Services.CourtierBdFilm;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class GenreCriteriaPanel extends JPanel implements CriteriaPanel {

	private JComboBox comboBox;
	private final static List<Genre> genreList = CourtierBdFilm.getGenres();
	/**
	 * Create the panel.
	 */
	public GenreCriteriaPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblTitreDuFilm = new JLabel("Genre:");
		GridBagConstraints gbc_lblTitreDuFilm = new GridBagConstraints();
		gbc_lblTitreDuFilm.anchor = GridBagConstraints.EAST;
		gbc_lblTitreDuFilm.insets = new Insets(0, 0, 0, 5);
		gbc_lblTitreDuFilm.gridx = 0;
		gbc_lblTitreDuFilm.gridy = 0;
		add(lblTitreDuFilm, gbc_lblTitreDuFilm);
		
	   comboBox = new JComboBox();
	   for(Object element : genreList)
	   {
			  Object[] genre = (Object[]) element;
			  comboBox.addItem(genre[1]);

	   }
	   
	   
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		add(comboBox, gbc_comboBox);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Container parent = GenreCriteriaPanel.this.getParent();
				parent.remove(GenreCriteriaPanel.this);
				parent.revalidate();
			}
		});
		GridBagConstraints gbc_btnSupprimer = new GridBagConstraints();
		gbc_btnSupprimer.gridx = 2;
		gbc_btnSupprimer.gridy = 0;
		add(btnSupprimer, gbc_btnSupprimer);

	}
	
	public String getGenre()
	{
		for(Object element : genreList)
		   {
				  Object[] genre = (Object[]) element;
				  if(genre[1].equals(comboBox.getSelectedItem().toString()))
				     return String.valueOf(genre[1]);

		   }
		return "";
	}



}
