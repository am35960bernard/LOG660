package Services.Filters;

import java.util.ArrayList;
import java.util.List;

import Model.Film;

public class FilterCriteria  {

	private static List<Parameters> myList;
	public FilterCriteria()
	{
		myList = new ArrayList<Parameters>();
	}
	

	public void addCriteria(Parameters p) {
		// TODO Auto-generated method stub
		myList.add(p);
		
	}
	
	public List<Parameters> getCriterias()
	{
		return myList;
	}

}
