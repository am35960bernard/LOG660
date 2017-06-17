package Services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Controllers.StaticVariables;
import Model.Film;
import Services.Filters.FilterCriteria;
import Services.Filters.Parameters;

public class CourtierBdFilm {

	public void chercherFilm(FilterCriteria criterias)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transactionFilm = null;
		try
		{
			transactionFilm = session.beginTransaction();
			List<Parameters> myCriterias = criterias.getCriterias();
			String hql = "select * from Film";
			String hqlWhere = " where ";
			List<String> title = new ArrayList<String>();
			List<String> actors = new ArrayList<String>();

			for(Parameters element : myCriterias)
			{
				switch(element.getName())
				{
				case  StaticVariables.ACTEUR_NOM :
					hql += " inner join Film.ActeurFilm ActeurFilm inner join ActeurFilm.Acteur  Acteur inner join Acteur.PersonnageDuCinema PersonnageDuCinemaas as acteurCinema";
					if(element.getValues().size() == 1)
						hqlWhere += " AND nomComplet  = :nomActeur" ;
					else
						hqlWhere += "acteurCinema.nomComplet IN(:nomActeur)";
					actors = element.getValues();
				break;
				
				case StaticVariables.ANNEE_FILM :
					if(element.getValues().size() == 1)
						hqlWhere +="anneeSortie = :anneeSortie";
					else
						hqlWhere +="anneeSortie BETWEEN(:min,:max)";
				break;
				
				case StaticVariables.LANGUE_FILM :
					if(element.getValues().size() == 1)
						hqlWhere += "langueOriginale  = :langueOriginale" ;
					else
						hqlWhere += "langueOriginale IN(:langueOriginale)";					
				break;
				
				case StaticVariables.NOM_GENRE :
					hql += " inner join FilmGenre as filmGenre inner join Genre as genre";
					if(element.getValues().size() == 1)
						hqlWhere += "genre.nom  = :genreNom" ;
					else
						hqlWhere += "genre.nom IN(:genreNom)";	
				break;
				
				case StaticVariables.NOM_PAYS :
					hql += " inner join PaysFilm as paysFilm inner join Pays as pays";
					if(element.getValues().size() == 1)
						hqlWhere += "pays.nom  = :paysNom" ;
					else
						hqlWhere += "pays.nom IN(:paysNom)";
				break;
				
				case StaticVariables.REALISATEUR_NOM :
					hql += " inner join RealisateurFilm as realisateurFilm inner join Realisateur as realisateur inner join PersonnageDuCinema as realisateurCinema";
					if(element.getValues().size() == 1)
						hqlWhere += "realisateurCinema.nomComplet  = :nomRealisateur" ;
					else
						hqlWhere += "realisateurCinema.nomComplet IN(:nomRealisateur)";
				break;
				
				case StaticVariables.TITRE_FILM :
					if(element.getValues().size() == 1)
						hqlWhere += "titre  = :titre" ;
					else
						hqlWhere += "titre IN(:titre)";
					title = element.getValues();
				break;
				}
			
			}
			String request = hql.concat(hqlWhere);
		
			Query query = session.createQuery(request);
			query.setParameterList("titre", title);
			query.setParameterList("nomActeur", actors);

			List<Film> results = query.list();
			if (results.size() > 0) {
	        	String langue = results.get(0).getLangueOriginale();
	        	results.clear();
	        }
		}
		catch(HibernateException e)
		{
			
		}
		
	}
}
