package Services;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Controllers.StaticVariables;
import Model.Acteur;
import Model.ActeurFilm;
import Model.Client;
import Model.Film;

public class CourtierBdUtilisateur {

	public String validateAuthentication(String txtCourrielUtilisateur, String txtMotDePasseUtilisateur) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transactionAuthentication = null;
		
		String authenticationIdUser = null;
			
		try {
						
			transactionAuthentication = session.beginTransaction();

			/*
			String hql = "SELECT f.titre FROM Film f JOIN f.acteurFilms af WHERE"
					+ " f.titre IN (SELECT fg.film.titre FROM FilmGenre fg"
					+ " WHERE fg.genre.nom IN ('Action')"
					+ " GROUP BY fg.film.titre"
					+ " HAVING COUNT(*) >= 1)";
			int id = 1605;
			Query query = session.createQuery(hql);
			List<Film> results = query.list();

			for (Film x : results) {
				System.out.println(x.getTitre());
			}*/
			
			
			
			String hql = "from Client where courriel like :utilisateurCourriel and motDePasse like :utilisateurMotDePasse";			
			Query query = session.createQuery(hql);
			query.setParameter("utilisateurCourriel", txtCourrielUtilisateur);
			query.setParameter("utilisateurMotDePasse", txtMotDePasseUtilisateur);
			List<Client> results = query.list();
			
	        if (results.size() > 0) {
	        	StaticVariables.client = results.get(0);
	        	authenticationIdUser = results.get(0).getPrenom();
	        	results.clear();
	        }

	        		
		} catch (HibernateException e) {
			transactionAuthentication.rollback();
			e.printStackTrace();
		} finally {
			if (!transactionAuthentication.wasCommitted())
				transactionAuthentication.commit();
			session.flush();
			session.close();
		}
	
		return authenticationIdUser;
	}

}
