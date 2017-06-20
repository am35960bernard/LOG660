package Controllers;

import java.util.List;

import Model.Film;

public interface Observer {

	public void update(String contentToUpdate);
	public void update(List<Film> contentToUpdate);
	public void update(Film contentToUpdate);
}
