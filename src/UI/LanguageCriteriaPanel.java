package UI;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LanguageCriteriaPanel extends JPanel implements CriteriaPanel {

	/**
	 * Create the panel.
	 */
	public LanguageCriteriaPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblTitreDuFilm = new JLabel("Langue originale:");
		GridBagConstraints gbc_lblTitreDuFilm = new GridBagConstraints();
		gbc_lblTitreDuFilm.anchor = GridBagConstraints.EAST;
		gbc_lblTitreDuFilm.insets = new Insets(0, 0, 0, 5);
		gbc_lblTitreDuFilm.gridx = 0;
		gbc_lblTitreDuFilm.gridy = 0;
		add(lblTitreDuFilm, gbc_lblTitreDuFilm);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		add(comboBox, gbc_comboBox);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Container parent = LanguageCriteriaPanel.this.getParent();
				parent.remove(LanguageCriteriaPanel.this);
				parent.revalidate();
			}
		});
		GridBagConstraints gbc_btnSupprimer = new GridBagConstraints();
		gbc_btnSupprimer.gridx = 2;
		gbc_btnSupprimer.gridy = 0;
		add(btnSupprimer, gbc_btnSupprimer);

	}

}
