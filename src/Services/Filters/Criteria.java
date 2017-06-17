package Services.Filters;

import java.util.List;

import Model.Film;

public interface Criteria {
	
	public List<Film> meetCriteria(List<Film> films);
	public void addCriteria(String filter);

}
