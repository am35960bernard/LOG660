package Services;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.Client;
import Model.Observable;

public class CourtierBdUtilisateur extends Observable{

	public String validateAuthentication(String txtCourrielUtilisateur, String txtMotDePasseUtilisateur){
		
		Session sessionAuthentication = HibernateUtil.getSessionFactory().openSession();
		Transaction transactionAuthentication = null;
		
		String authenticationIdUser = null;
			
		try {
						
			transactionAuthentication = sessionAuthentication.beginTransaction();
				
			String hql = "from Client where courriel like :utilisateurCourriel and motDePasse like :utilisateurMotDePasse";			
			Query query = sessionAuthentication.createQuery(hql);
			query.setParameter("utilisateurCourriel",txtCourrielUtilisateur);
			query.setParameter("utilisateurMotDePasse",txtMotDePasseUtilisateur);
			List<Client> results = query.list();
			
	        if (results.size() > 0) {
	        	authenticationIdUser =  results.get(0).getPrenom();
	        	results.clear();
	        }

				
		} catch (HibernateException e) {
			transactionAuthentication.rollback();
			e.printStackTrace();
		} finally {
			sessionAuthentication.close();
		}
	
		return authenticationIdUser;
	}

}
