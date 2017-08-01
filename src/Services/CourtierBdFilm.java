package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Controllers.StaticVariables;
import Model.Exemplaire;
import Model.Film;
import Model.Genre;
import Model.Location;
import Model.Pays;
import Services.Filters.FilterCriteria;
import Services.Filters.Parameters;

public class CourtierBdFilm {

	private static Session session;
	
	public CourtierBdFilm()
	{
		session = HibernateUtil.getSessionFactory().openSession();
	}
	
	
	public static List<Genre> getGenres()
	{
		Transaction transactionGenre = null;
		try
		{
			transactionGenre = session.beginTransaction();
			String hql = "SELECT idGenre,nom FROM Genre ORDER BY nom ASC";
			Query query = session.createQuery(hql);
			List<Genre> genres = query.list();
			if (!transactionGenre.wasCommitted())
				transactionGenre.commit();
			session.flush();
			return genres;
		}
		catch(HibernateException e)
		{
			System.out.println(e);
		}
		finally
		{
			session.flush();
		}
		return null;
	}
	
	
	public static List<Pays> getPays()
	{
		Transaction transactionPays = null;
		try
		{
			transactionPays = session.beginTransaction();
			String hql = "SELECT idPays,nom FROM Pays ORDER BY nom ASC";
			Query query = session.createQuery(hql);
			List<Pays> pays = query.list();
			if (!transactionPays.wasCommitted())
				transactionPays.commit();
		
			return pays;
		}
		catch(HibernateException e)
		{
			System.out.println(e);
		}
		finally
		{
			session.flush();
		}
		return null;
	}
	
	public static List<String> getLangues()
	{
		Transaction transactionLangues = null;
		try
		{
			transactionLangues = session.beginTransaction();
			String hql = "SELECT DISTINCT langueOriginale FROM Film ORDER BY langueOriginale ASC";
			Query query = session.createQuery(hql);
			List<String> filmLangue = query.list();
			if (!transactionLangues.wasCommitted())
				transactionLangues.commit();
			
			return filmLangue;
		}
		catch(HibernateException e)
		{
			System.out.println(e);
		}
		finally
		{
			session.flush();
		}
		return null;
	}
	
	public List<Film> chercherFilm(FilterCriteria criterias)
	{
		Transaction transactionFilm = null;
		try
		{
			transactionFilm = session.beginTransaction();
			List<Parameters> myCriterias = criterias.getCriterias();
			String hql = "SELECT DISTINCT f FROM Film f";// JOIN f.acteurFilms af JOIN f.paysFilms pf JOIN f.realisateurFilms rf";
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
			List<String> annee = new ArrayList<String>();
			List<String> langues = new ArrayList<String>();
			List<String> genres = new ArrayList<String>();
			List<String> pays = new ArrayList<String>();
			List<String> realisateurs = new ArrayList<String>();

			for(Parameters element : myCriterias)
			{
				boolean singleValue = element.getValues().size() == 1;
				
				switch(element.getName())
				{

				case StaticVariables.ACTEUR_NOM :
					hasActeurCriteria = true;
					actors = element.getValues();

					hqlWHERE += " AND f.titre IN (SELECT af.film.titre FROM ActeurFilm af"
								+ " WHERE af.acteur.nomComplet IN (:acteurs)"
								+ " GROUP BY af.film.titre"
								+ " HAVING COUNT(*) >= :nbActeurs)";
					break;

				case StaticVariables.ANNEE_FILM :
					hasAnneeCriteria = true;
					annee = element.getValues();
					hqlWHERE += singleValue ? 
							" AND f.anneeSortie = :annee"
							:
								" AND f.anneeSortie BETWEEN :min AND :max";
					break;

				case StaticVariables.LANGUE_FILM :
					hasLangueCriteria = true;
					langues = element.getValues();
					hqlWHERE += " AND f.langueOriginale IN (:langues)";				
					break;

				case StaticVariables.NOM_GENRE :
					hasGenreCriteria = true;
					genres = element.getValues();
					hqlWHERE += " AND f.titre IN (SELECT fg.film.titre FROM FilmGenre fg"
								+ " WHERE fg.genre.nom IN (:genres)"
								+ " GROUP BY fg.film.titre"
								+ " HAVING COUNT(*) >= :nbGenres)";
					break;

				case StaticVariables.NOM_PAYS :
					hasPaysCriteria = true;
					pays = element.getValues();
					System.out.println(pays.get(0));
					hqlWHERE += " AND f.titre IN (SELECT pf.film.titre FROM PaysFilm pf"
							+ " WHERE pf.pays.nom IN (:pays)"
							+ " GROUP BY pf.film.titre"
							+ " HAVING COUNT(*) >= :nbPays)";
					break;

				case StaticVariables.REALISATEUR_NOM :
					hasRealisateurCriteria = true;
					realisateurs = element.getValues();
					hqlWHERE += " AND f.titre IN (SELECT rf.film.titre FROM RealisateurFilm rf"
								+ " WHERE rf.realisateur.nomComplet IN (:realisateurs)"
								+ " GROUP BY rf.film.titre"
								+ " HAVING COUNT(*) >= :nbRealisateurs)";
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
			
			Query query = session.createQuery(request);
			
			if (hasTitreCriteria) {
				if (title.size() > 1)
					query.setParameterList("titre", title);
				else
					query.setParameter("titre", title.get(0));
			}
			if (hasActeurCriteria) {
				query.setInteger("nbActeurs", actors.size());
				query.setParameterList("acteurs", actors);
			}
			if (hasAnneeCriteria) {
				if (annee.size() > 1) {
					query.setInteger("min", Integer.parseInt(annee.get(0)));
					query.setInteger("max", Integer.parseInt(annee.get(1)));
				} else {
					query.setInteger("annee", Integer.parseInt(annee.get(0)));
				}
			}
			if (hasLangueCriteria) {
				query.setParameterList("langues", langues);
			}
			if (hasGenreCriteria) {
				query.setInteger("nbGenres", genres.size());
				query.setParameterList("genres", genres);				
			}
			if (hasPaysCriteria) {
				query.setInteger("nbPays", pays.size());
				query.setParameterList("pays", pays);
			}
			if (hasRealisateurCriteria) {
				query.setInteger("nbRealisateurs", realisateurs.size());
				query.setParameterList("realisateurs", realisateurs);
			}
			
			List<Film> results = query.list();
			for (Film f : results) {
				System.out.println(f.getFilmGenres());
			}
			
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
	
	/*public  void locationFilm(Film film)
	{
			Forfait forfait = StaticVariables.client.getForfait();
			getTempExemplaire(film.getIdFilm());
			film.getExemplaires();
			Query query = session.createQuery("insert into Location(datedebut,dateretour,idclient,idexemplaire) VALUES(:dateDebut,:dateRetour,:idClient,:idExemplaire");
			Date now = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(now);
			c.add(Calendar.DATE, forfait.getDureeMaxJours());
			query.setParameter("dateDebut", now);
			query.setParameter("dateRetour", c.getTime());
			query.setParameter("idClient", StaticVariables.client.getIdUtilisateur());
		//	query.setParameter("idExemplaire", exemplaire.getIdExemplaire());
		//	int result = query.executeUpdate();	
	}*/
	
	public void locationFilm(Film film) throws Throwable
	{
		Transaction transaction = session.beginTransaction();
		try
		{
			for(Exemplaire exemplaire : film.getExemplaires())
			{
				if (exemplaire.isEstLoue().equals("F"))
				{
					exemplaire.setEstLoue("T");
					
					Location location = new Location();
					location.setClient(StaticVariables.client);
					location.setDateDebut(Date.from(Instant.now()));
					location.setExemplaire(exemplaire);
					
					session.update(exemplaire);
					session.save(location);
					
					if (!transaction.wasCommitted()) 
						transaction.commit();
					
					return;
				}
			}
			
			throw new Exception("Il n'y a plus d'exemplaire disponible de ce film.");
		}
		catch(JDBCException e)
		{
			transaction.rollback();
			throw new Exception("Le nombre maximal de locations pour votre forfait a été atteint.");
		}
		finally
		{
			//session.flush();
		}
	}
	
	public static List<Film> getRecommandedMovies(Film film)
	{
		ArrayList<Film> recommandations = new ArrayList<Film>();
	    try {
	    	Connection con = SimpleConnection.GetSimpleDBConnection();
		    PreparedStatement stmt = null;
			String sql = "SELECT * FROM (SELECT idfilmk FROM MATERIALIZED_CORRELATION_COTES WHERE idfilmj = ? ORDER BY correlation DESC) WHERE ROWNUM <= 3";
	        stmt = con.prepareStatement(sql);
	        stmt.setInt(1, film.getXmlId());
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            int recommandedMovieId = rs.getInt("idfilmk");
				String hql = "SELECT DISTINCT f FROM Film f WHERE xmlid = :xmlid";
				Query query = session.createQuery(hql);
				query.setInteger("xmlid", recommandedMovieId);
				for(Object obj : query.list())
				{
					Film recommandation = (Film)obj;
					recommandations.add(recommandation);
				}
	        }
	        stmt.close();
	    } catch (SQLException e ) {
			System.out.println(e);
	    }
		return recommandations;
	}
	
	public static Double getAverageRating(Film film)
	{
		Double averageRating = null;
	    try {
	    	Connection con = SimpleConnection.GetSimpleDBConnection();
		    PreparedStatement stmt = null;
			String sql = "SELECT cote_moyenne FROM MATERIALIZED_COTES_MOYENNES WHERE idfilm = ?";
	        stmt = con.prepareStatement(sql);
	        stmt.setInt(1, film.getXmlId());
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next())
	        {
	        	averageRating = rs.getDouble("cote_moyenne");
	        }
	        stmt.close();
	    } catch (SQLException e ) {
			System.out.println(e);
	    }
		return averageRating;
	}
}
