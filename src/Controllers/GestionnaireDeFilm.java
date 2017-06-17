package Controllers;

import java.util.List;
import java.util.Observable;

import Services.CourtierBdFilm;
import Services.Filters.Criteria;
import Services.Filters.FilterCriteria;
import Services.Filters.Parameters;

public class GestionnaireDeFilm extends Observable {
	
	public void chercher(List<String> txtTitreFilm, List<String> txtActeurFilm, List<String> txtPaysFilm)
	{
		FilterCriteria myFilters = new FilterCriteria();
		if(!txtTitreFilm.isEmpty())
			myFilters.addCriteria(new Parameters(StaticVariables.TITRE_FILM, txtTitreFilm));
		if(!txtActeurFilm.isEmpty())
			myFilters.addCriteria(new Parameters(StaticVariables.ACTEUR_NOM, txtActeurFilm));
		if(!txtPaysFilm.isEmpty())
			myFilters.addCriteria(new Parameters(StaticVariables.NOM_PAYS, txtPaysFilm));
		
		CourtierBdFilm courtierFilm = new CourtierBdFilm();
		courtierFilm.chercherFilm(myFilters);
	}

}
