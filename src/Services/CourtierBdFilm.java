package Services;

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
			String hql = "from Film";
			if(myCriterias.size() >0)
				hql += " where ";
			for(Parameters element : myCriterias)
			{
				switch(element.getName())
				{
				case  StaticVariables.ACTEUR_NOM :
					hql += " inner join ";
					if(element.getValues().size() == 1)
						hql += "ActeurCinema.nomComplet  = :nomActeur" + element.getValues();
					else
						hql += "ActeurCinema.nomComplet IN(:nomActeur)";
				break;
				
				case StaticVariables.ANNEE_FILM :
					if(element.getValues().size() == 1)
						hql +="anneeSortie = :anneeSortie";
					else
						hql +="anneeSortie BETWEEN(:min,:max)";
				break;
				
				case StaticVariables.LANGUE_FILM :
					if(element.getValues().size() == 1)
						hql += "langueOriginale  = :langueOriginale" ;
					else
						hql += "langueOriginale IN(:langueOriginale)";					
				break;
				
				case StaticVariables.NOM_GENRE :
					if(element.getValues().size() == 1)
						hql += "Genre.nom  = :genreNom" ;
					else
						hql += "Genre.nom IN(:genreNom)";	
				break;
				
				case StaticVariables.NOM_PAYS :
					if(element.getValues().size() == 1)
						hql += "Pays.nom  = :paysNom" ;
					else
						hql += "Pays.nom IN(:paysNom)";
				break;
				
				case StaticVariables.REALISATEUR_NOM :
					if(element.getValues().size() == 1)
						hql += "RealisateurCinema.nomComplet  = :nomRealisateur" ;
					else
						hql += "RealisateurCinema.nomComplet IN(:nomRealisateur)";
				break;
				
				case StaticVariables.TITRE_FILM :
					if(element.getValues().size() == 1)
						hql += "titre  = :titre" ;
					else
						hql += "titre IN(titre)";
				break;
				}
			
			}
		
			Query query = session.createQuery(hql);
			List<Film> results = query.list();

		}
		catch(HibernateException e)
		{
			
		}
		
	}
}
