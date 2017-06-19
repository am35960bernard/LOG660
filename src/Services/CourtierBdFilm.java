package Services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Controllers.StaticVariables;
import Model.Exemplaire;
import Model.Film;
import Model.Forfait;
import Services.Filters.FilterCriteria;
import Services.Filters.Parameters;

public class CourtierBdFilm {

	private Session session;
	
	public CourtierBdFilm()
	{
		session = HibernateUtil.getSessionFactory().openSession();
	}
	
	public List<Film> chercherFilm(FilterCriteria criterias)
	{
		Transaction transactionFilm = null;
		try
		{
			transactionFilm = session.beginTransaction();
			List<Parameters> myCriterias = criterias.getCriterias();
			String hql = "SELECT DISTINCT f FROM Film f JOIN f.acteurFilms af";
			String hqlWHERE = " WHERE 1 = 1"; // This wouldn't cut it if we used OR statements though.
			boolean hasActeurCriteria = false;
			boolean hasAnneeCriteria = false;
			boolean hasLangueCriteria = false;
			boolean hasGenreCriteria = false;
			boolean hasPaysCriteria = false;
			boolean hasRealisateurCriteria = false;
			boolean hasTitreCriteria = false;
			List<String> title = new ArrayList<String>();
			List<String> actors = new ArrayList<String>();

			for(Parameters element : myCriterias)
			{
				boolean singleValue = element.getValues().size() == 1;
				
				switch(element.getName())
				{

				case StaticVariables.ACTEUR_NOM :
					hasActeurCriteria = true;
					actors = element.getValues();
					hqlWHERE += /*singleValue ?
							" AND af.acteur.nomComplet = :nomActeur"
							+ " AND af.film.idFilm = f.idFilm"
							:*/
								" AND f.titre IN (SELECT af.film.titre FROM ActeurFilm af"
								+ " WHERE af.acteur.nomComplet IN (:nomActeur)"
								+ " GROUP BY af.film.titre"
								+ " HAVING COUNT(*) >= :nbActeurs)";
					break;

				case StaticVariables.ANNEE_FILM :
					hasAnneeCriteria = true;
					hqlWHERE += singleValue ? 
							" AND f.anneeSortie = :anneeSortie"
							:
								" AND f.anneeSortie BETWEEN(:min, :max)";
					break;

				case StaticVariables.LANGUE_FILM :
					hasLangueCriteria = true;
					hqlWHERE += singleValue ? 
							" AND f.langueOriginale = :langueOriginale"
							:
								" AND f.langueOriginale IN(:langueOriginale)";					
					break;

				case StaticVariables.NOM_GENRE :
					hasGenreCriteria = true;
					hqlWHERE += singleValue ? 
							" AND f.genre.nom = :genreNom"
							:
								" AND f.genre.nom IN(:genreNom)";	
					break;

				case StaticVariables.NOM_PAYS :
					hasPaysCriteria = true;
					hqlWHERE += singleValue ? 
							" AND pays.nom = :paysNom"
							:
								" AND pays.nom IN(:paysNom)";
					break;

				case StaticVariables.REALISATEUR_NOM :
					hasRealisateurCriteria = true;
					hqlWHERE += singleValue ? 
							" AND realisateurCinema.nomComplet = :nomRealisateur"
							:
								" AND realisateurCinema.nomComplet IN(:nomRealisateur)";
					break;

				case StaticVariables.TITRE_FILM :
					hasTitreCriteria = true;
					hqlWHERE += singleValue ? 
							" AND f.titre = :titre"
							:
								" AND f.titre IN(:titre)";
					title = element.getValues();
					break;
				}

			}
			String request = hql.concat(hqlWHERE);
			
			System.out.println(request);
			Query query = session.createQuery(request);
			System.out.println("QUERY STARTED !");
			if (hasTitreCriteria) 
				if (title.size() > 1) 
					query.setParameterList("titre", title);
				else
					query.setParameter("titre", title.get(0));
			if (hasActeurCriteria) {
				query.setInteger("nbActeurs", actors.size());
				query.setParameterList("nomActeur", actors);
			}

			System.out.println("GET DATA !");
			List<Film> results = query.list();
			for (Film f : results) {
				System.out.println(f.getTitre());
			}
			
			System.out.println("QUERY DONE !");
			
			if (!transactionFilm.wasCommitted())
				transactionFilm.commit();
			
			return results;

		}
		catch(HibernateException e)
		{
			transactionFilm.rollback();
		}
		finally 
		{
			session.flush();
		}
		return null;

	}
	
	public void insertFilm(Film film)
	{
		Forfait forfait = StaticVariables.client.getForfait();
		Exemplaire exemplaire = (Exemplaire) film.getExemplaires();
		Query query = session.createQuery("insert into Location(datedebut,dateretour,idclient,idexemplaire) VALUES(:dateDebut,:dateRetour,:idClient,:idExemplaire");
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.add(Calendar.DATE, forfait.getDureeMaxJours());
		query.setParameter("dateDebut", now);
		query.setParameter("dateRetour", c.getTime());
		query.setParameter("idClient", StaticVariables.client.getIdUtilisateur());
		query.setParameter("idExemplaire", exemplaire.getIdExemplaire());
		int result = query.executeUpdate();

		
	}
}
