package Services;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.Client;

public class CourtierBdUtilisateur {

	public String validateAuthentication(String txtCourrielUtilisateur, String txtMotDePasseUtilisateur) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transactionAuthentication = null;
		
		String authenticationIdUser = null;
			
		try {
						
			transactionAuthentication = session.beginTransaction();
			
			/*
			String hql = "from Film";			
			Query query = session.createQuery(hql);
			List<Film> results = query.list();
			
			System.out.println(results.size());
			
			for (Film film : results) {
				Random random = new Random();
				int nbExemplaires = random.ints(1, 101).findFirst().getAsInt();
				
				for (int i = 0; i < nbExemplaires; i++) {
					Exemplaire exemplaire = new Exemplaire();
			        exemplaire.setFilm(film);
			        exemplaire.setNumCopie(i + 1);
			        
			        session.save(exemplaire);
			        if ( i % 20 == 0 ) { //20, same as the JDBC batch size
			            //flush a batch of inserts and release memory:
			            session.flush();
			            session.clear();
			        }
				}
			}
			
			transactionAuthentication.commit();*/
			
			
			String hql = "from Client where courriel like :utilisateurCourriel and motDePasse like :utilisateurMotDePasse";			
			Query query = session.createQuery(hql);
			query.setParameter("utilisateurCourriel", txtCourrielUtilisateur);
			query.setParameter("utilisateurMotDePasse", txtMotDePasseUtilisateur);
			List<Client> results = query.list();
			
	        if (results.size() > 0) {
	        	authenticationIdUser = results.get(0).getPrenom();
	        	results.clear();
	        }

	        		
		} catch (HibernateException e) {
			transactionAuthentication.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	
		return authenticationIdUser;
	}

}
