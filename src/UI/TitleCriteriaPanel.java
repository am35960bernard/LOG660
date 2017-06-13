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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TitleCriteriaPanel extends JPanel implements CriteriaPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public TitleCriteriaPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblTitreDuFilm = new JLabel("Titre du film:");
		GridBagConstraints gbc_lblTitreDuFilm = new GridBagConstraints();
		gbc_lblTitreDuFilm.anchor = GridBagConstraints.EAST;
		gbc_lblTitreDuFilm.insets = new Insets(0, 0, 0, 5);
		gbc_lblTitreDuFilm.gridx = 0;
		gbc_lblTitreDuFilm.gridy = 0;
		add(lblTitreDuFilm, gbc_lblTitreDuFilm);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Container parent = TitleCriteriaPanel.this.getParent();
				parent.remove(TitleCriteriaPanel.this);
				parent.revalidate();
			}
		});
		GridBagConstraints gbc_btnSupprimer = new GridBagConstraints();
		gbc_btnSupprimer.gridx = 2;
		gbc_btnSupprimer.gridy = 0;
		add(btnSupprimer, gbc_btnSupprimer);

	}

}
