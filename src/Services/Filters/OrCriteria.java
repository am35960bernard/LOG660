package Services.Filters;

import java.util.List;

import Model.Film;

public class OrCriteria implements Criteria{

	private Criteria criteria;
	private Criteria otherCriteria;
	
	public OrCriteria(Criteria criteria, Criteria otherCriteria)
	{
		this.criteria = criteria;
		this.otherCriteria = otherCriteria;
	}
	
	@Override
	public List<Film> meetCriteria(List<Film> films) {
        List<Film> criteriaFilm1 = criteria.meetCriteria(films);
        List<Film> criteriaFilm2 = otherCriteria.meetCriteria(criteriaFilm1);
        
        for(Film film : criteriaFilm2)
        {
        	if(!criteriaFilm1.contains(film))
        	{
        		criteriaFilm1.add(film);
        	}
        }
		return criteriaFilm1;
	}

	@Override
	public void addCriteria(String filter) {
		// TODO Auto-generated method stub
		
	}

}
