package UI;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import Model.Acteur;
import Model.Film;

public class ActorListCellRenderer  extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(JList<?> list,
                                 Object value,
                                 int index,
                                 boolean isSelected,
                                 boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Acteur) {
        	Acteur acteur = (Acteur)value;
            setText(acteur.getNomComplet());
        }
        return this;
    }
}
