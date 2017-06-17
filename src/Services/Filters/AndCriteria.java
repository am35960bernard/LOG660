package Services.Filters;

import java.util.List;

import Model.Film;

public class AndCriteria implements Criteria {

	private Criteria criteria;
	private Criteria otherCriteria;
	public AndCriteria(Criteria criteria, Criteria otherCriteria)
	{
		this.criteria = criteria;
		this.otherCriteria = otherCriteria;
	}
	
	
	@Override
	public List<Film> meetCriteria(List<Film> films) {
        List<Film> criteriaFilm1 = criteria.meetCriteria(films);
		return otherCriteria.meetCriteria(criteriaFilm1);
	}


	@Override
	public void addCriteria(String filter) {
		// TODO Auto-generated method stub
		
	}

}
