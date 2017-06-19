package UI;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import Model.Film;

public class FilmListCellRenderer  extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(JList<?> list,
                                 Object value,
                                 int index,
                                 boolean isSelected,
                                 boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Film) {
        	Film film = (Film)value;
            setText(film.getTitre() + " (" + film.getAnneeSortie() + ")");
        }
        return this;
    }
}
